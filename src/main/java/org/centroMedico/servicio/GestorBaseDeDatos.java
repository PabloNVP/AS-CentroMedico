package org.centroMedico.servicio;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.centroMedico.modelo.Medico;
import org.centroMedico.modelo.Paciente;
import org.centroMedico.modelo.Situacion;

public class GestorBaseDeDatos {
    private static final String DRIVER = "jdbc:sqlite:";
    private static final String DB_PATH = "centroMedico.db";
    private static GestorBaseDeDatos instancia;

    public static GestorBaseDeDatos getInstance(){
        if(instancia == null)
            instancia = new GestorBaseDeDatos();

        return instancia;
    }

    private GestorBaseDeDatos(){};

    public void iniciarBaseDeDatos() throws Exception{
        if (!this.existeBaseDeDatos()) 
            this.crearBaseDeDatos();
    }

    private boolean existeBaseDeDatos() {
        return new File(DB_PATH).exists();
    }

    private void crearBaseDeDatos() throws Exception {
        try (Connection conn = DriverManager.getConnection(DRIVER + DB_PATH)) {
            this.crearTablaPaciente(conn);
            this.crearTablaMedico(conn);
            this.crearTablaSituacion(conn);
        }catch(SQLException ex){
            throw new Exception(GestorMensaje.ERROR_BD_CREACION.getMensaje());
        }
    }

    private void activarClaveForanea(Connection conn) throws Exception{
        try(Statement stmt = conn.createStatement()){
            stmt.execute("PRAGMA foreign_keys = ON;");
        }catch(SQLException ex){
            throw new Exception(GestorMensaje.ERROR_BD_CONFIGURACION.getMensaje());
        }   
    }

    private void crearTablaPaciente(Connection conn) throws Exception{
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS Paciente( "
                        + " codigo INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + " nombre TEXT NOT NULL "
                        + ");");
        }catch(SQLException ex){
            throw new Exception(GestorMensaje.ERROR_PACIENTE_CREACION.getMensaje());
        }      
    }

    public void crearTablaMedico(Connection conn) throws Exception{
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS Medico( "
                            + " codigo INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + " nombre TEXT NOT NULL, " 
                            + " especialidad TEXT NOT NULL "
                            + ");");
        }catch(SQLException ex){
            throw new Exception(GestorMensaje.ERROR_MEDICO_CREACION.getMensaje());
        }    
    }

    public void crearTablaSituacion(Connection conn) throws Exception{
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS Situacion( "
                            + " fecha DATETIME, "
                            + " codigoMedico INTEGER, "
                            + " codigoPaciente INTEGER, "
                            + " situacion TEXT NOT NULL, "
                            + " PRIMARY KEY (fecha, codigoMedico, codigoPaciente), " 
                            + " CONSTRAINT fk_paciente FOREIGN KEY (codigoPaciente) REFERENCES Paciente(codigo), "
                            + " CONSTRAINT fk_medico FOREIGN KEY (codigoMedico) REFERENCES Medico(codigo) "
                            + ");");
        }catch(SQLException ex){
            throw new Exception(GestorMensaje.ERROR_SITUACION_CREACION.getMensaje());
        }    
    }

    public void ingresarPaciente(Paciente paciente) throws Exception{
        try (Connection conn = DriverManager.getConnection(DRIVER + DB_PATH);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO "
                                                            + "Paciente(codigo, nombre) "
                                                            + "VALUES (?, ?);");) {

            pstmt.setString(1, paciente.getCodigo());
            pstmt.setString(2, Seguridad.getInstance().cifrado(paciente.getNombre()));

            pstmt.executeUpdate();

        }catch(SQLException ex){
            if (ex.getErrorCode() == 19) 
                throw new Exception(GestorMensaje.ERROR_PACIENTE_DUPLICADO.getMensaje());
            
            throw new Exception(GestorMensaje.ERROR_PACIENTE_INSERTAR.getMensaje());
        }
    }

    public void ingresarMedico(Medico medico) throws Exception{
        try (Connection conn = DriverManager.getConnection(DRIVER + DB_PATH);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO "
                                                            + "Medico(codigo, nombre, especialidad) "
                                                            + "VALUES (?, ?, ?);");) {

            pstmt.setString(1, medico.getCodigo());
            pstmt.setString(2, Seguridad.getInstance().cifrado(medico.getNombre()));
            pstmt.setString(3, medico.getEspecialidad());

            pstmt.executeUpdate();

        }catch(SQLException ex){
            if (ex.getErrorCode() == 19) 
                throw new Exception(GestorMensaje.ERROR_MEDICO_DUPLICADO.getMensaje());
            
            throw new Exception(GestorMensaje.ERROR_MEDICO_INSERTAR.getMensaje());
        }
    }

    public void ingresarSituacion(Situacion situacion) throws Exception{
        try (Connection conn = DriverManager.getConnection(DRIVER + DB_PATH);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO "
                                                            + "Situacion(fecha, codigoPaciente, codigoMedico, situacion) "
                                                            + "VALUES (?, ?, ?, ?);");) {

                activarClaveForanea(conn);
                
                pstmt.setString(1, situacion.getFecha());
                pstmt.setString(2, situacion.getCodigoPaciente());
                pstmt.setString(3, situacion.getCodigoMedico());
                pstmt.setString(4, Seguridad.getInstance().cifrado(situacion.getDescripcion()));

                pstmt.executeUpdate();

        }catch(SQLException ex){
            throw new Exception(GestorMensaje.ERROR_SITUACION_NO_EXISTEN_CODIGOS.getMensaje());
        }
    }

    public ArrayList<String> obtenerSituacionesXMedico(Medico medico) throws Exception{
        ArrayList<String> situaciones = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DRIVER + DB_PATH);
            PreparedStatement pstmt = conn.prepareStatement("SELECT situacion "
                                                            + "FROM Situacion "
                                                            + "WHERE codigoMedico = ?");) {

            pstmt.setString(1, medico.getCodigo());

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    situaciones.add(Seguridad.getInstance().descifrado(rs.getString("situacion")));
                }
            }
        }catch(SQLException ex){
            throw new Exception(GestorMensaje.ERROR_INFORMES_SITUACIONES.getMensaje());
        }

        return situaciones;
    }

    public ArrayList<String> obtenerPacientesXMedico(Medico medico) throws Exception{
        ArrayList<String> pacientes = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DRIVER + DB_PATH);
            PreparedStatement pstmt = conn.prepareStatement("SELECT nombre "
                                                            + "FROM Paciente AS P "
                                                            + "INNER JOIN Situacion AS S ON P.codigo = S.codigoPaciente "
                                                            + "WHERE S.codigoMedico = ?");) {

            pstmt.setString(1, medico.getCodigo());

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    pacientes.add(Seguridad.getInstance().descifrado(rs.getString("nombre")));
                }
            }
        }catch(SQLException ex){
            throw new Exception(GestorMensaje.ERROR_INFORMES_PACIENTES.getMensaje());
        }

        return pacientes;
    }
}
 
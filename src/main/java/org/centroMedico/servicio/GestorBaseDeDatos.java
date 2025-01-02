package org.centroMedico.servicio;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/** 
 * TODO: APLICAR SEGURIDAD 
 * */
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

    public void crearTablaPaciente(Connection conn) throws Exception{
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
                            + " FOREIGN KEY (codigoPaciente) REFERENCES Paciente(codigo), "
                            + " FOREIGN KEY (codigoMedico) REFERENCES Medico(codigo) "
                            + ");");
        }catch(SQLException ex){
            throw new Exception(GestorMensaje.ERROR_SITUACION_CREACION.getMensaje());
        }    
    }

    public void ingresarPaciente(String codigo, String nombre) throws Exception{
        try (Connection conn = DriverManager.getConnection(DRIVER + DB_PATH);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO "
                                                            + "Paciente(codigo, nombre) "
                                                            + "VALUES (?, ?);");) {

            pstmt.setString(1, codigo);
            pstmt.setString(2, nombre);

            pstmt.executeUpdate();

        }catch(SQLException ex){
            if (ex.getErrorCode() == 19) 
                throw new Exception(GestorMensaje.ERROR_PACIENTE_DUPLICADO.getMensaje());
            
            throw new Exception(GestorMensaje.ERROR_PACIENTE_INSERTAR.getMensaje());
        }
    }

    public void ingresarMedico(String codigo, String nombre, String especialidad) throws Exception{
        try (Connection conn = DriverManager.getConnection(DRIVER + DB_PATH);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO "
                                                            + "Medico(codigo, nombre, especialidad) "
                                                            + "VALUES (?, ?, ?);");) {

            pstmt.setString(1, codigo);
            pstmt.setString(2, nombre);
            pstmt.setString(3, especialidad);

            pstmt.executeUpdate();

        }catch(SQLException ex){
            if (ex.getErrorCode() == 19) 
                throw new Exception(GestorMensaje.ERROR_MEDICO_DUPLICADO.getMensaje());
            
            throw new Exception(GestorMensaje.ERROR_MEDICO_INSERTAR.getMensaje());
        }
    }

    public ArrayList<String> obtenerEnfermedades(String codigo) throws Exception{
        ArrayList<String> enfermedades = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DRIVER + DB_PATH);
            PreparedStatement pstmt = conn.prepareStatement("SELECT nombre "
                                                            + "FROM Medico "
                                                            + "WHERE codigo = ?");) {

            pstmt.setString(1, codigo);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    enfermedades.add(rs.getString("nombre"));
                }
            }
        }catch(SQLException ex){
            throw new Exception("Error al querer obtener las enfermedades.");
        }

        return enfermedades;
    }


}
 
package org.centroMedico.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.centroMedico.servicio.GestorMensaje;

public class Situacion {
    private Medico medico;
    private Paciente paciente;
    private String descripcion;
    private LocalDateTime fecha;

    public Situacion(String codPac, String codMed, String descripcion) throws Exception{
        this.medico = new Medico(codMed);
        this.paciente = new Paciente(codPac);
        setDescripcion(descripcion);
        this.fecha = LocalDateTime.now();
    }

    public String getCodigoMedico(){
        return this.medico.getCodigo();
    }

    public String getCodigoPaciente(){
        return this.paciente.getCodigo();
    }

    public String getDescripcion(){
        return this.descripcion;
    }

    public String getFecha(){
        return this.fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private void setDescripcion(String descripcion) throws Exception{
		if(!esFormatoValido(descripcion)) 
			throw new Exception(GestorMensaje.ERROR_FORMATO_SITUACION.getMensaje());
		
        this.descripcion = descripcion;
    }

    public boolean esFormatoValido(String descripcion) {
		return descripcion.matches("^[A-Za-záéíóúÁÉÍÓÚ][A-Za-z0-9 áéíóúÁÉÍÓÚ]*$");
	}
}

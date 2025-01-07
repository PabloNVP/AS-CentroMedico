package org.centroMedico.controlador;

import org.centroMedico.servicio.GestorBaseDeDatos;
import org.centroMedico.modelo.Paciente;

public class ControllerIngresarPaciente {

    public ControllerIngresarPaciente(){}

    public void ingresarPaciente(String codigo, String nombre) throws Exception{
		try {
			GestorBaseDeDatos.getInstance().ingresarPaciente(new Paciente(codigo, nombre));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
	}
    
}

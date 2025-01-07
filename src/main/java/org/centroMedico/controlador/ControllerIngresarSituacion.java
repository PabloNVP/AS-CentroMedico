package org.centroMedico.controlador;

import org.centroMedico.modelo.Situacion;
import org.centroMedico.servicio.GestorBaseDeDatos;

public class ControllerIngresarSituacion {
    
    public ControllerIngresarSituacion(){};

    public void ingresarSituacionPaciente(String codPac, String codMed, String descripcion) throws Exception {
		
		Situacion situacion = new Situacion(codPac, codMed, descripcion);

		GestorBaseDeDatos.getInstance().ingresarSituacion(situacion);
	}
}

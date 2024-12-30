package org.centroMedico.controlador;

import org.centroMedico.servicio.GestorBaseDeDatos;
import org.centroMedico.servicio.GestorMensaje;
import org.centroMedico.servicio.Validador;

public class ControllerIngresarPaciente {

    public ControllerIngresarPaciente(){}

    public void ingresarPaciente(String codPac, String nomPac) throws Exception{
		// Valida el codigo del paciente.
		if(!Validador.esCodigoValido(codPac)){
			throw new Exception(GestorMensaje.ERROR_CODIGO_RANGO_PACIENTE.getMensaje());
		}
		
		// Valida el Rango del nombre del paciente.
		if(!Validador.esRangoNombreValido(nomPac, 1, 21)) {
			throw new Exception(GestorMensaje.ERROR_NOMBRE_RANGO_PACIENTE.getMensaje());
		}
		
		// Valida el Formato del nombre del paciente.
		if(!Validador.esFormatoNombreValido(nomPac)) {
			throw new Exception(GestorMensaje.ERROR_NOMBRE_FORMATO_PACIENTE.getMensaje());
		}
		
		try {
			GestorBaseDeDatos.getInstance().ingresarPaciente(codPac, nomPac);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
	}
    
}

package org.centroMedico.controlador;

import org.centroMedico.servicio.GestorBaseDeDatos;
import org.centroMedico.servicio.GestorMensaje;
import org.centroMedico.servicio.Validador;

public class ControllerIngresarSituacion {
    
    public ControllerIngresarSituacion(){};

    public void ingresarSituacionPaciente(String codPac, String codMed, String situacion) throws Exception {
		// Valida el codigo del paciente.
		if(!Validador.esCodigoValido(codPac)){
			throw new Exception(GestorMensaje.ERROR_CODIGO_RANGO_PACIENTE.getMensaje());
		}
		
		// Valida el codigo del medico.
		if(!Validador.esCodigoValido(codMed)){
			throw new Exception(GestorMensaje.ERROR_CODIGO_RANGO_MEDICO.getMensaje());
		}
		
		// Valida el Rango del nombre del paciente.
		if(!Validador.esRangoNombreValido(situacion, 9, 201)) {
			throw new Exception(GestorMensaje.ERROR_RANGO_SITUACION.getMensaje());
		}
		
		// Valida el formato de la situacion.
		if(!Validador.esFormatoNombreValido(situacion)) {
			throw new Exception(GestorMensaje.ERROR_FORMATO_SITUACION.getMensaje());
		}
		
		GestorBaseDeDatos.getInstance().ingresarSituacion(codPac, codMed, situacion);
	}
}

package org.centroMedico.controlador;

import org.centroMedico.servicio.GestorMensaje;
import org.centroMedico.servicio.Validador;

public class ControllerIngresarSituacion {
    
    public ControllerIngresarSituacion(){};

    public void ingresarSituacionPaciente(String codPac, String codMed, String sit) throws Exception {
		// Valida el codigo del paciente.
		if(!Validador.esCodigoValido(codPac)){
			throw new Exception(GestorMensaje.ERROR_CODIGO_RANGO_PACIENTE.getMensaje());
		}
		
		// Valida el codigo del medico.
		if(!Validador.esCodigoValido(codMed)){
			throw new Exception(GestorMensaje.ERROR_CODIGO_RANGO_MEDICO.getMensaje());
		}
		
		// Valida el Rango del nombre del paciente.
		if(!Validador.esRangoNombreValido(sit, 9, 201)) {
			throw new Exception(GestorMensaje.ERROR_RANGO_SITUACION.getMensaje());
		}
		
		// Valida el formato de la situacion.
		if(!Validador.esFormatoNombreValido(sit)) {
			throw new Exception(GestorMensaje.ERROR_FORMATO_SITUACION.getMensaje());
		}
		
		/* SQL FUNCION
		} catch (IOException ioe) {
			// Envia una excepción en caso de no tener permisos en el archivo.
			throw new Exception(GestorMensaje.ERROR_ARCHIVO_SITUACION.getMensaje());
		} */
	}
}

package org.centroMedico.controlador;

import org.centroMedico.servicio.GestorBaseDeDatos;
import org.centroMedico.servicio.GestorMensaje;
import org.centroMedico.servicio.Validador;

public class ControllerIngresarMedico {
    
    public ControllerIngresarMedico(){};

    public void ingresarMedico(String codigo, String nombre, String especialidad) throws Exception{
		// Valida el codigo del medico.
		if(!Validador.esCodigoValido(codigo)){
			throw new Exception(GestorMensaje.ERROR_CODIGO_RANGO_MEDICO.getMensaje());
		}
		
		// Valida el Rango del nombre del medico.
		if(!Validador.esRangoNombreValido(nombre, 1, 21)) {
			throw new Exception(GestorMensaje.ERROR_NOMBRE_RANGO_MEDICO.getMensaje());
		}
		
		// Valida el Formato del nombre del medico.
		if(!Validador.esFormatoNombreValido(nombre)) {
			throw new Exception(GestorMensaje.ERROR_NOMBRE_FORMATO_MEDICO.getMensaje());
		}
		
		// Valida la especialidad del medico.
		if(!Validador.esEspecialidadValida(especialidad)) {
			throw new Exception(GestorMensaje.ERROR_ESPECIALIDAD_VALORES_MEDICO.getMensaje());
		}
		
		try {
			GestorBaseDeDatos.getInstance().ingresarMedico(codigo, nombre, especialidad);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
	}
}

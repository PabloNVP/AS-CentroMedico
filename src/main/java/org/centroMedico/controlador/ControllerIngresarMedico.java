package org.centroMedico.controlador;

import org.centroMedico.modelo.Medico;
import org.centroMedico.servicio.GestorBaseDeDatos;

public class ControllerIngresarMedico {
    
    public ControllerIngresarMedico(){};

    public void ingresarMedico(String codigo, String nombre, String especialidad) throws Exception{
		
		
		
		/**   Valida el Rango del nombre del medico.
		if(!Validador.esRangoNombreValido(nombre, 1, 21)) {
			throw new Exception(GestorMensaje.ERROR_NOMBRE_RANGO_MEDICO.getMensaje());
		} 
		
		// Valida el Formato del nombre del medico.
		if(!Validador.esFormatoNombreValido(nombre)) {
			throw new Exception(GestorMensaje.ERROR_NOMBRE_FORMATO_MEDICO.getMensaje());
		}
		
		// Valida la especialidad del medico.
		if(!Especialidad.esEspecialidadValida(especialidad)) {
			throw new Exception(;
		} */
		
		try {
			GestorBaseDeDatos.getInstance().ingresarMedico(new Medico(codigo, nombre, especialidad));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
	}
}

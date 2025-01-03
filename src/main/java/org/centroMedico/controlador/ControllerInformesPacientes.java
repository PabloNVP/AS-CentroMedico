package org.centroMedico.controlador;

import java.util.ArrayList;

import org.centroMedico.servicio.GestorBaseDeDatos;
import org.centroMedico.servicio.GestorMensaje;
import org.centroMedico.servicio.Validador;

public class ControllerInformesPacientes {
    
    public ControllerInformesPacientes(){};

    public ArrayList<String> listarPacientesPorMedico(String codigo) throws Exception{
		
		// Valida el codigo del medico.
		if(!Validador.esCodigoValido(codigo))
			throw new Exception(GestorMensaje.ERROR_CODIGO_RANGO_MEDICO.getMensaje());
		
		return GestorBaseDeDatos.getInstance().obtenerPacientesXMedico(codigo);
	}
}

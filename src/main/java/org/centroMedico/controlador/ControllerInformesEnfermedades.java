package org.centroMedico.controlador;


import java.util.ArrayList;

import org.centroMedico.servicio.GestorBaseDeDatos;
import org.centroMedico.servicio.GestorMensaje;
import org.centroMedico.servicio.Validador;
import org.centroMedico.controlador.ControllerInformesEnfermedades;

public class ControllerInformesEnfermedades {

    public ControllerInformesEnfermedades(){};
	
    public ArrayList<String> listarEnfermedadesPorMedico(String codigo) throws Exception {
		
		// Valida el codigo del medico.
		if(!Validador.esCodigoValido(codigo))
			throw new Exception(GestorMensaje.ERROR_CODIGO_RANGO_MEDICO.getMensaje());

		return GestorBaseDeDatos.getInstance().obtenerSituaciones(codigo);
	}
}

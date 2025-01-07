package org.centroMedico.controlador;

import java.util.ArrayList;

import org.centroMedico.servicio.GestorBaseDeDatos;

import org.centroMedico.modelo.Medico;

import org.centroMedico.controlador.ControllerInformesEnfermedades;

public class ControllerInformesEnfermedades {

    public ControllerInformesEnfermedades(){};
	
	/**
	 * Retorna las enfermedades por Medico.
	 * @param codigo Codigo del Medico.
	 * @return Lista de enfermedades por Medico.
	 * @throws Exception
	 */
    public ArrayList<String> listarEnfermedadesPorMedico(String codigo) throws Exception {
		return GestorBaseDeDatos.getInstance().obtenerSituacionesXMedico(new Medico(codigo));
	}
}

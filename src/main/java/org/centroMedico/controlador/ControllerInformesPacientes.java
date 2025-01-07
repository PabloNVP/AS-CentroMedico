package org.centroMedico.controlador;

import java.util.ArrayList;

import org.centroMedico.modelo.Medico;

import org.centroMedico.servicio.GestorBaseDeDatos;

public class ControllerInformesPacientes {
    
    public ControllerInformesPacientes(){};

    public ArrayList<String> listarPacientesPorMedico(String codigo) throws Exception{
		return GestorBaseDeDatos.getInstance().obtenerPacientesXMedico(new Medico(codigo));
	}
}

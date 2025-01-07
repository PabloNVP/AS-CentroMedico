package org.centroMedico.modelo;

import java.util.Arrays;

import org.centroMedico.servicio.GestorMensaje;

public class Medico extends Paciente{

    public static final String[] ESPECIALIDADES = {"Pediatria", "Traumatologia", "Cardiologia"};

    private String especialidad;

    public Medico(String codigo) throws Exception{
        super(codigo);
    }

    public Medico(String codigo, String nombre, String especialidad) throws Exception{
        super(codigo, nombre);
        setEspecialidad(especialidad);
    }

    public String getEspecialidad(){
        return this.especialidad;
    }

    private void setEspecialidad(String especialidad) throws Exception{
        if(esEspecialidadValida(especialidad))
            this.especialidad = especialidad;
        else
            throw new Exception(GestorMensaje.ERROR_ESPECIALIDAD_VALORES_MEDICO.getMensaje());
    }

    private static boolean esEspecialidadValida(String especialidadMedico) {
		return Arrays.stream(ESPECIALIDADES).anyMatch(especialidad -> especialidad.equals(especialidadMedico));
	}

}

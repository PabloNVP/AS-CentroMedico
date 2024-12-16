package org.centroMedico.servicio;

import java.util.Arrays;

public class Validador {
    public static boolean esCodigoValido(String codigo) {
		return codigo.matches("^[1-9][0-9]{0,3}$");
	}

	public static boolean esRangoNombreValido(String nombreMedico, int min, int max) {
		int longitud = nombreMedico.length();
		
		return longitud > min && longitud < max;
	}
	
	public static boolean esFormatoNombreValido(String nombreMedico) {
		return nombreMedico.matches("^[A-Za-záéíóúÁÉÍÓÚ][A-Za-z0-9 áéíóúÁÉÍÓÚ]*$");
	}
	
	public static boolean esEspecialidadValida(String especialidadMedico) {
		return Arrays.stream(ESPECIALIDADES).anyMatch(especialidad -> especialidad.equals(especialidadMedico));
	}
}

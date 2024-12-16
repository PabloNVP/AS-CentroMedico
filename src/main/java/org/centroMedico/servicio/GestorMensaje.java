package org.centroMedico.servicio;

public enum GestorMensaje {
	/**
	 * Mensajes de Errores
	 */
	ERROR_CODIGO_RANGO_MEDICO("El código del medico debe ser un número entre 1 y 9999."),
	ERROR_NOMBRE_RANGO_MEDICO("El nombre del medico debe contener un mínimo de 2 caracteres y un máximo de 20."),
	ERROR_NOMBRE_FORMATO_MEDICO("El nombre del medico debe empezar con una letra y solo puede contener caracteres alfanúmericos, tildes y espacios."),
	ERROR_ESPECIALIDAD_VALORES_MEDICO("La especialidad solo puede ser Pediatría, Traumatología o Cardiología."),
	ERROR_CODIGO_RANGO_PACIENTE("El código del paciente debe ser un número entre 1 y 9999."),
	ERROR_NOMBRE_RANGO_PACIENTE("El nombre del paciente debe contener un mínimo de 2 caracteres y un máximo de 20."),
	ERROR_NOMBRE_FORMATO_PACIENTE("El nombre del paciente debe empezar con una letra y solo puede contener caracteres alfanúmericos, tildes y espacios."),
	ERROR_RANGO_SITUACION("El diagnostico del paciente debe contener un minimo de 10 y un maximo de 200 caracteres."),
	ERROR_FORMATO_SITUACION("El diagnostico del paciente debe empezar con una letra y solo puede contener caracteres alfanúmericos, tildes y espacios."),
	ERROR_ARCHIVO_PACIENTE("No se pudo guardar los datos del paciente en el archivo."),
	ERROR_ARCHIVO_MEDICO("No se pudo guardar los datos del medico en el archivo."),
	ERROR_ARCHIVO_SITUACION("No se pudo guardar el historial en el archivo."),

	/**
	 * Mensajes de Ayuda
	 */ 
	AYUDA_COD_MEDICO("<html> El codigo del medico debe ser un <br>numero entero entre 1 y 9999.</html>"),
	AYUDA_NOM_MEDICO("<html>El nombre del medico debe contener un mínimo de 2 caracteres <br>y un máximo de 20, empezar con una letra y solo puede contener <br>caracteres alfanúmericos, tildes y espacios.</html>"),
	AYUDA_COD_PACIENTE("<html>El codigo del paciente debe ser un <br>numero entero entre 1 y 9999.</html>"),
	AYUDA_NOM_PACIENTE("<html>El nombre del paciente debe contener un mínimo de 2 caracteres <br>y un máximo de 20, empezar con una letra y solo puede contener <br>caracteres alfanúmericos, tildes y espacios.</html>"),	
	AYUDA_SIT_PACIENTE("<html>El diagnostico del paciente debe contener un mínimo de 10 caracteres <br>y un máximo de 200, empezar con una letra y solo puede contener <br>caracteres alfanúmericos, tildes y espacios.</html>");
	
	private final String mensaje;

	private GestorMensaje(String mensaje){
		this.mensaje = mensaje;
	}

	public String getMensaje(){
		return this.mensaje;
	}

	
	
}

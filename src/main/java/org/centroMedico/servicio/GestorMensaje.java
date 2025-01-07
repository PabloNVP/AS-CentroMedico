package org.centroMedico.servicio;

public enum GestorMensaje {

	/**********************
	 * Mensajes de Errores
	 **********************/

	/* Mensajes de la ventana conectar */
	ERROR_LOGIN("Usuario y/o contraseña incorrecta."),
	ERROR_SQL("No pudo crearse la base de datos."),
	
	/* Mensajes de validaciones */
	ERROR_CODIGO_RANGO_MEDICO("El código del medico debe ser un número entre 1 y 9999."),
	ERROR_NOMBRE_RANGO_MEDICO("El nombre del medico debe contener un mínimo de 2 caracteres y un máximo de 20."),
	ERROR_NOMBRE_FORMATO_MEDICO("El nombre del medico debe empezar con una letra y solo puede contener caracteres alfanúmericos, tildes y espacios."),
	ERROR_ESPECIALIDAD_VALORES_MEDICO("La especialidad solo puede ser Pediatría, Traumatología o Cardiología."),
	ERROR_CODIGO_RANGO_PACIENTE("El código del paciente debe ser un número entre 1 y 9999."),
	ERROR_NOMBRE_RANGO_PACIENTE("El nombre del paciente debe contener un mínimo de 2 caracteres y un máximo de 20."),
	ERROR_NOMBRE_FORMATO_PACIENTE("El nombre del paciente debe empezar con una letra y solo puede contener caracteres alfanúmericos, tildes y espacios."),
	ERROR_RANGO_SITUACION("El diagnostico del paciente debe contener un minimo de 10 y un maximo de 200 caracteres."),
	ERROR_FORMATO_SITUACION("El diagnostico del paciente debe empezar con una letra y solo puede contener caracteres alfanúmericos, tildes y espacios."),
	
	/* Mensajes de Bases de Datos */
	ERROR_BD_CREACION("Error al querer crear la base de datos."),
	ERROR_BD_CONFIGURACION("Error al querer configurar la base de datos."),
	ERROR_PACIENTE_CREACION("Error al querer crear la tabla Paciente."),
	ERROR_PACIENTE_INSERTAR("No se pudo guardar los datos del paciente."),
	ERROR_PACIENTE_DUPLICADO("El paciente con ese código ya existe."),
	ERROR_MEDICO_CREACION("Error al querer crear la tabla Medico."),
	ERROR_MEDICO_INSERTAR("No se pudo guardar los datos del medico."),
	ERROR_MEDICO_DUPLICADO("El medico con ese código ya existe."),
	ERROR_SITUACION_CREACION("Error al querer crear la tabla Situacion."),
	ERROR_SITUACION_NO_EXISTEN_CODIGOS("El codigo del paciente y/o del medico no existen."),
	ERROR_INFORMES_SITUACIONES("Error al querer obtener las situaciones de los pacientes."),
	ERROR_INFORMES_PACIENTES("Error al querer obtener los datos de los pacientes."),

	/*********************
	 *  Mensajes de Ayuda
	 *********************/ 
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

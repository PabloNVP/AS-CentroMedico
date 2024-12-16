package org.centroMedico.controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;

import org.centroMedico.modelo.Paciente;
import org.centroMedico.servicio.GestorArchivo;
import org.centroMedico.servicio.GestorMensaje;
import org.centroMedico.servicio.Seguridad;
import org.centroMedico.servicio.Validador;
import org.centroMedico.servicio.GestorArchivo.Archivo;

public class CENTROMEDICO {


	//Especialidades de los Medicos
	public static final String[] ESPECIALIDADES = {"Pediatria", "Traumatologia", "Cardiologia"};

	public static void ingresarPaciente(String codPac, String nomPac) throws Exception{
		// Valida el codigo del paciente.
		if(!Validador.esCodigoValido(codPac)){
			throw new Exception(GestorMensaje.ERROR_CODIGO_RANGO_PACIENTE.getMensaje());
		}
		
		// Valida el Rango del nombre del paciente.
		if(!Validador.esRangoNombreValido(nomPac, 1, 21)) {
			throw new Exception(GestorMensaje.ERROR_NOMBRE_RANGO_PACIENTE.getMensaje());
		}
		
		// Valida el Formato del nombre del paciente.
		if(!Validador.esFormatoNombreValido(nomPac)) {
			throw new Exception(GestorMensaje.ERROR_NOMBRE_FORMATO_PACIENTE.getMensaje());
		}
		
		try {
			GestorArchivo.getInstance().escribirArchivo(Archivo.DATO_PAC_PATH, new Paciente(codPac, nomPac));
		} catch (IOException e) {
			// Envia una excepción en caso de no tener permisos en el archivo.
			throw new Exception(GestorMensaje.ERROR_ARCHIVO_PACIENTE.getMensaje());
		}
	}

	public static void ingresarSituacionPaciente(String codPac, String codMed, String sit) throws Exception {
		// Valida el codigo del paciente.
		if(!Validador.esCodigoValido(codPac)){
			throw new Exception(GestorMensaje.ERROR_CODIGO_RANGO_PACIENTE.getMensaje());
		}
		
		// Valida el codigo del medico.
		if(!Validador.esCodigoValido(codMed)){
			throw new Exception(GestorMensaje.ERROR_CODIGO_RANGO_MEDICO.getMensaje());
		}
		
		// Valida el Rango del nombre del paciente.
		if(!Validador.esRangoNombreValido(sit, 9, 201)) {
			throw new Exception(GestorMensaje.ERROR_RANGO_SITUACION.getMensaje());
		}
		
		// Valida el formato de la situacion.
		if(!Validador.esFormatoNombreValido(sit)) {
			throw new Exception(GestorMensaje.ERROR_FORMATO_SITUACION.getMensaje());
		}
		
		try {
			// Abre el archivo haciendo referencia al final del mismo.
			DataOutputStream archivo = new DataOutputStream(new FileOutputStream(SITU_PAC_PATH, true));

			// Escribe en el archivo el historial encriptado.
			archivo.writeUTF(Seguridad.getInstance().cifrado(codPac));
			archivo.writeUTF(Seguridad.getInstance().cifrado(codMed));
			archivo.writeUTF(Seguridad.getInstance().cifrado(sit));
			
			archivo.close();
		} catch (IOException ioe) {
			// Envia una excepción en caso de no tener permisos en el archivo.
			throw new Exception(GestorMensaje.ERROR_ARCHIVO_SITUACION.getMensaje());
		}
	}

	public static void ingresarMedico(String codmed, String nommed, String espmed) throws Exception{
		// Valida el codigo del medico.
		if(!Validador.esCodigoValido(codmed)){
			throw new Exception(GestorMensaje.ERROR_CODIGO_RANGO_MEDICO.getMensaje());
		}
		
		// Valida el Rango del nombre del medico.
		if(!Validador.esRangoNombreValido(nommed, 1, 21)) {
			throw new Exception(GestorMensaje.ERROR_NOMBRE_RANGO_MEDICO.getMensaje());
		}
		
		// Valida el Formato del nombre del medico.
		if(!Validador.esFormatoNombreValido(nommed)) {
			throw new Exception(GestorMensaje.ERROR_NOMBRE_FORMATO_MEDICO.getMensaje());
		}
		
		// Valida la especialidad del medico.
		if(!Validador.esEspecialidadValida(espmed)) {
			throw new Exception(GestorMensaje.ERROR_ESPECIALIDAD_VALORES_MEDICO.getMensaje());
		}
		
		try {
			// Abre el archivo haciendo referencia al final del mismo.
			DataOutputStream archivo = new DataOutputStream(new FileOutputStream(DATO_MED_PATH, true));
		
			// Escribe en el archivo los datos del medico encriptados.
			archivo.writeUTF(Seguridad.getInstance().cifrado(codmed));
			archivo.writeUTF(Seguridad.getInstance().cifrado(nommed));
			archivo.writeUTF(Seguridad.getInstance().cifrado(espmed));
			
			archivo.close();
		} catch (IOException e) {
			// Envia una excepción en caso de no tener permisos en el archivo.
			throw new Exception(GestorMensaje.ERROR_ARCHIVO_MEDICO.getMensaje());
		} 
	}

	public static ArrayList<String> buscarPacientesPorMedico(String codigo, ArrayList<String> pacientes) throws Exception{
		int sw = 0, sw1 = 0;
		String codp, codme, enfp, codpa, nompa;
		
		try {
			DataInputStream situpac = new DataInputStream(new FileInputStream(SITU_PAC_PATH));
			
			sw = 1;
			while (sw != 0) {
				try {
					codp = Seguridad.getInstance().descifrado(situpac.readUTF());
					codme = Seguridad.getInstance().descifrado(situpac.readUTF());
					enfp = Seguridad.getInstance().descifrado(situpac.readUTF());
	
					// compara el codigo medico de la tabla "datomed" 
					// con el de la tabla "situpac"
					if (codme.equals(codigo)) {
						DataInputStream datopac = new DataInputStream(new FileInputStream(DATO_PAC_PATH));
	
						sw1 = 1;
						while (sw1 != 0) {
							try {
								codpa = Seguridad.getInstance().descifrado(datopac.readUTF());
								nompa = Seguridad.getInstance().descifrado(datopac.readUTF());
	
								// compara el codigo del paciente de la tabla 
								// "situpac" con el codigo del
								// paciente de la tabla "datopac"
								if (codpa.equals(codp)) {
									pacientes.add("   " + nompa);
								}
							} catch (EOFException e) {
								sw1 = 0;
							}
						}
						datopac.close();
					}
				} catch (EOFException e) {
					sw = 0;
				}
			}
			situpac.close();
		} catch(FileNotFoundException e) {
			sw = 0;
		}
		
		return pacientes;
	}
	
	public static ArrayList<String> listarPacientesPorMedico(String codigo) throws Exception{
		
		// Valida el codigo del medico.
		if(!Validador.esCodigoValido(codigo)){
			throw new Exception(GestorMensaje.ERROR_CODIGO_RANGO_MEDICO.getMensaje());
		}
	
		int sw = 0;
		String codm = "", nomm = "", espm;

		// Arreglo de información
		ArrayList<String> pacientes = new ArrayList<String>();
		
		try {
			DataInputStream datomed = new DataInputStream(new FileInputStream(DATO_MED_PATH));

			sw = 1;
			while (sw != 0) {
				try {
					codm = Seguridad.getInstance().descifrado(datomed.readUTF());
					nomm = Seguridad.getInstance().descifrado(datomed.readUTF());
					espm = Seguridad.getInstance().descifrado(datomed.readUTF());
					
					// compara el codigo extraido de la tabla "datomed" con el codigo digitado
					if (codm.equals(codigo)) {
						pacientes.add("Nombre del Medico: " + nomm);
						pacientes.add("Nombre de los pacientes que atiende: ");
						
						pacientes = buscarPacientesPorMedico(codigo, pacientes);
					}
				}catch(EOFException e) {
					sw = 0;
				}
			}
			
			datomed.close();
		} catch (FileNotFoundException e) {
			sw = 0;
		}
		
		return pacientes;
	}
}

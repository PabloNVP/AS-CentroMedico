package org.centroMedico.controlador;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.centroMedico.servicio.GestorMensaje;
import org.centroMedico.servicio.Seguridad;
import org.centroMedico.servicio.Validador;
import org.centroMedico.controlador.ControllerInformesEnfermedades;

public class ControllerInformesEnfermedades {

    public ControllerInformesEnfermedades(){};

    public ArrayList<String> listarEnfermedadesPorMedico(String codigo) throws Exception {
		
		// Valida el codigo del medico.
		if(!Validador.esCodigoValido(codigo)){
			throw new Exception(GestorMensaje.ERROR_CODIGO_RANGO_MEDICO.getMensaje());
		}
		
		// Variables de corte de control
		int sw = 0, sw1 = 0;

		// Variables usadas en la lectura de datos.
		String codm = "", codme = "", enfp = "", nomm = "", espm = "", codp;

		// Arreglo de información
		ArrayList<String> enfermedades = new ArrayList<String>();

		try {
			DataInputStream datomed = new DataInputStream(new FileInputStream(DATO_MED_PATH));
			
			sw1 = 1;
			while (sw1 != 0) {
				try {
					codm = Seguridad.getInstance().descifrado(datomed.readUTF());
					nomm = Seguridad.getInstance().descifrado(datomed.readUTF());
					espm = Seguridad.getInstance().descifrado(datomed.readUTF());

					// compara el codigo digitado
					// con el codigo del medico de la
					// tabla "datomed"
					if (codm.equals(codigo)) {
						enfermedades.add("Nombre del Medico: " + nomm);
						enfermedades.add("Nombre de las enfermedades que atiende: ");
						
						DataInputStream situpac = new DataInputStream(new FileInputStream(SITU_PAC_PATH));

						sw = 1;
						while (sw != 0) {
							try {
								codp = Seguridad.getInstance().descifrado(situpac.readUTF());
								codme = Seguridad.getInstance().descifrado(situpac.readUTF());
								enfp = Seguridad.getInstance().descifrado(situpac.readUTF());

								// compara el codigo del medico
								// de la tabla "datomed"
								// con el codigo del medico en la
								// tabla "situpac"
								if (codme.equals(codigo)) {
									enfermedades.add("   " + enfp);
								}
							} catch (EOFException e) {
								sw = 0;
							}
						}
						situpac.close();
					}
				} catch (EOFException e) {
					sw1 = 0;
				}
			}
			datomed.close();
		} catch (FileNotFoundException e) {
			sw = 0;
		}

		return enfermedades;
	}
}

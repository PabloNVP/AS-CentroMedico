package org.centroMedico.servicio;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.centroMedico.modelo.Paciente;

public class GestorArchivo {
    private static GestorArchivo instancia;

    private DataOutputStream archivo;

    /**
     * Path de archivos
     */
    public enum Archivo{
        DATO_MED_PATH("./datomed.txt"),
        DATO_PAC_PATH("./datopac.txt"),
        SITU_PAC_PATH("./situpac.txt");

        private final String directorio;

        private Archivo(String directorio){
            this.directorio = directorio;
        }

        public String getPath(){
            return this.directorio;
        }
    }

    private GestorArchivo(){};

    public static GestorArchivo getInstance(){
        if(instancia == null)
            instancia = new GestorArchivo();

        return instancia;
    }

    public <T> void escribirArchivo(Archivo path, T objeto) throws Exception{
        if (objeto instanceof Paciente){
            Paciente paciente = (Paciente) objeto;

            // Abre el archivo haciendo referencia al final del mismo.
			this.archivo = new DataOutputStream(new FileOutputStream(Archivo.DATO_PAC_PATH.getPath(), true));

			// Escribe en el archivo los datos del paciente encriptados.
			this.archivo.writeUTF(Seguridad.getInstance().cifrado(paciente.getCodigo()));
			this.archivo.writeUTF(Seguridad.getInstance().cifrado(paciente.getNombre()));

			this.archivo.close();
        }
    }

}

package org.centroMedico.modelo;

import org.centroMedico.servicio.GestorMensaje;

public class Paciente {
    private String codigo;
    private String nombre;

    public Paciente(String codigo) throws Exception{
        this.setCodigo(codigo);
    }

    public Paciente(String codigo, String nombre) throws Exception{
        this.setCodigo(codigo);
        this.setNombre(nombre);
    }

    public String getCodigo(){
        return this.codigo;
    }

    public String getNombre(){
        return this.nombre;
    }

    private void setCodigo(String codigo) throws Exception{
        if(esCodigoValido(codigo))
            this.codigo = codigo;
        else
            throw new Exception(GestorMensaje.ERROR_CODIGO_RANGO_PACIENTE.getMensaje());
    }

    private void setNombre(String nombre) throws Exception{
        if(!esRangoNombreValido(nombre, 1, 21))
            throw new Exception(GestorMensaje.ERROR_NOMBRE_RANGO_PACIENTE.getMensaje());

        if(!esFormatoNombreValido(nombre))
            throw new Exception(GestorMensaje.ERROR_NOMBRE_FORMATO_PACIENTE.getMensaje());

        this.nombre = nombre;
    }

    private boolean esCodigoValido(String codigo) {
		return codigo.matches("^[1-9][0-9]{0,3}$");
	}

	public  boolean esRangoNombreValido(String nombreMedico, int min, int max) {
		int longitud = nombreMedico.length();
		
		return longitud > min && longitud < max;
	}
	
	public boolean esFormatoNombreValido(String nombreMedico) {
		return nombreMedico.matches("^[A-Za-záéíóúÁÉÍÓÚ][A-Za-z0-9 áéíóúÁÉÍÓÚ]*$");
	}
}

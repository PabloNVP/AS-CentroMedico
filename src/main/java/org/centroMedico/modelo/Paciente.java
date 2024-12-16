package org.centroMedico.modelo;

public class Paciente {
    private String codigo;
    private String nombre;

    public Paciente(String codigo, String nombre){
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo(){
        return this.codigo;
    }

    public String getNombre(){
        return this.nombre;
    }


}

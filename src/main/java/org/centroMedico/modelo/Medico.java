package org.centroMedico.modelo;

public class Medico{
    private String codigo;
    private String nombre;
    private Especialidad especialidad;

    public Medico(String codigo, String nombre, Especialidad especialidad){
        this.codigo = codigo;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public String getCodigo(){
        return this.codigo;
    }

    public String getNombre(){
        return this.nombre;
    }

    public Especialidad getEspecialidad(){
        return this.especialidad;
    }

}

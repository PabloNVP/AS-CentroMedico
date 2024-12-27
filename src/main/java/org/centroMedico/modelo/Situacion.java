package org.centroMedico.modelo;

import java.sql.Date;

public class Situacion {
    private int codigoMedico;
    private int codigoPaciente;
    private String descripcion;
    private Date fecha;

    public Situacion(String codigoMedico, String codigoPaciente, String descripcion, Date fecha){
        this.codigoMedico = Integer.parseInt(codigoMedico);
        this.codigoPaciente = Integer.parseInt(codigoMedico);
        this.descripcion = descripcion;
        this.fecha = fecha;
    }
}

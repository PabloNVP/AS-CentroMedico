package org.centroMedico.servicio;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import org.centroMedico.ventana.Ventana;

public class GestorVentanas {
    private Map<Ventana, JFrame> ventanas;

    private static GestorVentanas instancia;

    public static GestorVentanas getInstance(){
        if(instancia == null)
            instancia = new GestorVentanas();

        return instancia;
    }

    private GestorVentanas(){
        this.ventanas = new HashMap<Ventana, JFrame>();
    }    

    public void iniciarVentana(Ventana ventana){
        this.ventanas.put(ventana, ventana.crearVentana());
        this.ventanas.get(ventana).setVisible(true);
    }

    public void cambiarVentana(Ventana ventanaOcultar, Ventana ventanaMostrar){
        this.ventanas.get(ventanaOcultar).setVisible(false);

        if(this.ventanas.get(ventanaMostrar) == null)
            this.iniciarVentana(ventanaMostrar);
        else
            this.ventanas.get(ventanaMostrar).setVisible(true);
    }

    public void finalizarVentana(Ventana ventanaCerrar, Ventana ventanaMostrar){
        this.cerrarVentana(ventanaCerrar);
        this.ventanas.get(ventanaMostrar).setVisible(true);
    }

    public void cerrarVentana(Ventana ventana){
        this.ventanas.get(ventana).dispose();
        this.ventanas.remove(ventana);
    }

    public void limpiarVentanas(){
        this.ventanas.forEach( (nombre, ventana) -> ventana.dispose());
        this.ventanas.clear();
    }
}

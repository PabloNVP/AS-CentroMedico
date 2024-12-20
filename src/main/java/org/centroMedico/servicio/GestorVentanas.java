package org.centroMedico.servicio;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import org.centroMedico.ventana.*;

public class GestorVentanas {
    private Map<String, JFrame> ventanas;

    private static GestorVentanas instancia;

	public final static int ALTO = 640; // Altura de las interfaces
	public final static int ANCHO = 480; // Anchura de las interfaces

	public final static String TITULO = "CENTRO MEDICO"; // Titulo de las interfaces

    public static GestorVentanas getInstance(){
        if(instancia == null)
            instancia = new GestorVentanas();

        return instancia;
    }

    private GestorVentanas(){
        this.ventanas = new HashMap<String, JFrame>();
    }    

    public void iniciarVentana(String nombreVentana){
        switch (nombreVentana) {
            case "conectar":
                ventanas.put("conectar",  new VentanaConectar());
                ventanas.get("conectar").setVisible(true);
                break;
        
            default:
                break;
        }
    }

    public void cambiarVentana(String nombreVentanaOcultar, String nombreVentanaMostrar){
        this.ventanas.get(nombreVentanaOcultar).setVisible(false);
        this.ventanas.get(nombreVentanaMostrar).setVisible(true);
    }

    public void limpiarVentanas(){
        this.ventanas.forEach( (nombre, ventana) -> ventana.dispose());
        this.ventanas.clear();
    }
}

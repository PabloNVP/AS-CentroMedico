
package org.centroMedico;

import org.centroMedico.servicio.GestorVentanas;
import org.centroMedico.ventana.Ventana;

public class App{
    public static void main(String args[]) throws Exception {
        GestorVentanas.getInstance().iniciarVentana(Ventana.CONECTAR);
    }

}

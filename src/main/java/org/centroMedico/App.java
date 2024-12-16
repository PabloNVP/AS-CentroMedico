
package org.centroMedico;

import org.centroMedico.controlador.ControllerVentanas;

public class App{
    public static void main(String args[]) throws Exception {
        ControllerVentanas.getInstance().iniciarVentana("conectar");
        ControllerVentanas.getInstance().limpiarVentanas();
    }

}

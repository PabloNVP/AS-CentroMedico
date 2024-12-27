package org.centroMedico.ventana;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaBase extends JFrame{
	private final static int ALTO = 640; // Altura de las interfaces
	private final static int ANCHO = 480; // Anchura de las interfaces

	private final static String TITULO = "CENTRO MEDICO"; // Titulo de las interfaces
    private JLabel tituloJL = new JLabel(TITULO);

    public VentanaBase(String nombreVentana){
		setSize(ALTO, ANCHO);
		setTitle(TITULO + " - " + nombreVentana);
		setLocationRelativeTo(null);
		setResizable(false);
    }

    public void inicializar(JPanel pantalla){
        pantalla.setLayout(null);

        tituloJL.setBounds(220, 64, 320, 32);
		tituloJL.setFont(new Font("Serif", Font.PLAIN, 22));
        
        pantalla.add(tituloJL);

        setContentPane(pantalla);
    }
    
}

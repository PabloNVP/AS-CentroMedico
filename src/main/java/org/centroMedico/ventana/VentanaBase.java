package org.centroMedico.ventana;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class VentanaBase extends JFrame{
	private final static int ALTO = 640; // Altura de las ventana
	private final static int ANCHO = 480; // Anchura de las ventana

	private final static String TITULO = "CENTRO MEDICO"; // Titulo de las ventana
    private final static String ICONO_PATH = "/logo.png"; // Icono de la ventana

    public VentanaBase(String nombreVentana){
		setSize(ALTO, ANCHO);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(ICONO_PATH)));
		setTitle(TITULO + " - " + nombreVentana);
		setLocationRelativeTo(null);
		setResizable(false);
    }

    public void inicializar(JPanel pantalla){
        pantalla.setLayout(null);

        JLabel tituloJL = new JLabel(TITULO);
        tituloJL.setBounds(220, 48, 320, 32);
		tituloJL.setFont(new Font("Serif", Font.PLAIN, 22));
        
        pantalla.add(tituloJL);

        setContentPane(pantalla);
    }

    
    
}

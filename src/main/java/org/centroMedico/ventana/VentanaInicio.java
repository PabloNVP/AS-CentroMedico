package org.centroMedico.ventana;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.centroMedico.servicio.GestorVentanas;

public class VentanaInicio extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private final String nombreVentana = "Mesa de admisión";
	
	private JLabel tituloJL = new JLabel(GestorVentanas.TITULO);
	private JLabel nombreVentanaJL = new JLabel(nombreVentana);
	private JButton ingresarJB = new JButton("Ingreso de datos");
	private JButton informesJB = new JButton("Informes");
	private JButton salirJB = new JButton("Salir");
	
	public VentanaInicio(){
		JPanel pantalla = new Pantalla();
		
		setSize(GestorVentanas.ALTO, GestorVentanas.ANCHO);
		setTitle(GestorVentanas.TITULO + " - " + nombreVentana);
		add(pantalla);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	private class Pantalla extends JPanel{
		
		private static final long serialVersionUID = 1L;

		public Pantalla() {
			setLayout(null);
			
			tituloJL.setBounds(170, 64, 320, 32);
			tituloJL.setFont(new Font("Serif", Font.PLAIN, 22));
			nombreVentanaJL.setBounds(240, 96, 256, 32);
			nombreVentanaJL.setFont(new Font("Serif", Font.PLAIN, 18));
			
			ingresarJB.setBounds(192, 192, 256, 32);
			informesJB.setBounds(192,  256, 256, 32);
			salirJB.setBounds(192, 320, 256, 32);
			
			addWindowListener(new WindowListener() {

				@Override
				public void windowActivated(WindowEvent e) {}

				@Override
				public void windowClosed(WindowEvent e) {}

				@Override
				public void windowClosing(WindowEvent e) {
					cerrarVentana();
				}

				@Override
				public void windowDeactivated(WindowEvent e) {}

				@Override
				public void windowDeiconified(WindowEvent e) {}

				@Override
				public void windowIconified(WindowEvent e) {}

				@Override
				public void windowOpened(WindowEvent e) {}
							
			});
			
			ingresarJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					GestorVentanas.getInstance().cambiarVentana("inicio", "ingreso");
				}
			});
			
			informesJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					GestorVentanas.getInstance().cambiarVentana("inicio", "informes");
				}
			});
			
			salirJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cerrarVentana();
				}
			});
			
			add(tituloJL);
			add(nombreVentanaJL);
			add(ingresarJB);
			add(informesJB);
			add(salirJB);
		}
	}
	
	private void cerrarVentana() {
		GestorVentanas.getInstance().limpiarVentanas();
	}
}

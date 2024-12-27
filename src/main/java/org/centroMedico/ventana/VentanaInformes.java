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

public class VentanaInformes extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private final String nombreVentana = "Informes";
	
	private JLabel tituloJL = new JLabel(GestorVentanas.TITULO);
	private JLabel nombreVentanaJL = new JLabel(nombreVentana);
	private JButton listarPacientesJB = new JButton("Listado de pacientes por medico");
	private JButton enfermedadesMedicoJB = new JButton("Listado de enfermedades por medico");
	private JButton volverJB = new JButton("Volver");
	
	public VentanaInformes(){
		JPanel pantalla = new Pantalla();
		
		setSize(GestorVentanas.ALTO, GestorVentanas.ANCHO);
		setTitle(GestorVentanas.TITULO + " - " + nombreVentana);
		add(pantalla);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	public class Pantalla extends JPanel{
		
		private static final long serialVersionUID = 1L;

		public Pantalla() {
			setLayout(null);
			
			tituloJL.setBounds(170, 64, 320, 32);
			tituloJL.setFont(new Font("Serif", Font.PLAIN, 22));
			nombreVentanaJL.setBounds(275, 96, 256, 32);
			nombreVentanaJL.setFont(new Font("Serif", Font.PLAIN, 18));
			
			listarPacientesJB.setBounds(162, 192, 304, 32);
			enfermedadesMedicoJB.setBounds(162,  256, 304, 32);
			volverJB.setBounds(162, 320, 304, 32);
			
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
			
			listarPacientesJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					GestorVentanas.getInstance().cambiarVentana("informes", "informesPacientes");
				}
			});
			
			enfermedadesMedicoJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					GestorVentanas.getInstance().cambiarVentana("informes", "informesEnfermedades");
				}
			});
			
			volverJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cerrarVentana();
				}
			});
			
			add(tituloJL);
			add(nombreVentanaJL);
			add(listarPacientesJB);
			add(enfermedadesMedicoJB);
			add(volverJB);
		}
	}
	
	private void cerrarVentana() {
		GestorVentanas.getInstance().cambiarVentana("informes", "inicio");
	}
}

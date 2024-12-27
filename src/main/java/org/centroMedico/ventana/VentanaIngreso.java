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

public class VentanaIngreso extends JFrame{

	private static final long serialVersionUID = 1L;

	private final String nombreVentana = "Ingreso de Pacientes";
	
	private JLabel tituloJL = new JLabel(GestorVentanas.TITULO);
	private JLabel nombreVentanaJL = new JLabel(nombreVentana);
	private JButton datosJB = new JButton("Ingresar datos del Paciente");
	private JButton situacionJB = new JButton("Ingresar situación del Paciente");
	private JButton medicoJB = new JButton("Ingresar datos del Medico");
	private JButton volverJB = new JButton("Volver");
	
	public VentanaIngreso(){
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
			nombreVentanaJL.setBounds(220, 96, 320, 32);
			nombreVentanaJL.setFont(new Font("Serif", Font.PLAIN, 18));
			
			datosJB.setBounds(192, 192, 256, 32);
			situacionJB.setBounds(192,  256, 256, 32);
			medicoJB.setBounds(192, 320, 256, 32);
			volverJB.setBounds(192, 384, 256, 32);
			
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
			
			datosJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					GestorVentanas.getInstance().cambiarVentana("ingreso", "ingresoPaciente");
				}
			});
			
			situacionJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					GestorVentanas.getInstance().cambiarVentana("ingreso", "ingresoSituacion");
				}
			});
			
			medicoJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					GestorVentanas.getInstance().cambiarVentana("ingreso", "ingresoMedico");
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
			add(datosJB);
			add(situacionJB);
			add(medicoJB);
			add(volverJB);
		}
	}
	
	public void cerrarVentana() {
		GestorVentanas.getInstance().cambiarVentana("ingreso", "inicio");
	}
}

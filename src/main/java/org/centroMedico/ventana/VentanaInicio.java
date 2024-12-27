package org.centroMedico.ventana;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.centroMedico.servicio.GestorVentanas;

public class VentanaInicio extends VentanaBase{
	private static final long serialVersionUID = 1L;
	
	private JLabel nombreVentanaJL = new JLabel("Mesa de admisión");
	private JButton ingresarJB = new JButton("Ingreso de datos");
	private JButton informesJB = new JButton("Informes");
	private JButton salirJB = new JButton("Salir");
	
	public VentanaInicio(){
		super("Mesa de admisión");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		inicializar(new Pantalla());
	}
	
	private class Pantalla extends JPanel{
		
		private static final long serialVersionUID = 1L;

		public Pantalla() {
			setLayout(null);
			
			nombreVentanaJL.setBounds(240, 96, 256, 32);
			nombreVentanaJL.setFont(new Font("Serif", Font.PLAIN, 18));
			
			ingresarJB.setBounds(192, 192, 256, 32);
			informesJB.setBounds(192,  256, 256, 32);
			salirJB.setBounds(192, 320, 256, 32);
			
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					cerrarVentana();
				}
			});
			
			ingresarJB.addActionListener(e -> 
				GestorVentanas.getInstance().cambiarVentana(Ventana.INICIO, Ventana.INGRESO));
			
			informesJB.addActionListener(e -> 
				GestorVentanas.getInstance().cambiarVentana(Ventana.INICIO, Ventana.INFORMES));
			
			salirJB.addActionListener(e -> 
				cerrarVentana());
				
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

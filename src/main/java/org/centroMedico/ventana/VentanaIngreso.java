package org.centroMedico.ventana;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.centroMedico.servicio.GestorVentanas;

public class VentanaIngreso extends VentanaBase{
	private static final long serialVersionUID = 1L;
	
	public VentanaIngreso(){
		super("Ingreso de Pacientes");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		inicializar(new JPanel(){
			{
			JLabel nombreVentanaJL = new JLabel("Ingreso de Pacientes");
			nombreVentanaJL.setBounds(220, 96, 320, 32);
			nombreVentanaJL.setFont(new Font("Serif", Font.PLAIN, 18));
			
			JButton datosJB = new JButton("Ingresar datos del Paciente");
			datosJB.setBounds(192, 192, 256, 32);

			JButton situacionJB = new JButton("Ingresar situación del Paciente");
			situacionJB.setBounds(192,  256, 256, 32);

			JButton medicoJB = new JButton("Ingresar datos del Medico");
			medicoJB.setBounds(192, 320, 256, 32);

			JButton volverJB = new JButton("Volver");
			volverJB.setBounds(192, 384, 256, 32);
			
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					cerrarVentana();		
				}		
			});
			
			datosJB.addActionListener(e -> 
				GestorVentanas.getInstance().cambiarVentana(Ventana.INGRESO, Ventana.INGRESO_PACIENTE)
			);
			
			situacionJB.addActionListener(e -> 
				GestorVentanas.getInstance().cambiarVentana(Ventana.INGRESO, Ventana.INGRESO_SITUACION)
			);
			
			medicoJB.addActionListener(e -> 
				GestorVentanas.getInstance().cambiarVentana(Ventana.INGRESO, Ventana.INGRESO_MEDICO)
			);
			
			volverJB.addActionListener(e -> 
				cerrarVentana()
			);
			
			add(nombreVentanaJL);
			add(datosJB);
			add(situacionJB);
			add(medicoJB);
			add(volverJB);
			}
		});
	}
	
	public void cerrarVentana() {
		GestorVentanas.getInstance().cambiarVentana(Ventana.INGRESO, Ventana.INICIO);
	}
}

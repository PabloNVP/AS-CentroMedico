package org.centroMedico.ventana;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.centroMedico.servicio.GestorVentanas;

public class VentanaInformes extends VentanaBase{
	private static final long serialVersionUID = 1L;
	
	public VentanaInformes(){
		super("Informes");
		inicializar(new JPanel(){
			{
			JLabel nombreVentanaJL = new JLabel("Informes");
			nombreVentanaJL.setBounds(275, 96, 256, 32);
			nombreVentanaJL.setFont(new Font("Serif", Font.PLAIN, 18));
			
			JButton listarPacientesJB = new JButton("Listado de pacientes por medico");
			listarPacientesJB.setBounds(162, 192, 304, 32);

			JButton enfermedadesMedicoJB = new JButton("Listado de enfermedades por medico");
			enfermedadesMedicoJB.setBounds(162,  256, 304, 32);

			JButton volverJB = new JButton("Volver");
			volverJB.setBounds(162, 320, 304, 32);
			
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					cerrarVentana();
				}		
			});
			
			listarPacientesJB.addActionListener(e -> 
				GestorVentanas.getInstance().cambiarVentana(Ventana.INFORMES, Ventana.INFORMES_PACIENTES)
			);
			
			enfermedadesMedicoJB.addActionListener(e -> 
				GestorVentanas.getInstance().cambiarVentana(Ventana.INFORMES, Ventana.INFORMES_ENFERMEDADES)
			);
			
			volverJB.addActionListener(e ->
				cerrarVentana()
			);
			
			add(nombreVentanaJL);
			add(listarPacientesJB);
			add(enfermedadesMedicoJB);
			add(volverJB);
			}
		});
	}
	
	private void cerrarVentana() {
		GestorVentanas.getInstance().cambiarVentana(Ventana.INFORMES, Ventana.INICIO);
	}
}

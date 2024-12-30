package org.centroMedico.ventana;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.centroMedico.controlador.ControllerInformesPacientes;
import org.centroMedico.servicio.GestorVentanas;

public class VentanaInformesPacientesXMedico extends VentanaBase{
	private static final long serialVersionUID = 1L;

	private ControllerInformesPacientes controlador = new ControllerInformesPacientes();
	
	private JLabel nombreVentanaJL = new JLabel("Informes de pacientes por medico");
	private JLabel codMedicoJL = new JLabel("Codigo del medico:");
	private JLabel mensajeJL = new JLabel("");
	private JTextField codMedicoJTF = new JTextField();
	private DefaultListModel<String> contenidoDLM = new DefaultListModel<String>(); 
	private JList<String> resultadoJL = new JList<String>(contenidoDLM);
	private JButton buscarJB = new JButton("Buscar");
	private JButton volverJB = new JButton("Volver");
	
	public VentanaInformesPacientesXMedico(){
		super("Informes de pacientes por medico");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		inicializar(new Pantalla());
	}
	
	private class Pantalla extends JPanel{
		
		private static final long serialVersionUID = 1L;

		public Pantalla() {
			nombreVentanaJL.setBounds(165, 96, 360, 32);
			nombreVentanaJL.setFont(new Font("Serif", Font.PLAIN, 18));
			
			codMedicoJL.setBounds(128, 126, 192, 32);
		
			codMedicoJTF.setBounds(284, 130, 128, 24);
			resultadoJL.setBounds(128, 164, 406, 192);
			resultadoJL.setEnabled(false);
			
			mensajeJL.setBounds(140, 370, 420, 24);
			mensajeJL.setForeground(Color.RED);
			
			buscarJB.setBounds(420, 123, 128, 32);
			volverJB.setBounds(192,  412, 256, 32);

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
			
			buscarJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					try {
						contenidoDLM.clear();
						
						ArrayList<String> pacientes = controlador.listarPacientesPorMedico(codMedicoJTF.getText());
		
						if( pacientes.size() == 0)
							contenidoDLM.add(0,"No existe ningún medico con ese codigo.");
						else 
							contenidoDLM.addAll(pacientes);
							
						mensajeJL.setText("");
					}catch(Exception e) {
						mensajeJL.setText(e.getMessage());
						codMedicoJTF.setText("");
					}
				}
			});
			
			volverJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cerrarVentana();
				}
			});
			
			add(codMedicoJL);
			add(codMedicoJTF);
			add(resultadoJL);
			add(mensajeJL);
			add(nombreVentanaJL);
			add(buscarJB);
			add(volverJB);
		}
	}
	
	private void resetearVentana() {
		contenidoDLM.clear();
		codMedicoJTF.setText("");
		mensajeJL.setText("");
	}
	
	public void cerrarVentana() {
		resetearVentana();
		GestorVentanas.getInstance().cambiarVentana(Ventana.INFORMES_PACIENTES, Ventana.INFORMES);
	}
}

package org.centroMedico.ventana;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
	
	public VentanaInformesPacientesXMedico(){
		super("Informes de pacientes por medico");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		inicializar(new JPanel(){
			{
			JLabel nombreVentanaJL = new JLabel("Informes de pacientes por medico");
			nombreVentanaJL.setBounds(165, 96, 360, 32);
			nombreVentanaJL.setFont(new Font("Serif", Font.PLAIN, 18));
			
			JLabel codMedicoJL = new JLabel("Codigo del medico:");
			codMedicoJL.setBounds(128, 126, 192, 32);
		
			JTextField codMedicoJTF = new JTextField();
			codMedicoJTF.setBounds(284, 130, 128, 24);

			DefaultListModel<String> contenidoDLM = new DefaultListModel<String>(); 
			JList<String> resultadoJL = new JList<String>(contenidoDLM);
			resultadoJL.setBounds(128, 164, 406, 192);
			resultadoJL.setEnabled(false);
			
			JLabel mensajeJL = new JLabel("");
			mensajeJL.setBounds(140, 370, 420, 24);
			mensajeJL.setForeground(Color.RED);

	 		JButton buscarJB = new JButton("Buscar");
			buscarJB.setBounds(420, 123, 128, 32);

			JButton volverJB = new JButton("Volver");
			volverJB.setBounds(192,  412, 256, 32);

			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					cerrarVentana();
				}
			});
				
			buscarJB.addActionListener(e -> {
				try {
					contenidoDLM.clear();
					
					ArrayList<String> pacientes = controlador.listarPacientesPorMedico(codMedicoJTF.getText());
	
					if( pacientes.size() == 0)
						contenidoDLM.add(0,"No existe ningún medico con ese codigo.");
					else 
						contenidoDLM.addAll(pacientes);
						
					mensajeJL.setText("");
				}catch(Exception ex) {
					mensajeJL.setText(ex.getMessage());
					codMedicoJTF.setText("");
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
		});
	}
	
	public void cerrarVentana() {
		GestorVentanas.getInstance().finalizarVentana(Ventana.INFORMES_PACIENTES, Ventana.INFORMES);
	}
}

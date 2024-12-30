package org.centroMedico.ventana;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.centroMedico.controlador.ControllerIngresarPaciente;
import org.centroMedico.servicio.GestorMensaje;
import org.centroMedico.servicio.GestorVentanas;

public class VentanaIngresoPaciente extends VentanaBase{

	private static final long serialVersionUID = 1L;

	private ControllerIngresarPaciente controlador = new ControllerIngresarPaciente();
	
	private final String INGRESAR_NUEVO = "Se han guardado los datos del paciente correctamente, ¿Desea ingresar otro?";
	
	public VentanaIngresoPaciente(){
		super("Ingresar datos del paciente");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		inicializar(new JPanel() {
			{

			JLabel nombreVentanaJL = new JLabel("Ingresar datos del paciente");
			nombreVentanaJL.setBounds(180, 96, 320, 32);
			nombreVentanaJL.setFont(new Font("Serif", Font.PLAIN, 18));
			
			JLabel codPacienteJL = new JLabel("Codigo del paciente:");
			codPacienteJL.setBounds(128, 160, 192, 32);

			JLabel nomPacienteJL = new JLabel("Nombre del paciente:");
			nomPacienteJL.setBounds(128, 192, 192, 32);

			JTextField codPacienteJTF = new JTextField();
			codPacienteJTF.setBounds(288, 165, 192, 24);

			JTextField nomPacienteJPF = new JTextField();
			nomPacienteJPF.setBounds(288, 197, 192, 24);

			JLabel mensajeJL = new JLabel("");
			mensajeJL.setBounds(140, 245, 360, 32);
			mensajeJL.setForeground(Color.RED);
			
			JLabel codPacienteAyuda = new JLabel("?");
			codPacienteAyuda.setBounds(490, 168, 16, 16);
			codPacienteAyuda.setToolTipText(GestorMensaje.AYUDA_COD_PACIENTE.getMensaje());
			codPacienteAyuda.setBackground(Color.LIGHT_GRAY);
			codPacienteAyuda.setHorizontalAlignment(JLabel.CENTER);
			codPacienteAyuda.setOpaque(true);
			
			JLabel nomPacienteAyuda = new JLabel("?");
			nomPacienteAyuda.setBounds(490, 200, 16, 16);
			nomPacienteAyuda.setToolTipText(GestorMensaje.AYUDA_NOM_PACIENTE.getMensaje());
			nomPacienteAyuda.setBackground(Color.LIGHT_GRAY);
			nomPacienteAyuda.setHorizontalAlignment(JLabel.CENTER);
			nomPacienteAyuda.setOpaque(true);
			
			JButton ingresarJB = new JButton("Ingresar");
			ingresarJB.setBounds(192, 304, 256, 32);

			JButton volverJB = new JButton("Volver");
			volverJB.setBounds(192,  356, 256, 32);

			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					cerrarVentana();
				}
			});
			
			ingresarJB.addActionListener(e -> {
				String codigoPaciente = codPacienteJTF.getText();
				String nombrePaciente = nomPacienteJPF.getText();
				
				try {
					controlador.ingresarPaciente(codigoPaciente, nombrePaciente);
					
					int opcion = JOptionPane.showConfirmDialog(null, INGRESAR_NUEVO, "Ingresar datos del paciente", JOptionPane.YES_NO_OPTION);
					
					if(opcion == JOptionPane.NO_OPTION) {
						cerrarVentana();
					}
					
					codPacienteJTF.setText("");
					nomPacienteJPF.setText("");
					mensajeJL.setText("");

				} catch (Exception ex) {
					mensajeJL.setText("<html>" + ex.getMessage() + "</html>");
				}
			});
			
			volverJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cerrarVentana();
				}
			});
			
			add(codPacienteJL);
			add(nomPacienteJL);
			add(codPacienteJTF);
			add(nomPacienteJPF);
			add(mensajeJL);
			add(codPacienteAyuda);
			add(nomPacienteAyuda);
			add(nombreVentanaJL);
			add(ingresarJB);
			add(volverJB);
			}
		});
	}

	private void cerrarVentana() {
		GestorVentanas.getInstance().finalizarVentana(Ventana.INGRESO_PACIENTE, Ventana.INGRESO);
	}
}

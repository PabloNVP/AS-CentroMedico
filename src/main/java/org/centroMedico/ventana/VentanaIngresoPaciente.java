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

import org.centroMedico.controlador.CENTROMEDICO;
import org.centroMedico.servicio.GestorMensaje;
import org.centroMedico.servicio.GestorVentanas;

public class VentanaIngresoPaciente extends VentanaBase{

	private static final long serialVersionUID = 1L;
	
	private final String INGRESAR_NUEVO = "Se han guardado los datos del paciente correctamente, ¿Desea ingresar otro?";
	
	private JLabel nombreVentanaJL = new JLabel("Ingresar datos del paciente");
	private JLabel codPacienteJL = new JLabel("Codigo del paciente:");
	private JLabel nomPacienteJL = new JLabel("Nombre del paciente:");
	private JLabel mensajeJL = new JLabel("");
	private JLabel codPacienteAyuda = new JLabel("?");
	private JLabel nomPacienteAyuda = new JLabel("?");
	private JTextField codPacienteJTF = new JTextField();
	private JTextField nomPacienteJPF = new JTextField();
	private JButton ingresarJB = new JButton("Ingresar");
	private JButton volverJB = new JButton("Volver");
	
	public VentanaIngresoPaciente(){
		super("Ingresar datos del paciente");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		inicializar(new Pantalla());
	}
	
	private class Pantalla extends JPanel{
		
		private static final long serialVersionUID = 1L;

		public Pantalla() {
			nombreVentanaJL.setBounds(180, 96, 320, 32);
			nombreVentanaJL.setFont(new Font("Serif", Font.PLAIN, 18));
			
			codPacienteJL.setBounds(128, 160, 192, 32);
			nomPacienteJL.setBounds(128, 192, 192, 32);
			
			codPacienteJTF.setBounds(288, 165, 192, 24);
			nomPacienteJPF.setBounds(288, 197, 192, 24);
			
			mensajeJL.setBounds(140, 245, 360, 32);
			mensajeJL.setForeground(Color.RED);
			
			codPacienteAyuda.setBounds(490, 168, 16, 16);
			codPacienteAyuda.setToolTipText(GestorMensaje.AYUDA_COD_PACIENTE.getMensaje());
			codPacienteAyuda.setBackground(Color.LIGHT_GRAY);
			codPacienteAyuda.setHorizontalAlignment(JLabel.CENTER);
			codPacienteAyuda.setOpaque(true);
			
			nomPacienteAyuda.setBounds(490, 200, 16, 16);
			nomPacienteAyuda.setToolTipText(GestorMensaje.AYUDA_NOM_PACIENTE.getMensaje());
			nomPacienteAyuda.setBackground(Color.LIGHT_GRAY);
			nomPacienteAyuda.setHorizontalAlignment(JLabel.CENTER);
			nomPacienteAyuda.setOpaque(true);
			
			ingresarJB.setBounds(192, 304, 256, 32);
			volverJB.setBounds(192,  356, 256, 32);

			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					cerrarVentana();
				}
			});
			
			ingresarJB.addActionListener(e -> {
				String codPac = codPacienteJTF.getText();
				String nomPac = nomPacienteJPF.getText();
				
				try {
					CENTROMEDICO.ingresarPaciente(codPac,nomPac);
					
					int opcion = JOptionPane.showConfirmDialog(null, INGRESAR_NUEVO, "Ingresar datos del paciente", JOptionPane.YES_NO_OPTION);
					
					if(opcion == JOptionPane.NO_OPTION) {
						cerrarVentana();
					}
					
					resetearVentana();
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
	}
	
	private void resetearVentana() {
		codPacienteJTF.setText("");
		nomPacienteJPF.setText("");
		mensajeJL.setText("");
	}
	
	private void cerrarVentana() {
		resetearVentana();
		GestorVentanas.getInstance().cambiarVentana(Ventana.INGRESO_PACIENTE, Ventana.INGRESO);
	}
}

package org.centroMedico.ventana;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.centroMedico.controlador.CENTROMEDICO;
import org.centroMedico.servicio.GestorMensaje;
import org.centroMedico.servicio.GestorVentanas;
import org.centroMedico.servicio.Validador;

public class VentanaIngresoMedico extends VentanaBase{

	private static final long serialVersionUID = 1L;

	private final String INGRESAR_NUEVO = "Se han guardado los datos del Medico correctamente, ¿Desea ingresar otro?";
	
	private JLabel nombreVentanaJL = new JLabel("Ingresar datos del medico");
	private JLabel codMedicoJL = new JLabel("Codigo del medico:");
	private JLabel nomMedicoJL = new JLabel("Nombre del medico:");
	private JLabel espMedicoJL = new JLabel("Especialización del medico:");
	private JLabel codMedicoAyuda = new JLabel("?");
	private JLabel nomMedicoAyuda = new JLabel("?");
	private JLabel mensajeJL = new JLabel("");
	private JTextField codMedicoJTF = new JTextField();
	private JTextField nomMedicoJTF = new JTextField();
	private JComboBox<String> espMedicoJTF = new JComboBox<String>(Validador.ESPECIALIDADES);
	private JButton ingresarJB = new JButton("Ingresar");
	private JButton volverJB = new JButton("Volver");
	
	public VentanaIngresoMedico(){
		super("Ingresar datos del medico");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// Declara el tiempo de respuesta del mouse sobre la etiqueta de ayuda.
		javax.swing.ToolTipManager.sharedInstance().setInitialDelay(10);
		inicializar(new Pantalla());
	}

	private static void mostrarMensaje(JLabel label, String mensaje) {
		label.setText("<html>" + mensaje + "</html>");
	}
	
	
	private class Pantalla extends JPanel{
		
		private static final long serialVersionUID = 1L;

		public Pantalla() {
			nombreVentanaJL.setBounds(190, 96, 320, 32);
			nombreVentanaJL.setFont(new Font("Serif", Font.PLAIN, 18));
			
			codMedicoJL.setBounds(128, 160, 192, 32);
			nomMedicoJL.setBounds(128, 192, 192, 32);
			espMedicoJL.setBounds(92, 224, 224, 32);
			
			codMedicoJTF.setBounds(288, 165, 192, 24);
			nomMedicoJTF.setBounds(288, 197, 192, 24);
			espMedicoJTF.setBounds(288, 229, 192, 24);
			
			codMedicoAyuda.setBounds(490, 168, 16, 16);
			codMedicoAyuda.setToolTipText(GestorMensaje.AYUDA_COD_MEDICO.getMensaje());
			codMedicoAyuda.setBackground(Color.LIGHT_GRAY);
			codMedicoAyuda.setHorizontalAlignment(JLabel.CENTER);
			codMedicoAyuda.setOpaque(true);
			
			nomMedicoAyuda.setBounds(490, 200, 16, 16);
			nomMedicoAyuda.setToolTipText(GestorMensaje.AYUDA_NOM_MEDICO.getMensaje());
			nomMedicoAyuda.setBackground(Color.LIGHT_GRAY);
			nomMedicoAyuda.setHorizontalAlignment(JLabel.CENTER);
			nomMedicoAyuda.setOpaque(true);
			
			mensajeJL.setBounds(100, 260, 460, 32);
			mensajeJL.setForeground(Color.RED);
			
			ingresarJB.setBounds(192, 304, 256, 32);
			volverJB.setBounds(192,  356, 256, 32);
			
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					cerrarVentana();		
				}
			});

			ingresarJB.addActionListener(e -> {
					try {
						String codigoMedico = codMedicoJTF.getText();
						String nombreMedico = nomMedicoJTF.getText();
						String especialidadMedico = Validador.ESPECIALIDADES[espMedicoJTF.getSelectedIndex()];
						
						CENTROMEDICO.ingresarMedico(codigoMedico, nombreMedico, especialidadMedico);
							
						int opcion = JOptionPane.showConfirmDialog(null, INGRESAR_NUEVO, "Ingresar datos del medico", JOptionPane.YES_NO_OPTION);
						
						if(opcion == JOptionPane.NO_OPTION) {
							cerrarVentana();
						}
							
						resetearVentana();
					} catch(Exception ex) {
						mostrarMensaje(mensajeJL, ex.getMessage());
					}
				}
			);
			
			volverJB.addActionListener(e -> {
					resetearVentana();
					cerrarVentana();
				}
			);
			
			add(codMedicoJL);
			add(nomMedicoJL);
			add(espMedicoJL);
			add(codMedicoJTF);
			add(nomMedicoJTF);
			add(espMedicoJTF);
			add(codMedicoAyuda);
			add(nomMedicoAyuda);
			add(mensajeJL);
			add(nombreVentanaJL);
			add(ingresarJB);
			add(volverJB);
		}
	}
	
	private void resetearVentana() {
		codMedicoJTF.setText("");
		nomMedicoJTF.setText("");
		mensajeJL.setText("");
	}
	
	private void cerrarVentana() {
		resetearVentana();
		GestorVentanas.getInstance().cambiarVentana(Ventana.INGRESO_MEDICO, Ventana.INGRESO);
	}
}

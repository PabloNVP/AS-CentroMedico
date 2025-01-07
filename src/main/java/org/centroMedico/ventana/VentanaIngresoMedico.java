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
import javax.swing.SwingConstants;

import org.centroMedico.controlador.ControllerIngresarMedico;
import org.centroMedico.modelo.Medico;
import org.centroMedico.servicio.GestorMensaje;
import org.centroMedico.servicio.GestorVentanas;

public class VentanaIngresoMedico extends VentanaBase{
	private static final long serialVersionUID = 1L;

	ControllerIngresarMedico controlador = new ControllerIngresarMedico();

	private final String INGRESAR_NUEVO = "Se han guardado los datos del Medico correctamente, ¿Desea ingresar otro?";
	
	
	private JButton ingresarJB = new JButton("Ingresar");
	private JButton volverJB = new JButton("Volver");
	
	public VentanaIngresoMedico(){
		super("Ingresar datos del medico");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// Declara el tiempo de respuesta del mouse sobre la etiqueta de ayuda.
		javax.swing.ToolTipManager.sharedInstance().setInitialDelay(5);
		inicializar(new JPanel(){
			{
			JLabel nombreVentanaJL = new JLabel("Ingresar datos del medico");
			nombreVentanaJL.setBounds(190, 96, 320, 32);
			nombreVentanaJL.setFont(new Font("Serif", Font.PLAIN, 18));
			
			JLabel codMedicoJL = new JLabel("Codigo del medico:");
			codMedicoJL.setBounds(128, 160, 192, 32);

			JLabel nomMedicoJL = new JLabel("Nombre del medico:");
			nomMedicoJL.setBounds(128, 192, 192, 32);

			JLabel espMedicoJL = new JLabel("Especialización del medico:");
			espMedicoJL.setBounds(92, 224, 224, 32);
			
			JTextField codMedicoJTF = new JTextField();
			codMedicoJTF.setBounds(288, 165, 192, 24);

			JTextField nomMedicoJTF = new JTextField();
			nomMedicoJTF.setBounds(288, 197, 192, 24);

			JComboBox<String> espMedicoJTF = new JComboBox<String>(Medico.ESPECIALIDADES);
			espMedicoJTF.setBounds(288, 229, 192, 24);
			
			JLabel codMedicoAyuda = new JLabel("?");
			codMedicoAyuda.setBounds(490, 168, 16, 16);
			codMedicoAyuda.setToolTipText(GestorMensaje.AYUDA_COD_MEDICO.getMensaje());
			codMedicoAyuda.setBackground(Color.LIGHT_GRAY);
			codMedicoAyuda.setHorizontalAlignment(JLabel.CENTER);
			codMedicoAyuda.setOpaque(true);
			
			JLabel nomMedicoAyuda = new JLabel("?");
			nomMedicoAyuda.setBounds(490, 200, 16, 16);
			nomMedicoAyuda.setToolTipText(GestorMensaje.AYUDA_NOM_MEDICO.getMensaje());
			nomMedicoAyuda.setBackground(Color.LIGHT_GRAY);
			nomMedicoAyuda.setHorizontalAlignment(JLabel.CENTER);
			nomMedicoAyuda.setOpaque(true);
			
			JLabel mensajeJL = new JLabel("");
			mensajeJL.setBounds(100, 260, 460, 32);
			mensajeJL.setHorizontalAlignment(SwingConstants.CENTER);
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
					String especialidadMedico = Medico.ESPECIALIDADES[espMedicoJTF.getSelectedIndex()];
					
					controlador.ingresarMedico(codigoMedico, nombreMedico, especialidadMedico);
						
					int opcion = JOptionPane.showConfirmDialog(null, INGRESAR_NUEVO, "Ingresar datos del medico", JOptionPane.YES_NO_OPTION);
					
					if(opcion == JOptionPane.NO_OPTION) {
						cerrarVentana();
					}
						
					codMedicoJTF.setText("");
					nomMedicoJTF.setText("");
					mensajeJL.setText("");

				} catch(Exception ex) {
					mensajeJL.setText("<html>" + ex.getMessage() + "</html>");
				}
			});
			
			volverJB.addActionListener(e -> {
				cerrarVentana();
			});
			
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
		});
	}
	
	private void cerrarVentana() {
		GestorVentanas.getInstance().finalizarVentana(Ventana.INGRESO_MEDICO, Ventana.INGRESO);
	}
}

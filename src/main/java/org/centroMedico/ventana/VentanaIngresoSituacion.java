package org.centroMedico.ventana;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.centroMedico.controlador.ControllerIngresarSituacion;
import org.centroMedico.servicio.GestorMensaje;
import org.centroMedico.servicio.GestorVentanas;

public class VentanaIngresoSituacion extends VentanaBase{
	private static final long serialVersionUID = 1L;

	private ControllerIngresarSituacion controlador = new ControllerIngresarSituacion();
	
	private final String INGRESAR_NUEVO = "Se ha guardado el historial correctamente, ¿Desea ingresar otro?";
	
	public VentanaIngresoSituacion(){
		super("Ingresar situación del paciente");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		inicializar(new JPanel(){
			{
			JLabel nombreVentanaJL = new JLabel("Ingresar situación del paciente");
			nombreVentanaJL.setBounds(180, 96, 360, 32);
			nombreVentanaJL.setFont(new Font("Serif", Font.PLAIN, 18));
			
			JLabel codPacienteJL = new JLabel("Codigo del paciente:");
			codPacienteJL.setBounds(128, 160, 192, 32);

			JLabel codMedicoJL = new JLabel("Codigo del medico:");
			codMedicoJL.setBounds(128, 192, 192, 32);

			JLabel sitPacienteJL = new JLabel("Diagnostico del paciente:");
			sitPacienteJL.setBounds(96, 232, 192, 32);
			
			JTextField codPacienteJTF = new JTextField();
			codPacienteJTF.setBounds(288, 165, 192, 24);

			JTextField codMedicoJTF = new JTextField();
			codMedicoJTF.setBounds(288, 197, 192, 24);

			JTextArea sitPacienteJTA = new JTextArea(5, 4);
			sitPacienteJTA.setLineWrap(true);

			JScrollPane sitPacienteJSP = new JScrollPane(sitPacienteJTA);
			sitPacienteJSP.setBounds(288, 224, 192, 64);

			JLabel mensajeJL = new JLabel("");
			mensajeJL.setBounds(140, 300, 400, 32);
			mensajeJL.setHorizontalAlignment(SwingConstants.CENTER);
			mensajeJL.setForeground(Color.RED);
			
			JLabel codPacienteAyuda = new JLabel("?");
			codPacienteAyuda.setBounds(490, 168, 16, 16);
			codPacienteAyuda.setToolTipText(GestorMensaje.AYUDA_COD_PACIENTE.getMensaje());
			codPacienteAyuda.setBackground(Color.LIGHT_GRAY);
			codPacienteAyuda.setHorizontalAlignment(JLabel.CENTER);
			codPacienteAyuda.setOpaque(true);
			
			JLabel codMedicoAyuda = new JLabel("?");
			codMedicoAyuda.setBounds(490, 200, 16, 16);
			codMedicoAyuda.setToolTipText(GestorMensaje.AYUDA_COD_MEDICO.getMensaje());
			codMedicoAyuda.setBackground(Color.LIGHT_GRAY);
			codMedicoAyuda.setHorizontalAlignment(JLabel.CENTER);
			codMedicoAyuda.setOpaque(true);
			
			JLabel sitPacienteAyuda = new JLabel("?");
			sitPacienteAyuda.setBounds(490, 250, 16, 16);
			sitPacienteAyuda.setToolTipText(GestorMensaje.AYUDA_SIT_PACIENTE.getMensaje());
			sitPacienteAyuda.setBackground(Color.LIGHT_GRAY);
			sitPacienteAyuda.setHorizontalAlignment(JLabel.CENTER);
			sitPacienteAyuda.setOpaque(true);
			
			JButton ingresarJB = new JButton("Ingresar");
			ingresarJB.setBounds(192, 340, 256, 32);

			JButton volverJB = new JButton("Volver");
			volverJB.setBounds(192,  388, 256, 32);
			
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					cerrarVentana();
				}		
			});

			ingresarJB.addActionListener(e -> {
				String codPaciente = codPacienteJTF.getText();
				String codMedico = codMedicoJTF.getText();
				String situacion = sitPacienteJTA.getText();
				
				try {
					controlador.ingresarSituacionPaciente(codPaciente, codMedico, situacion);
				
					int opcion = JOptionPane.showConfirmDialog(null, INGRESAR_NUEVO, "Ingresar situación del paciente", JOptionPane.YES_NO_OPTION);
					
					if(opcion == JOptionPane.NO_OPTION) {
						cerrarVentana();
					}
					
					codPacienteJTF.setText("");
					codMedicoJTF.setText("");
					sitPacienteJTA.setText("");
					mensajeJL.setText("");
				}catch(Exception ex){
					mensajeJL.setText("<html>" + ex.getMessage() + "</html>");
				}
			});
			
			volverJB.addActionListener(e -> 
				cerrarVentana()
			);
			
			add(codPacienteJL);
			add(codMedicoJL);
			add(sitPacienteJL);
			add(codPacienteJTF);
			add(codMedicoJTF);
			add(sitPacienteJSP);
			add(mensajeJL);
			add(codPacienteAyuda);
			add(codMedicoAyuda);
			add(sitPacienteAyuda);
			add(nombreVentanaJL);
			add(ingresarJB);
			add(volverJB);
			}
		});
	}

	private void cerrarVentana() {
		GestorVentanas.getInstance().finalizarVentana(Ventana.INGRESO_SITUACION, Ventana.INGRESO);
	}
}

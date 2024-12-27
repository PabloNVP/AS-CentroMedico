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

import org.centroMedico.controlador.ControllerInformesEnfermedades;
import org.centroMedico.servicio.GestorVentanas;

public class VentanaInformesEnfermedadesXMedico extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private static VentanaInformesEnfermedadesXMedico instancia;

	private final String nombreVentana = "Informes de enfermedades por medico";
	
	private JLabel tituloJL = new JLabel(GestorVentanas.TITULO);
	private JLabel nombreVentanaJL = new JLabel(nombreVentana);
	private JLabel codMedicoJL = new JLabel("Codigo del medico:");
	private JLabel mensajeJL = new JLabel("");
	private JTextField codMedicoJTF = new JTextField();
	private DefaultListModel<String> contenidoDLM = new DefaultListModel<String>(); 
	private JList<String> resultadoJL = new JList<String>(contenidoDLM);
	private JButton buscarJB = new JButton("Buscar");
	private JButton volverJB = new JButton("Volver");

	
	private ControllerInformesEnfermedades controlador = new ControllerInformesEnfermedades();
	
	private VentanaInformesEnfermedadesXMedico(){
		JPanel pantalla = new Pantalla();
		
		setSize(GestorVentanas.ALTO, GestorVentanas.ANCHO);
		setTitle(GestorVentanas.TITULO + " - " + nombreVentana);
		add(pantalla);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	public static VentanaInformesEnfermedadesXMedico getInstancia() {
		if(instancia == null)
			instancia = new VentanaInformesEnfermedadesXMedico();
		
		return instancia;
	}
	
	private class Pantalla extends JPanel{
		
		private static final long serialVersionUID = 1L;

		public Pantalla() {
			setLayout(null);
			
			tituloJL.setBounds(170, 64, 320, 32);
			tituloJL.setFont(new Font("Serif", Font.PLAIN, 22));
			nombreVentanaJL.setBounds(150, 96, 360, 32);
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
						
						ArrayList<String> enfermedades = controlador.listarEnfermedadesPorMedico(codMedicoJTF.getText());
		
						if( enfermedades.size() == 0)
							contenidoDLM.add(0,"No existe ningún medico con ese codigo.");
						else 
							contenidoDLM.addAll(enfermedades);
							
						mensajeJL.setText("");
					}catch(Exception e) {
						mensajeJL.setText(e.getMessage());
						codMedicoJTF.setText("");
					}
				}
			});
			
			volverJB.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					cerrarVentana();
				}
			});
			
			add(codMedicoJL);
			add(codMedicoJTF);
			add(resultadoJL);
			add(mensajeJL);
			add(tituloJL);
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
	
	private void cerrarVentana() {
		resetearVentana();
		GestorVentanas.getInstance().cambiarVentana("informesEnfermedades", "informes");
	}
}

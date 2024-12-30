package org.centroMedico.ventana;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;

import javax.security.auth.login.LoginException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.centroMedico.servicio.GestorBaseDeDatos;
import org.centroMedico.servicio.GestorMensaje;
import org.centroMedico.servicio.GestorVentanas;
import org.centroMedico.controlador.ControllerConectar;

public class VentanaConectar extends VentanaBase{
	private static final long serialVersionUID = 1L;
	
	private ControllerConectar controlador = new ControllerConectar();
	
	public VentanaConectar(){
		super("Conectar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inicializar(new JPanel(){
			{
			JLabel nombreVentanaJL = new JLabel("Conectar");
			JLabel usuarioJL = new JLabel("Usuario:");
			JLabel contrasenaJL = new JLabel("Contraseña:");
			JLabel mensajeJL = new JLabel("");
			JTextField usuarioJTF = new JTextField();
			JPasswordField contrasenaJPF = new JPasswordField();
			JButton ingresarJB = new JButton("Ingresar");
			JButton cerrarJB = new JButton("Cerrar");

			nombreVentanaJL.setBounds(275, 96, 256, 32);
			nombreVentanaJL.setFont(new Font("Serif", Font.PLAIN, 18));
			usuarioJL.setBounds(208, 160, 128, 32);
			contrasenaJL.setBounds(192, 192, 96, 32);
			
			usuarioJTF.setBounds(288, 165, 144, 24);
			contrasenaJPF.setBounds(288, 197, 144, 24);
			contrasenaJPF.setEchoChar('*');
			
			mensajeJL.setBounds(220, 245, 256, 24);
			mensajeJL.setForeground(Color.RED);
			
			ingresarJB.setBounds(192, 304, 256, 32);
			cerrarJB.setBounds(192,  356, 256, 32);

			ingresarJB.addActionListener(e -> {
				try {
					controlador.puedeIngresarUsuario(usuarioJTF.getText(), String.valueOf(contrasenaJPF.getPassword()));
					GestorBaseDeDatos.getInstance().iniciarBaseDeDatos();
					GestorVentanas.getInstance().cerrarVentana(Ventana.CONECTAR);
					GestorVentanas.getInstance().iniciarVentana(Ventana.INICIO);
				}catch(LoginException ex){
					mensajeJL.setText(GestorMensaje.ERROR_LOGIN.getMensaje());
				}catch(Exception ex) {
					mensajeJL.setText(GestorMensaje.ERROR_SQL.getMensaje());
				}
			});
			
			cerrarJB.addActionListener(e -> 
				GestorVentanas.getInstance().cerrarVentana(Ventana.CONECTAR)
			);
			
			add(usuarioJL);
			add(contrasenaJL);
			add(usuarioJTF);
			add(contrasenaJPF);
			add(mensajeJL);
			add(nombreVentanaJL);
			add(ingresarJB);
			add(cerrarJB);
			}
		});
	}
}

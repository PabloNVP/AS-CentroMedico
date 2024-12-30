package org.centroMedico.controlador;

import javax.security.auth.login.LoginException;

public class ControllerConectar {
    private static final String USUARIO = "Admin";
	private static final Integer PASSWORD = -1165458038; //centroMedico2020

    public ControllerConectar(){};

    /**
     * Verificación de usuario y contraseña correctos.
     * @param {String} Nombre de usuario.
     * @param {String} Contraseña del usuario.
     * @return {void}
     */
    public void puedeIngresarUsuario(String usuario, String contrasena) throws LoginException{
		if( !USUARIO.equals(usuario) || !PASSWORD.equals(contrasena.hashCode())){
            throw new LoginException();
        }
	}
}

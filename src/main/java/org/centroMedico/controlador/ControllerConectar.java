package org.centroMedico.controlador;

import javax.security.auth.login.LoginException;

public class ControllerConectar {
    private static final String USUARIO = "Admin";
	private static final Integer PASSWORD = -1165458038;
    
    public ControllerConectar(){};

    /**
     * Verificación de usuario y contraseña.
     * @param usuario Nombre de usuario.
     * @param contrasena Contraseña del usuario.
     * @return
     * @throws LoginException
     */
    public void puedeIngresarUsuario(String usuario, String contrasena) throws LoginException{
		if( !USUARIO.equals(usuario) || !PASSWORD.equals(contrasena.hashCode()))
            throw new LoginException();
	}
}

package org.centroMedico.controlador;

public class ControllerConectar {
    private static final String USUARIO = "Admin";
	private static final Integer PASSWORD = -1165458038; //centroMedico2020

    public ControllerConectar(){};

    /**
     * Verificación de usuario y contraseña correctos.
     * @param {String} Nombre de usuario.
     * @param {String} Contraseña del usuario.
     * @return {boolean} Si es valido o no.
     */
    public boolean puedeIngresarUsuario(String usuario, String contrasena) {
		return (USUARIO.equals(usuario) && PASSWORD.equals(contrasena.hashCode()));
	}
}

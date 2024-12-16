package org.centroMedico.servicio;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Seguridad {

	private static final String CLAVE="MIIJKAIBAAKCAgEAqXpkuwW4OPVxMgzm";

    private static Seguridad instancia;
    private final Key aesKey;

    private Seguridad(){
        this.aesKey = new SecretKeySpec(CLAVE.getBytes(), 0, 16, "AES");
    }

    public static Seguridad getInstance() {
        if (instancia == null) 
            instancia = new Seguridad();
        
        return instancia;
    }

    public String cifrado(String mensaje) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, this.aesKey);

		byte[] encrypted = cipher.doFinal(mensaje.getBytes());
			
		return Base64.getEncoder().encodeToString(encrypted);
	}

	public String descifrado(String mensaje) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, aesKey);

		String decrypted = new String(cipher.doFinal(Base64.getDecoder().decode(mensaje)));
	        
		return decrypted;
	}
}

package Servidor.org;

import static org.apache.commons.codec.binary.Base64.decodeBase64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Desencriptar {

	//Definimos el tipo de algoritmo a utilizar
	private static String alg;
	
	//Definimos el modo de cifrado a utilizar
	private static String cifrado;
	
	private static void confi(String ruta) {
		Configuraciones config = new Configuraciones(ruta);
		alg = config.getAlg();
		cifrado = config.getCifrado();
	}
	
	public static String decrypt(String key, String vector, String textoEncripted, String rutaP) throws Exception{
		confi(rutaP);
		Cipher cipher = Cipher.getInstance(cifrado);
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);
		IvParameterSpec paramSpec = new IvParameterSpec(vector.getBytes());
		byte [] encript = decodeBase64(textoEncripted);
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, paramSpec);
		byte [] decriptado = cipher.doFinal(encript);
		return new String (decriptado);
	}
}

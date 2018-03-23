package FirmaDigital.org;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

public class FirmaDigital {
	
	public static boolean comprobar;

	public static void Firmar(String ruta) {
		try{
			//Crear un generador de par de claves.
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA","SUN");
			
			//Inicializar el genrador de claves
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG","SUN");
			keyGen.initialize(1024, random);
			
			//Generar el Par de claves
			KeyPair pair = keyGen.generateKeyPair();
			PrivateKey priv = pair.getPrivate();
			PublicKey pub = pair.getPublic();
			
			//Firmar los Datos
				//Obtener el objeto de firma
			Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
			
			//Inicializar el objeto de Firma
			dsa.initSign(priv);
			
			//Proporcionar al objeto de firma los datos que se deben firmar
			File archi = new File(ruta);
			FileInputStream fis = new FileInputStream(/*ruta*/archi);
			BufferedInputStream bufin = new BufferedInputStream(fis);
			byte [] buffer = new byte[1024];
			int tam;
			while((tam = bufin.read(buffer)) >= 0) {
				dsa.update(buffer, 0, tam);
			};
			bufin.close();
			
			//Generar la Firma
			byte [] realSig = dsa.sign();
			
			//Guardar la Firma y la Clave publica del Archivo
				//Guardar la firma en un archivo
			FileOutputStream sigfos = new FileOutputStream("FirmaDigital");
			sigfos.write(realSig);
			sigfos.close();
			
			//Guardar la clave publica en un archivo
			byte [] key = pub.getEncoded();
			FileOutputStream keyfos = new FileOutputStream("keyPub");
			keyfos.write(key);
			keyfos.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void VerifySign(String[] rutas) {
		try {
			//Ingresar y Convertir los bytes de Clave Publica Codificada
				//Leer los bytes de la clave publica codificada
			File ApubKey = new File(rutas[0]);
			FileInputStream keyfis = new FileInputStream(/*rutas[0]*/ApubKey);
			byte [] encKey = new byte[keyfis.available()];
			keyfis.read(encKey);
			keyfis.close();
			
			//Especificar la clave segun el estandar X509
			X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
			KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
			
			//Generar la clave publica segun la especificacion usando el KeyFactory
			PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);
			
			//Ingresar los bytes de la firma
			File ASignature = new File(rutas[1]);
			FileInputStream sigfis = new FileInputStream(/*rutas[1] */ASignature);
			byte[] sigToVerify = new byte[sigfis.available()];
			sigfis.read(sigToVerify);
			sigfis.close();
			
			//Verificar la firma
				//Inicializar el objeto de firma para la verificacion
			Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
			sig.initVerify(pubKey);
			
			//Proporcionar el objeto de firma con los datos que se verificaran
			File Adata = new File(rutas[2]);
			FileInputStream datafis = new FileInputStream(/*rutas[2] */Adata);
			BufferedInputStream bufin = new BufferedInputStream(datafis);
			
			byte[] buffer = new byte[1024];
			int tam;
			while(bufin.available() != 0) {
				tam = bufin.read(buffer);
				sig.update(buffer, 0, tam);
			}
			bufin.close();
			
			//Verificar la firma
			comprobar = sig.verify(sigToVerify);
			System.out.println("Firma Verificada?: " + comprobar);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}



import Comprimir.org.*;
import FirmaDigital.org.*;
import encriptamiento.org.*;

public class Main {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub	
		Configuraciones config = new Configuraciones("Config.properties");
		config.setAlg("AES");
		config.setClave(generarKey());
		config.setVector(generarKey());
		
		String ruta1 = "ejemplo.txt";
		String ruta2 = "ejemploEncrip.txt";
		String ruta3 = "ejemploDecrip.txt";
		int modo = 1; //1 = Encriptamiento; 2 = Desencriptamiento
		
		String key = config.getClave();
		String vector = config.getVector();
		String texto = LecturaEscritura.LecturaTxt("C:\\Users\\AnangelaJ\\Documents\\ejemplo.txt");
		System.out.println(texto);
		System.out.println("Texto Encriptado: " + Encriptar.encrypt(key, vector, texto, "Config.properties"));
		System.out.println("Texto Desencriptado: " + Encriptar.decrypt(key, vector, Encriptar.encrypt(key, vector, texto, "Config.properties"), "Config.properties"));
		LecturaEscritura.LeerArchivo(ruta1, ruta2, key, vector, modo);		
		LecturaEscritura.LeerArchivo(ruta2, ruta3, key, vector, 2);
		
		String[] rutas= {
				"keyPub",
				"FirmaDigital",
				ruta1
		};
		FirmaDigital.Firmar(ruta1);
		FirmaDigital.VerifySign(rutas);
		
		String [] archivos = {
				ruta2, 
				"Config.properties",
				"keyPub",
				"FirmaDigital"
				};
//		Comprimir com = new Comprimir("C:\\Users\\AnangelaJ\\Documents\\Ejemplos\\ej.zip");
//		com.agregar(archivos);
		
		String rutaZip = "Licitaciones.zip";
		String PasswordZip = "Licitaciones";
		ZipPass.ComprimirZip(archivos, rutaZip, PasswordZip);
	}
	
		
	
	public static String generarKey() {
		String [] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","v","w","x","y","z",
				"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","V","W","X","Y","Z",
				"1","2","3","4","5","6","7","8","9","0"};
		String key = "";
		int number;
		for(int i = 0; i < 16; i++) {
			number = (int) (Math.random()*59);
			key += alphabet[number];
		}
		return key;
	}

}

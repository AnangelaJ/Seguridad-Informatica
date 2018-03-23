package Servidor.org;

import FirmaDigital.org.*;
import net.lingala.zip4j.core.ZipFile;

public class descomprimir {
	public static String rutaArchi = "";
	public static boolean comprobar;
	public static void descomprimirRar(String ruta) {
		System.out.println("DescomprimirRar");
		String rutaDes = "c:/Archivos";
		String clave = "Licitaciones";
		
		System.out.println(rutaDes);
		
		try {
			ZipFile zipFile = new ZipFile(ruta);
			if(zipFile.isEncrypted()) {
				System.out.println("Archivo Encriptado");
				zipFile.setPassword(clave);
				zipFile.extractAll(rutaDes);
			}
			
			Configuraciones config = new Configuraciones(rutaDes + "/Config.properties");			
			EscribirDesen.LeerArchivo(rutaDes + "/ejemploEncrip.txt", rutaDes + "/ejemploDes.txt", config.getClave(), config.getVector(), rutaDes + "/Config.properties");
			String [] rutas = {
					rutaDes + "/keyPub",
					rutaDes + "/FirmaDigital",
					rutaDes + "/ejemploDes.txt"
			};
			FirmaDigital.VerifySign(rutas);
			comprobar = FirmaDigital.comprobar;
			rutaArchi = rutaDes + "/ejemploDes.txt";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package Servidor.org;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class EscribirDesen {

	public static void LeerArchivo(String entrada, String salida, String key, String vector, String properties){
		String leido = null;
		File EntradaF = null;
		File SalidaF = null;
		FileReader fr = null;
		FileWriter fw = null;
		BufferedReader br = null;
		BufferedWriter bw = null;
		try{
			EntradaF = new File(entrada);
			SalidaF = new File(salida);
			fr = new FileReader(EntradaF);
			fw = new FileWriter(SalidaF);
			br = new BufferedReader(fr);
			bw = new BufferedWriter(fw);
			//System.out.println("Lo primero" + leido);
			while((leido = br.readLine()) != null){
				//System.out.println(leido);
					bw.write(Desencriptar.decrypt(key, vector, leido, properties));
				bw.newLine();
				bw.flush();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

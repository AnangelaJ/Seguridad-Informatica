package encriptamiento.org;

//import org.apache.poi.hwpf.extractor.WordExtractor;
//import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class LecturaEscritura {
	private static String texto;
	
//	public static void LecturaWord(String tipo, String ruta) {
//		//Se crea el objeto File con la ruta del archivo
//		File archivo = new File(ruta);
//		
//		//Creamos el stream fijense bien los objetos usados
//		InputStream entradaArch = null;
//		try {
//			entradaArch = new FileInputStream(archivo);
//			if(tipo == "doc") {
//				doc(entradaArch);
//			}else if(tipo == "docx") {
//				docx(entradaArch);
//			}else {
//				System.out.println("Tipo invalido");
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
	
	public static String LecturaTxt(String ruta) {
		  File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	      String text = "";
	      try {
	         archivo = new File (ruta);
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         // Lectura del fichero
	         String linea;
	         while((linea=br.readLine())!=null)
	            text += linea;
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         try{                    
	            if( fr != null ) 
	               fr.close();  
	            if(br != null)
	            	br.close();
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	      return text;
	}
	
	public static void LeerArchivo(String entrada, String salida, String key, String vector, int modo){
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
				if(modo == 1)
					bw.write(Encriptar.encrypt(key, vector, leido, "Config.properties"));
				else if(modo == 2)
					bw.write(Encriptar.decrypt(key, vector, leido, "Config.properties"));
				bw.newLine();
				bw.flush();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void EscribirTxt(String ruta, String texto) {
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(ruta);
            pw = new PrintWriter(fichero);
            pw.println(texto);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (fichero != null)
              fichero.close();
           if(pw != null)
        	   pw.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
	}
	
//	private static void doc(InputStream entrada) {
//		//Creamos el extractor pasandole el stream
//		WordExtractor we;
//		try {
//			we = new WordExtractor(entrada);
//			//Leemos y guardamos en un String
//			texto = we.getText();
//			//Lo imprimimos para probar
//			System.out.print(texto);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	private static void docx(InputStream entradaArch) {
//		//Se crea un documento que la POI entiende pasandole el stream
//		XWPFDocument ardocx;
//		try {
//			ardocx = new XWPFDocument(entradaArch);
//			//instanciamos el obj para extraer contenido pasando el documento
//			XWPFWordExtractor xwpf_we = new XWPFWordExtractor(ardocx);
//			//leer el texto para un .docx
//			texto = xwpf_we.getText();
//			// se imprime
//			System.out.println(texto);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public static String getTexto() {
		return texto;
	}
	
}

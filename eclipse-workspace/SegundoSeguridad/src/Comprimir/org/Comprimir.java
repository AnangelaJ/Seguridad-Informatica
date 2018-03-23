package Comprimir.org;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Comprimir {
	String ruta1;
	private FileOutputStream fos = null;
	File archi;
	
	public Comprimir(String ruta) {
		this.ruta1 = ruta;
		archi = new File(this.ruta1);
		try {
			if(archi.exists()) {
				System.out.println("Archivo existente");
			}else {
				this.fos = new FileOutputStream(ruta1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void agregar(String [] files) {
		if(fos == null) {
			System.out.println("No se puede comprimir");
		}else {
			try {
				byte [] buffer = new byte[1024];
				FileOutputStream fos2 = fos;
				ZipOutputStream zos = new ZipOutputStream(fos2);
				
				for(int i = 0; i < files.length; i++) {
					System.out.println("Agregado: " + files[i]);
					FileInputStream fis = new FileInputStream(files[i]);
					zos.putNextEntry(new ZipEntry(files[i]));
					int tamaño;
					while((tamaño = fis.read(buffer)) > 0) {
						zos.write(buffer, 0, tamaño);
					}
					zos.closeEntry();
					fis.close();
				}
				zos.close();
				System.out.println("El comprimido fue creado");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

	}
}

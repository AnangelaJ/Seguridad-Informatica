package Comprimir.org;

import java.io.File;
import java.util.ArrayList;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;


public class ZipPass {

	public static void ComprimirZip(String [] files, String ruta, String Password) {
		
		try {
			ZipFile zipFile = new ZipFile(ruta);
			ArrayList<File> Afiles = new ArrayList<File>();
			ZipParameters parameters = new ZipParameters();
			
			for(int i = 0; i < files.length; i++) {
				Afiles.add(new File(files[i]));
			}
			
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			parameters.setEncryptFiles(true);
			parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
			parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
			parameters.setPassword(Password);
			
			zipFile.addFiles(Afiles, parameters);
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
}

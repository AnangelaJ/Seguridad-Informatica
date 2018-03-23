package encriptamiento.org;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Configuraciones {
	private String Alg;
	private String Cifrado;
	private String Clave;
	private String Vector;
	Properties p = new Properties();
	
	public Configuraciones(String ruta) {
		try {
			p.load(new FileReader(ruta));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getAlg() {
		this.Alg = p.getProperty("Algo");
		return Alg;
	}
	
	public String getCifrado() {
		this.Cifrado = p.getProperty("Cifrado");
		return Cifrado;
	}
	
	public String getClave() {
		this.Clave = p.getProperty("Clave");
		return Clave;
	}
	
	public String getVector() {
		this.Vector = p.getProperty("Vector");
		return Vector;
	}
	
	public void setAlg(String alg) {
		Alg = alg;
		p.setProperty("Algo", this.Alg);
		try {
			p.store(new FileWriter("Config.properties"), "ModificadoAlgo");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setCifrado(String cifrado) {
		Cifrado = cifrado;
		p.setProperty("Cifrado", Cifrado);
		try {
			p.store(new FileWriter("Config.properties"), "ModificadoCifrado");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setClave(String clave) {
		Clave = clave;
		p.setProperty("Clave", Clave);
		try {
			p.store(new FileWriter("Config.properties"), "ModificadoClave");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setVector(String vector) {
		Vector = vector;
		p.setProperty("Vector", Vector);
		try {
			p.store(new FileWriter("Config.properties"), "ModificadoVector");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

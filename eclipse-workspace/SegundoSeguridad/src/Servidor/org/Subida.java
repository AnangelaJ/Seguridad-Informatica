package Servidor.org;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import org.json.JSONObject;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


/**
 * Servlet implementation class Subida
 */
@WebServlet("/Subida")
@MultipartConfig
public class Subida extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Subida() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Part file = request.getPart("file");
		InputStream filecontent = file.getInputStream();
		OutputStream os = null;
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		int status;
		try {
			String baseDir = "c:/Ejemplos";
			String dir = baseDir + "/" + this.getFileName(file);
			os = new FileOutputStream(dir);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = filecontent.read(bytes)) != -1) {
				os.write(bytes, 0, read);
			}
			descomprimir.descomprimirRar(dir);
			json.put("Archivo", descomprimir.rutaArchi);
			if(descomprimir.comprobar == true)
				json.put("Firma", "Correcta");
			else
				json.put("Firma", "Incorrecta");
			status = 200;
		} catch (Exception e) {
			status = 404;
			e.printStackTrace();
		} finally {
			if (filecontent != null) {
				filecontent.close();
			}
			if (os != null) {
				os.close();
			}
		}
		json.put("status", status);
		out.print(json);
	}
	
	private String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

}

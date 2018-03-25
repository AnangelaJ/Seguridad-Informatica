package acfb.org;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.server.ServerEndpoint;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.websocket.*;

@ServerEndpoint("/juego")
public class juego {
	int pos;
	private static int players;
	
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	
	
	@OnMessage
	public void onMessage (String msg, Session session)
			throws IOException{
		
		synchronized (clients) {
			for (Session client : clients) {
				client.getBasicRemote().sendText(msg);
			}
		}
		
	}
	
	@OnOpen
	public void onOpen(Session session) throws IOException{
		System.out.println("b");
		if(clients.size() < 2){
			clients.add(session);
		}
	}
	
	@OnClose
	public void onClose (Session session) throws IOException{
		clients.remove(session);
		synchronized (clients) {
			for (Session client : clients) {
				client.getBasicRemote().sendText( "Alguien abandono la sala");
			}
		}
		
	}
	@OnError
	public void onError(Throwable e) throws IOException {
	    e.printStackTrace();
	    synchronized (clients) {
			for (Session client : clients) {
				client.getBasicRemote().sendText( e.toString());
			}
		}
	}
	 
}

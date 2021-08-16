package socket;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Servlet implementation class websocket
 */
@ServerEndpoint("/websocket")
public class websocket {
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnMessage
	public void handleMessage(String message, Session session) throws Exception
	{
		
		// 메시지 내용을 콘솔에 출력한다.		
		System.out.println(message);
		if(message.startsWith("S|")) {
			synchronized(clients) {
	            for(Session client : clients) {
	                if(!client.equals(session)) {
	                    client.getBasicRemote().sendText(message);
	                }
	            }
	        }
		}else {
			System.out.println("test");
		}
	}
	// WebSocket과 브라우저가 접속이 끊기면 요청되는 함수
	
	@OnOpen
	public void handleOpen(Session session)
	{
		
		// 콘솔에 접속 로그를 출력한다.
		clients.add(session);
		System.out.println("client is now connected...");
	}
	// WebSocket으로 메시지가 오면 요청되는 함수
	
	@OnClose
	public void handleClose(Session session)
	{
		// 콘솔에 접속 끊김 로그를 출력한다.
		clients.remove(session);
		System.out.println("client is now disconnected...");
	}
	
	// WebSocket과 브라우저 간에 통신 에러가 발생하면 요청되는 함수.
	
	@OnError
	public void handleError(Throwable t)
	{
		// 콘솔에 에러를 표시한다.
		t.printStackTrace();
	}
}

package clientSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class PingServer {

	final static int ServerPort = 13000;
	final static String ServerIP = "54.186.26.157";

	private Socket socket=null;
	public Socket getSocket() {
		return socket;
	}

	private PrintWriter out;
	private BufferedReader in;

	public void listenSocket(){
		try{
			socket = new Socket(ServerIP, ServerPort);
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("listening to "+ ServerIP +" at "+ ServerPort);

		}catch(UnknownHostException  e)
		{ 
			e.getStackTrace();
			System.out.println("Unknown host");
			
		}
		catch(IOException e){
			System.out.println("NO I/O");
			
		}
		finally{
			
			System.exit(1);
		}
	}

	public void clientActions(){
		int count = 1;
		//String text = "Hello Server. Ping : ";
		while(count < 300){
			System.out.println("Sending text");
			//text+= text+(count++);
			try {
				out.println(count);
				count++;
				String line;
				line = in.readLine();
				System.out.println("Text received: " + line);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("error sending");
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PingServer clientInstance = new PingServer();
		clientInstance.listenSocket();
		clientInstance.clientActions();
		try {
			clientInstance.getSocket().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

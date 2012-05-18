package locations.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import locations.gui.EnterNameWindow;
import locations.gui.GameWindow;
import locations.server.Message;
import locations.server.MessageType;

/**
 * Kliendi ühendamine.
 *
 */
public class Client {

	/**
	 * Port, millel server jookseb.
	 */
	public static final int port = 8889;
	
	public static void main(String[] args) {
		String serverName = "localhost";
		Socket sock;
		try {
			InetAddress serverAddr = InetAddress.getByName(serverName);
			try {
				sock = new Socket(serverAddr, port);
				
				//serverile sõnumid
				ObjectOutputStream netOut = new ObjectOutputStream(sock.getOutputStream());
				//serverilt sõnumid
				ObjectInputStream netIn = new ObjectInputStream(sock.getInputStream());
				
				Object fromServer;
				while ((fromServer = netIn.readObject()) != null) {
					
					Message msgFromServer = (Message) fromServer;

					if (msgFromServer.type == MessageType.COMMAND) {
						String cmd = msgFromServer.contents;
						if ("NAME".equals(cmd)) {
							EnterNameWindow n = new EnterNameWindow(netOut);
							Starter.run(n, 300, 80);
						} else if ("JOIN".equals(cmd)) {
							GameWindow n = new GameWindow(netOut);
							Starter.run(n, 800, 600);
						} 
					}
				}			
			} catch (ConnectException e) {
				System.out.println("Ei õµnnestu luua ühendust serveriga " + serverAddr.getHostName() + ". Põhjus: " + e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (UnknownHostException e) {
			System.out.println("Tundmatu server: " + e.getMessage());
		}
	}
}

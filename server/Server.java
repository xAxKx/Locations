package locations.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import locations.game.Game;


/**
 * Serveri klass.
 *
 */
public class Server {

	/**
	 * Serveri port.
	 */
	public static final int port = 8889;
	
	public static void main(String[] args) {
		
		Game game = new Game();
		
		try {
			ServerSocket serv = new ServerSocket(port);
			while (true) { //serveri t��ts�kkel
				Socket socket = serv.accept();	//ootab kuni klient �hendab
				try {
					new ClientSession(socket, game);	//kliendi l�ime loomine
				} catch (IOException e) {
					socket.close();	//�hendust ei loodud, sulgeme sokli
				}
			}
		} catch (IOException e) {
			System.out.println("IO viga :" + e.getMessage());
			e.printStackTrace();
		}
	}
}

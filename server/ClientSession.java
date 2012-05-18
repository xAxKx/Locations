package locations.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import locations.game.Game;

/**
 * Kliendi lõim.
 * @author Taavi
 *
 */
class ClientSession extends Thread {
	private Socket sock;
	private ObjectInputStream netIn;
	private ObjectOutputStream netOut;
	private Protocol protocol;
	
	/**
	 * Uue kliendi threadi loomine.
	 * @param sock socket
	 * @param game game
	 * @throws IOException kui tekib viga, siis väljakutsuja sulgeb socketi (Server klassi all)
	 */
	public ClientSession(Socket sock, Game game) throws IOException {
		this.sock = sock;
		protocol = new Protocol(game);
		//kliendile sõnumid
		netOut = new ObjectOutputStream(sock.getOutputStream());
		//kliendilt sõnumid
		netIn = new ObjectInputStream(sock.getInputStream());
		start();
	}
	
	/**
	 * Kliendi töötsükkel.
	 */
	public void run() {
		try {
			Object fromClient, toClient;
			
			//protokolli käivitamine
			netOut.writeObject(protocol.processInput(null));
			
			//kui klient midagi sisestab
			while ((fromClient = netIn.readObject()) != null) {
				toClient = protocol.processInput((Message)fromClient);
				netOut.reset();
				netOut.writeObject(toClient);
			}
		} catch (Exception e) {
			try {
				System.out.println("Avarii kliendi " + getName() + " seansis: " + e.getMessage() );
				sock.close();
			} catch (IOException ee) {
				throw new RuntimeException(ee);
			}
		}
	}
}

package locations.server;

import locations.game.Game;


/**
 * Serveri olek.
 */
enum ServerState {
	WAITING, ASKEDNAME, GOTNAME
}

/**
 * Protokolli klass.
 * M‰‰rab suhtluse toimimise j‰rjekorra jms.
 *
 */
public class Protocol {
	
	private ServerState state = ServerState.WAITING;
	private Game game;
	
	public Protocol(Game game) {
		this.game = game;
	}

	/**
	 * Saadab m‰ngijale sobiva info tagasi.
	 * @param String m‰ngija sisend
	 * @return String m‰ngijale tagasi
	 */
	public Object processInput(Message msgIn) {
		Object output = null;
		
		switch (state) {
		case WAITING:
			output = new Message(MessageType.COMMAND, "NAME");
			state = ServerState.ASKEDNAME;
			break;
		
		case ASKEDNAME:
			game.addPlayer(msgIn.contents);
			output = new Message(MessageType.COMMAND, "JOIN");
			state = ServerState.GOTNAME;
			break;
		
		case GOTNAME:
//			output = new Message(MessageType.COMMAND, "STARTGAME");
//			state = ServerState.GOTNAME;
			break;
			
		}
		
		return output;
	}
}
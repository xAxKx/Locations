package locations.game;

import java.util.ArrayList;

/**
 * M�ngu loogika.
 *
 */
public class Game {

	private ArrayList<Player> players = new ArrayList<Player>();
	
	public void addPlayer(String name) {
		players.add(new Player(name));
	}

}

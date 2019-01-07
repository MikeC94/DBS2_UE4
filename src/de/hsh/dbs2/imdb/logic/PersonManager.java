package de.hsh.dbs2.imdb.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.hsh.dbs2.imdb.entities.Player;

public class PersonManager {

	/**
	 * Liefert eine Liste aller Personen, deren Name den Suchstring enthaelt.
	 * 
	 * @param text Suchstring
	 * @return Liste mit passenden Personennamen, die in der Datenbank eingetragen
	 *         sind.
	 * @throws Exception
	 */
	public List<String> getPersonList(String text) throws Exception {
		List<String> playersStr = new ArrayList<String>();
		MovieFactory mf = new MovieFactory();
		Collection<Player> players = mf.findByName(text);
		mf.shutdown();
		for (Player p : players) {
			playersStr.add(p.getName());
		}
		return playersStr;
	}

}

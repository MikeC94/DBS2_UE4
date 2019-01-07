package de.hsh.dbs2.imdb.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import de.hsh.dbs2.imdb.entities.Genre;

public class GenreManager {

	/**
	 * Ermittelt eine vollstaendige Liste aller in der Datenbank abgelegten Genres
	 * Die Genres werden alphabetisch sortiert zurueckgeliefert.
	 * 
	 * @return Alle Genre-Namen als String-Liste
	 * @throws Exception
	 */
	public List<String> getGenres() throws Exception {
		List<String> genresStr = new ArrayList<String>();

		MovieFactory mf = new MovieFactory();
		Collection<Genre> genres = mf.findAll();
		mf.shutdown();

		for (Genre g : genres) {
			genresStr.add(g.getGenre());
		}
		return genresStr;

	}

}

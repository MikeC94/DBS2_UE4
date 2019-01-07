package de.hsh.dbs2.imdb.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.hsh.dbs2.imdb.entities.Movie;
import de.hsh.dbs2.imdb.logic.dto.MovieDTO;

public class MovieManager {

	/**
	 * Ermittelt alle Filme, deren Filmtitel den Suchstring enthaelt. Wenn der
	 * String leer ist, sollen alle Filme zurueckgegeben werden. Der Suchstring soll
	 * ohne Ruecksicht auf Gross/Kleinschreibung verarbeitet werden.
	 * 
	 * @param search Suchstring.
	 * @return Liste aller passenden Filme als MovieDTO
	 * @throws Exception
	 */
	public List<MovieDTO> getMovieList(String search) throws Exception {
		List<MovieDTO> moviesDTO = new ArrayList<MovieDTO>();
		MovieFactory mf = new MovieFactory();
		Collection<Movie> movies = mf.findByTitle(search);
		for (Movie m : movies) {
			moviesDTO.add(MovieFactory.convertMovieToDto(m));
		}
		return moviesDTO;
	}

	/**
	 * Speichert die uebergebene Version des Films neu in der Datenbank oder
	 * aktualisiert den existierenden Film. Dazu werden die Daten des Films selbst
	 * (Titel, Jahr, Typ) beruecksichtigt, aber auch alle Genres, die dem Film
	 * zugeordnet sind und die Liste der Charaktere auf den neuen Stand gebracht.
	 * 
	 * @param movie Film-Objekt mit Genres und Charakteren.
	 * @throws Exception
	 */
	public void insertUpdateMovie(MovieDTO movieDTO) throws Exception {

		if (movieDTO != null) {

			MovieFactory mf = new MovieFactory();
			Movie m = null;

			if (movieDTO.getId() != null) {
				m = mf.findById(movieDTO.getId());
			}
			if (m == null) {
				m = new Movie();
			}
			MovieFactory.convertDtoToMovie(movieDTO, m);
			mf.persist(m);
			mf.shutdown();

		}
	}

	/**
	 * Loescht einen Film aus der Datenbank. Es werden auch alle abhaengigen Objekte
	 * geloescht, d.h. alle Charaktere und alle Genre-Zuordnungen.
	 * 
	 * @param movie
	 * @throws Exception
	 */
	public void deleteMovie(long movieId) throws Exception {
		MovieFactory mf = new MovieFactory();
		mf.delete(movieId);
		mf.shutdown();
	}

	public MovieDTO getMovie(long movieId) throws Exception {
		MovieFactory mf = new MovieFactory();
		return MovieFactory.convertMovieToDto(mf.findById(movieId));
	}

}

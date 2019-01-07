package de.hsh.dbs2.imdb.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Diese Klasse stellt ein Genre da
 *
 * @author A-Team
 */
@Entity
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;

	@Column(unique = true)
	private String genre;

	@ManyToMany(mappedBy = "genres")
	private Set<Movie> movies = new HashSet<Movie>();

	/**
	 * Standardconstruktor
	 */
	public Genre() {
		super();
	}

	/**
	 * Diese Methode gibt die ID zurück
	 *
	 * @return ID
	 */
	public long getId() {
		return id;
	}

	/**
	 * Diese Methode setzte die ID
	 *
	 * @param id ID
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Diese Methode gibt den Namen des Gerne zurück
	 *
	 * @return Name
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Diese Methode setzt den Namen des Gernre
	 *
	 * @param genre Name
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * Diese Methode gibt die Filme zurück.
	 * 
	 * @return Filme
	 */
	public Set<Movie> getMovies() {
		return this.movies;
	}

	/**
	 * Diese Methode gibt den Wert des Genre Objekts als String zurück.
	 *
	 * @return String Wert
	 */
	@Override
	public String toString() {
		return this.getGenre();
	}

}

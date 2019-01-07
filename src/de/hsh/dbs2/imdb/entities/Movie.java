package de.hsh.dbs2.imdb.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Diese Klasse stellt einen Film da
 *
 * @author A-Team
 */
@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id = 0;

	private String title;

	private int year;

	private String type;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "moviegenre")
	private Set<Genre> genres = new HashSet<Genre>();

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	private Set<MovieCharacter> character = new HashSet<MovieCharacter>();

	/**
	 * Standardconstruktor
	 */
	public Movie() {
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
	 * Diese Methode setzt die ID des Films
	 *
	 * @param id ID
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Diese Methode gibt den Titel des Films zurück.
	 *
	 * @return Titel
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Diese Methode setzt den Titel des Films.
	 *
	 * @param title Titel des Films
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Diese Mehtode gibt das Jahr des Films zurück.
	 *
	 * @return Jahr des Films
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Diese Mehtode setzt das Jahr des Films.
	 *
	 * @param year Jahr
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Diese Methode ermittelt den Typen des Films. C = Kinofilm
	 *
	 * @return Typ
	 */
	public String getType() {
		return type;
	}

	/**
	 * Diese Methode setzt den Typen des Films. C = Kinofilm
	 *
	 * @param type Typ
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Diese Methode gibt die Liste der Genre zurück.
	 * 
	 * @return Genre
	 */
	public Set<Genre> getGenre() {
		return this.genres;
	}

	/**
	 * Diese Methode setzt die Genres
	 * 
	 * @param genres Genres
	 */
	public void setGenre(Set<Genre> genres) {
		this.genres = genres;
	}

	/**
	 * Diese Methode gibt die Lister der Filmcharaktere zurück.
	 * 
	 * @return Filmcharaktere
	 */
	public Set<MovieCharacter> getCharacter() {
		return this.character;
	}

	/**
	 * Diese Methode setzt die Filmcharakter
	 * 
	 * @param character Filmcharakter
	 */
	public void setCharacter(Set<MovieCharacter> character) {
		this.character = character;
	}

	/**
	 * Diese Methode gibt den Wert des Movie Objekts als String zurück.
	 *
	 * @return String Wert
	 */
	@Override
	public String toString() {

		String ret;

		// Film
		ret = "Kinofilm: " + this.getTitle() + " (" + this.getYear() + ")";

		// Genre
		/*
		 * ret += "\r\n"; ret += "Genre:"; for (int i = 0; i < this.genre.size(); i++) {
		 * if (i != 0) { ret += " | "; } else { ret += " "; } ret += this.genre.get(i);
		 * }
		 * 
		 * // Filmcharaktere ret += "\r\n"; ret += "Darsteller:"; for (int i = 0; i <
		 * this.character.size(); i++) { ret += "\r\n"; ret += this.character.get(i); }
		 */
		return ret;
	}

}

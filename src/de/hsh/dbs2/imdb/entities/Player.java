package de.hsh.dbs2.imdb.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Diese Klasse stellt eine Person da
 *
 * @author A-Teams
 */
@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;

	@Column(unique = true)
	private String name;

	private char sex;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	private Set<MovieCharacter> character = new HashSet<MovieCharacter>();

	/**
	 * Standardconstruktor
	 */
	public Player() {
		super();
	}

	/**
	 * Diese Methode gibt die Id zurück.
	 *
	 * @return Id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Diese Methode setzt die Id.
	 *
	 * @param id Id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Diese Mehtode gibt den Namen zurück.
	 *
	 * @return Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Diese Methode setzt den Namen.
	 *
	 * @param name Namen
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Diese Methode gibt das Geschlecht zurück. 'M' oder 'F'
	 *
	 * @return Geschlecht
	 */
	public char getSex() {
		return sex;
	}

	/**
	 * Diese Methode setzt das Geschlecht. 'M' oder 'F'
	 *
	 * @param sex Geschlecht
	 */
	public void setSex(char sex) {
		this.sex = sex;
	}

	/**
	 * Diese Methode gibt die Liste der Filmcharaktere zurück.
	 * 
	 * @return Filmcharaktere
	 */
	public Set<MovieCharacter> getCharacter() {
		return this.character;
	}

	/**
	 * Diese Methode gibt den Wert des Person Objekts als String zurück.
	 *
	 * @return String Wert
	 */
	@Override
	public String toString() {
		return this.getName();
	}
}

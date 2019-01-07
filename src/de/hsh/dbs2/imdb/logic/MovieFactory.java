package de.hsh.dbs2.imdb.logic;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import de.hsh.dbs2.imdb.entities.Genre;
import de.hsh.dbs2.imdb.entities.Movie;
import de.hsh.dbs2.imdb.entities.MovieCharacter;
import de.hsh.dbs2.imdb.entities.Player;
import de.hsh.dbs2.imdb.logic.dto.CharacterDTO;
import de.hsh.dbs2.imdb.logic.dto.MovieDTO;

public class MovieFactory {

	private EntityManagerFactory factory;
	private static EntityManager em;

	public MovieFactory() {
		factory = Persistence.createEntityManagerFactory("dbcon");
		em = factory.createEntityManager();
	}

	public void shutdown() {
		em.close();
		factory.close();
		em = null;
		factory = null;
	}

	public Collection<Movie> findByTitle(String title) {
		TypedQuery<Movie> query = em
				.createQuery("SELECT m FROM Movie m WHERE UPPER(m.title) LIKE UPPER('%" + title + "%')", Movie.class);

		Collection<Movie> collection = query.getResultList();
		return collection;
	}

	public Movie findById(long id) {
		return em.find(Movie.class, id);
	}

	public void persist(Movie m) {
		em.getTransaction().begin();
		em.persist(m);
		em.getTransaction().commit();
	}

	public void delete(long id) {
		em.getTransaction().begin();
		Movie m = em.find(Movie.class, id);
		em.remove(m);
		em.getTransaction().commit();
	}

	public Collection<Genre> findAll() {
		TypedQuery<Genre> query = em.createQuery("SELECT g FROM Genre g", Genre.class);
		Collection<Genre> collection = query.getResultList();
		return collection;
	}

	public static Genre findByGenre(String genre) {
		return (Genre) em.createQuery("SELECT g FROM Genre g WHERE g.genre = :gr").setParameter("gr", genre)
				.getSingleResult();
	}

	public static MovieCharacter findMCById(long id) {
		return em.find(MovieCharacter.class, id);
	}

	public static Player findPlayerByName(String name) {
		Player p = (Player) em.createQuery("SELECT p FROM Player p WHERE p.name = :na").setParameter("na", name)
				.getSingleResult();
		return p;
	}

	public Collection<Player> findByName(String search) {
		TypedQuery<Player> query = em
				.createQuery("SELECT p FROM Player p WHERE UPPER(p.name) LIKE UPPER('%" + search + "%')", Player.class);
		Collection<Player> collection = query.getResultList();
		return collection;
	}

	public static MovieDTO convertMovieToDto(Movie m) {
		MovieDTO dto = new MovieDTO();
		dto.setId(m.getId());
		dto.setTitle(m.getTitle());
		dto.setType(m.getType());
		dto.setYear(m.getYear());

		for (Genre g : m.getGenre()) {
			dto.getGenres().add(g.getGenre());
		}

		for (MovieCharacter mc : m.getCharacter()) {
			CharacterDTO cDTO = new CharacterDTO();
			cDTO.setId(mc.getId());
			cDTO.setAlias(mc.getAlias());
			cDTO.setCharacter(mc.getCharacter());
			if (mc.getPlayer() != null) {
				cDTO.setPlayer(mc.getPlayer().getName());
			}
			dto.getCharacters().add(cDTO);
		}

		return dto;

	}

	public static Movie convertDtoToMovie(MovieDTO dto, Movie movie) {

		if (dto != null && movie != null) {

			// Filminformationen übernehmen
			movie.setTitle(dto.getTitle());
			movie.setType(dto.getType());
			movie.setYear(dto.getYear());

			// Genres übernehmen
			Set<Genre> genres = new HashSet<Genre>();
			for (String genre : dto.getGenres()) {
				Genre g = findByGenre(genre);
				if (g != null) {
					genres.add(g);
				}
			}
			movie.setGenre(genres);

			// Moviecharacter übernehmen
			Set<MovieCharacter> character = new HashSet<MovieCharacter>();
			for (CharacterDTO cDTO : dto.getCharacters()) {
				MovieCharacter mc = findMCById(cDTO.getId());
				if (mc == null) {
					mc = new MovieCharacter();
				}
				mc.setAlias(cDTO.getAlias());
				mc.setCharacter(cDTO.getCharacter());
				mc.setMovie(movie);

				Player player = null;
				if (!cDTO.getPlayer().equals("")) {
					player = findPlayerByName(cDTO.getPlayer());
					player.getCharacter().add(mc);
				}
				mc.setPlayer(player);

				character.add(mc);
			}
			movie.setCharacter(character);

			return movie;

		} else {

			return null;
		}
	}

}

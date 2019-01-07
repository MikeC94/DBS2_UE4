package de.hsh.dbs2.imdb;

import javax.persistence.*;
import de.hsh.dbs2.imdb.entities.*;
import de.hsh.dbs2.imdb.logic.MovieFactory;

public class Test {

	public static void main(String[] args) {

		MovieFactory mf = new MovieFactory();

		Movie m = mf.findById(7);
		System.out.println(m);

		for (Genre g : m.getGenre()) {
			System.out.println(g);
		}

		mf.shutdown();

	}

	public static void initSQL() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbcon");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Movie m = new Movie();
		m.setTitle("Star Wars - Eine neue Hoffnung");
		m.setType("C");
		m.setYear(1977);

		Genre g = new Genre();
		g.setGenre("Science-Fiction");
		g.getMovies().add(m);
		m.getGenre().add(g);
		em.persist(g);

		g = new Genre();
		g.setGenre("Fantasy");
		g.getMovies().add(m);
		m.getGenre().add(g);
		em.persist(g);

		MovieCharacter mc = new MovieCharacter();
		mc.setCharacter("Leia Organa");
		mc.setMovie(m);

		Player p = new Player();
		p.setName("Carrie Fischer");
		p.setSex('W');
		p.getCharacter().add(mc);
		mc.setPlayer(p);
		em.persist(p);
		em.persist(mc);

		mc = new MovieCharacter();
		mc.setCharacter("Luke Skywalker");
		mc.setMovie(m);

		p = new Player();
		p.setName("Mark Hamill");
		p.setSex('M');
		p.getCharacter().add(mc);
		mc.setPlayer(p);
		em.persist(p);
		em.persist(mc);

		em.persist(m);

		em.getTransaction().commit();
		em.close();
	}

}

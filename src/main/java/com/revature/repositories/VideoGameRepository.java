package com.revature.repositories;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import com.revature.entities.VideoGame;

@Repository
public class VideoGameRepository {
	
	SessionFactory sf;
	
	@Inject
	public VideoGameRepository(SessionFactory sf) {
		this.sf = sf;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public VideoGame getById(int id) {
		Session session = sf.getCurrentSession();
		return session.get(VideoGame.class, id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public VideoGame create(VideoGame videoGame) {
		Session session = sf.getCurrentSession();
		session.save(videoGame);
		return videoGame;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public VideoGame update(VideoGame videoGame) {
		Session session = sf.getCurrentSession();
		session.merge(videoGame);
		return videoGame;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public VideoGame deleteById(int id) {
		Session session = sf.getCurrentSession();
		VideoGame videoGame = session.get(VideoGame.class, id );
		if (videoGame == null) throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		session.delete(videoGame);
		return videoGame;
	}

}

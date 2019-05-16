package com.revature.services;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.revature.entities.VideoGame;
import com.revature.repositories.VideoGameRepository;

@Service
public class VideoGameService {
	
	VideoGameRepository videoGameRepository;

	@Inject
	public VideoGameService(VideoGameRepository videoGameRepository) {
		super();
		this.videoGameRepository = videoGameRepository;
	}

	public VideoGame getById(int id) {
		return this.videoGameRepository.getById(id);
	}

	public VideoGame create(VideoGame videoGame) {
		return this.videoGameRepository.create(videoGame);
	}

	public VideoGame update(VideoGame videoGame) {
		return this.videoGameRepository.update(videoGame);
	}

	public VideoGame deleteById(int id) {
		return this.videoGameRepository.deleteById(id);
	}

}

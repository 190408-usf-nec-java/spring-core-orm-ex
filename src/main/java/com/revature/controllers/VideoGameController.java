package com.revature.controllers;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.revature.entities.VideoGame;
import com.revature.services.VideoGameService;

@RestController // All methods infer @ResponseBody
@RequestMapping("games")
public class VideoGameController {
	
	private VideoGameService videoGameService;
	
	@Inject
	public VideoGameController(VideoGameService videoGameService) {
		super();
		this.videoGameService = videoGameService;
	}

	@GetMapping("/{id}")
	public VideoGame getById(@PathVariable int id) {
		return Optional.ofNullable(this.videoGameService.getById(id))
			.orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public VideoGame createVideoGame(@RequestBody VideoGame videoGame) {
		return this.videoGameService.create(videoGame);
	}
	
	@PutMapping("")
	public VideoGame updateVideoGame(@RequestBody VideoGame videoGame) {
		return this.videoGameService.update(videoGame);
	}
	
	@DeleteMapping("/{id}")
	public VideoGame deleteVideoGame(@PathVariable int id) {
		return this.videoGameService.deleteById(id);
	}
	
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<String> handleClientError(HttpClientErrorException e) {
		return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
	}
}

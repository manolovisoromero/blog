package com.swayam.demo.trx.web.rest;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swayam.demo.trx.entity.Author;
import com.swayam.demo.trx.entity.Genre;
import com.swayam.demo.trx.service.AuthorService;
import com.swayam.demo.trx.web.dto.AuthorRequest;

@RestController
public class AuthorRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorRestController.class);

	private final AuthorService authorService;

	public AuthorRestController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@GetMapping(path = "/author")
	public List<Author> getAuthors() {
		return authorService.getAuthors();
	}

	@GetMapping(path = "/genre")
	public List<Genre> getGenre() {
		return authorService.getGenres();
	}

	@PostMapping(path = "/author", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Map<String, String> save(AuthorRequest authorRequest) {
		LOGGER.debug("authorRequest: {}", authorRequest);
		return authorService.saveAuthor(authorRequest);
	}

}
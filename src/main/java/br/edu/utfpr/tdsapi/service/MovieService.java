package br.edu.utfpr.tdsapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.utfpr.tdsapi.model.Movie;
import br.edu.utfpr.tdsapi.repository.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	public Movie updateMovie(Long id, Movie movie) {
		
		Movie savedMovie = movieRepository.findById(id)
				.orElseThrow( ()-> new EmptyResultDataAccessException(1));
		
		BeanUtils.copyProperties(movie, savedMovie, "id");
		
		return movieRepository.save(savedMovie);
	}
	
}
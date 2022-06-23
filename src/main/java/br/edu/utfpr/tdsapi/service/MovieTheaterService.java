package br.edu.utfpr.tdsapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.utfpr.tdsapi.model.MovieTheater;
import br.edu.utfpr.tdsapi.repository.MovieTheaterRepository;

@Service
public class MovieTheaterService {
	
	@Autowired
	private MovieTheaterRepository movieTheaterRepository;
	
	public MovieTheater updateMovieTheater(Long id, MovieTheater userModel) {
		
		MovieTheater savedUser = movieTheaterRepository.findById(id)
				.orElseThrow( ()-> new EmptyResultDataAccessException(1));
		
		BeanUtils.copyProperties(userModel, savedUser, "id");
		
		return movieTheaterRepository.save(savedUser);
	}
	
}
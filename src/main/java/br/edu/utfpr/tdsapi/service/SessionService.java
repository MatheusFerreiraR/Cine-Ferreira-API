package br.edu.utfpr.tdsapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import br.edu.utfpr.tdsapi.execeptionhandler.ExceptionHandler.Error;
import br.edu.utfpr.tdsapi.model.MovieTheater;
import br.edu.utfpr.tdsapi.model.Session;
import br.edu.utfpr.tdsapi.model.UserModel;
import br.edu.utfpr.tdsapi.repository.MovieTheaterRepository;
import br.edu.utfpr.tdsapi.repository.SessionRepository;
import br.edu.utfpr.tdsapi.service.exception.MovieTheatherNotAvailableException;

@Service
public class SessionService {
	
	@Autowired
	private SessionRepository sessionRepository;
	
	@Autowired
	private MovieTheaterRepository movieTheaterRepository;
	
	public Session saveSessao(Session session) {
		
		Optional<MovieTheater> movieTheater = movieTheaterRepository.findById(session.getMovieTheater().getId());
		
		return sessionRepository.save(session); 
	}
	
	public Session updateSession(Long id, Session session) {
		
		Session savedSession = sessionRepository.findById(id)
				.orElseThrow( ()-> new EmptyResultDataAccessException(1));
		
		BeanUtils.copyProperties(session, savedSession, "id");
		
		return sessionRepository.save(savedSession);
	}
}










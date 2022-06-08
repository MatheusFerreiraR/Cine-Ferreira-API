package br.edu.utfpr.tdsapi.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.tdsapi.model.MovieTheater;
import br.edu.utfpr.tdsapi.repository.MovieTheaterRepository;

@RestController
@RequestMapping("/movie-theather")
public class MovieTheaterResource {
	
	@Autowired
	private MovieTheaterRepository movieTheaterRepository;	
	
	@GetMapping
	@PreAuthorize("hasAuthority('RULE_PESQUISAR_SALA') and #oauth2.hasScope('read')")
	public List<MovieTheater> listMovieTheaters() {
		return movieTheaterRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('RULE_CADASTRAR_SALA') and #oauth2.hasScope('write')")
	public void createMovieTheater(@Valid @RequestBody MovieTheater movieTheater) {
		movieTheaterRepository.save(movieTheater); 
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('RULE_PESQUISAR_SALA') and #oauth2.hasScope('read')")
	public ResponseEntity<?> getMovieTheaterById(@PathVariable Long id) {
		Optional<MovieTheater> movieTheater = movieTheaterRepository.findById(id);
		
		return movieTheater.isPresent() ? ResponseEntity.ok(movieTheater) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('RULE_CADASTRAR_SALA') and #oauth2.hasScope('write')")
	public void deleteMovieTheaterById(@PathVariable Long id) {
		movieTheaterRepository.deleteById(id);
	}
}
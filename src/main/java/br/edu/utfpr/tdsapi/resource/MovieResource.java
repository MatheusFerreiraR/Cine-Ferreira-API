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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.tdsapi.model.Movie;
import br.edu.utfpr.tdsapi.model.UserModel;
import br.edu.utfpr.tdsapi.repository.MovieRepository;
import br.edu.utfpr.tdsapi.service.MovieService;
import br.edu.utfpr.tdsapi.service.UserService;

@RestController
@RequestMapping("/movie")
public class MovieResource {
	
	@Autowired
	private MovieRepository MovieRepository;	
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping
	//@PreAuthorize("hasAuthority('RULE_PESQUISAR_CAT_FILME') and #oauth2.hasScope('read')")
	public List<Movie> listFilms() {
		return MovieRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('RULE_CADASTRAR_CAT_FILME') and #oauth2.hasScope('write')")
	public void createMovie(@Valid @RequestBody Movie movie) {
		MovieRepository.save(movie); 
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('RULE_PESQUISAR_CAT_FILME') and #oauth2.hasScope('read')")
	public ResponseEntity<?> getMovieById(@PathVariable Long id) {
		Optional<Movie> movie = MovieRepository.findById(id);
		
		return movie.isPresent() ? ResponseEntity.ok(movie) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUserById(@PathVariable Long id, @RequestBody Movie movie) {
		
		return ResponseEntity.ok(movieService.updateMovie(id, movie));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('RULE_CADASTRAR_CAT_FILME') and #oauth2.hasScope('write')")
	public void deleteMovieById(@PathVariable Long id) {
		MovieRepository.deleteById(id);
	}
	
}
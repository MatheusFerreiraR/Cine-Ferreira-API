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

import br.edu.utfpr.tdsapi.model.CatMovie;
import br.edu.utfpr.tdsapi.repository.CatMovieRepository;

@RestController
@RequestMapping("/cat_movie")
public class CatMovieResource {
	
	@Autowired
	private CatMovieRepository catMovieRepository;	
	
	@GetMapping
	//@PreAuthorize("hasAuthority('RULE_PESQUISAR_CAT_FILME') and #oauth2.hasScope('read')")
	public List<CatMovie> listCatFilmes() {
		return catMovieRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('RULE_CADASTRAR_CAT_FILME') and #oauth2.hasScope('write')")
	public void createCatMovie(@Valid @RequestBody CatMovie catMovie) {
		catMovieRepository.save(catMovie); 
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('RULE_PESQUISAR_CAT_FILME') and #oauth2.hasScope('read')")
	public ResponseEntity<?> getCatMovieById(@PathVariable Long id) {
		Optional<CatMovie> catMovie = catMovieRepository.findById(id);
		
		return catMovie.isPresent() ? ResponseEntity.ok(catMovie) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('RULE_CADASTRAR_CAT_FILME') and #oauth2.hasScope('write')")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteCatMovieById(@PathVariable Long id) {
		catMovieRepository.deleteById(id);
	}
}
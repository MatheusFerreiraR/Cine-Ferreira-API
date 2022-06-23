package br.edu.utfpr.tdsapi.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.context.request.WebRequest;

import br.edu.utfpr.tdsapi.execeptionhandler.ExceptionHandler.Error;
import br.edu.utfpr.tdsapi.model.MovieTheater;
import br.edu.utfpr.tdsapi.model.Session;
import br.edu.utfpr.tdsapi.model.UserModel;
import br.edu.utfpr.tdsapi.model.bodyRequest.BodyRequestGetHours;
import br.edu.utfpr.tdsapi.repository.SessionRepository;
import br.edu.utfpr.tdsapi.repository.filter.SessionFilter;
import br.edu.utfpr.tdsapi.service.SessionService;
import br.edu.utfpr.tdsapi.service.exception.MovieTheatherNotAvailableException;

@RestController
@RequestMapping("/session")
public class SessionResource {
	
	@Autowired
	private SessionRepository sessionRepository;	
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	public List<Session> listSessions() {
		return sessionRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('RULE_CADASTRAR_SESSAO') and #oauth2.hasScope('write')")
	public void createSession(@Valid @RequestBody Session session) {
		sessionService.saveSessao(session);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getSessionById(@PathVariable Long id) {
		Optional<Session> session = sessionRepository.findById(id);
		
		return session.isPresent() ? ResponseEntity.ok(session) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('RULE_CADASTRAR_SESSAO') and #oauth2.hasScope('write')")
	public void deleteSessionById(@PathVariable Long id) {
		sessionRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateSessionById(@PathVariable Long id, @RequestBody Session session) {
		return ResponseEntity.ok(sessionService.updateSession(id, session));
	}
	
	@PostMapping("/hours")
	@PreAuthorize("hasAuthority('RULE_CADASTRAR_SESSAO') and #oauth2.hasScope('write')")
	public List<String> getHorsAvailable(@RequestBody BodyRequestGetHours bodyRequestGetHours) {
		return sessionRepository.listHorsAvailable(bodyRequestGetHours);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler({ MovieTheatherNotAvailableException.class })
	public ResponseEntity<Object> handleEmptyResultDataAcessException(MovieTheatherNotAvailableException ex, WebRequest request) {
		
		String userMessage = messageSource.getMessage("sala.indisponivel", null, LocaleContextHolder.getLocale());
		
		return ResponseEntity.badRequest().body(new Error(userMessage, ex.toString()));
	}
}
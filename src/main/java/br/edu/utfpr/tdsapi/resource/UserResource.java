package br.edu.utfpr.tdsapi.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.tdsapi.model.UserModel;
import br.edu.utfpr.tdsapi.repository.UserRepository;
import br.edu.utfpr.tdsapi.service.UserService;

@RestController
@RequestMapping("/user")
public class UserResource {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;
	
	
	@GetMapping
	public List<UserModel> listUsers() {
		return userRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createUser(@Valid @RequestBody UserModel userModel) {
		userRepository.save(userModel); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {
		Optional<UserModel> userModel = userRepository.findById(id);
		
		return userModel.isPresent() ? ResponseEntity.ok(userModel) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteUserById(@PathVariable Long id) {
		userRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUserById(@PathVariable Long id, @RequestBody UserModel userModel) {
		
		return ResponseEntity.ok(userService.updateUser(id, userModel));
	}
}
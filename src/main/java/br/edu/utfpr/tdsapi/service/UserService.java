package br.edu.utfpr.tdsapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.utfpr.tdsapi.model.UserModel;
import br.edu.utfpr.tdsapi.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserModel updateUser(Long id, UserModel userModel) {
		
		UserModel savedUser = userRepository.findById(id)
				.orElseThrow( ()-> new EmptyResultDataAccessException(1));
		
		BeanUtils.copyProperties(userModel, savedUser, "id");
		
		return userRepository.save(savedUser);
	}
	
}
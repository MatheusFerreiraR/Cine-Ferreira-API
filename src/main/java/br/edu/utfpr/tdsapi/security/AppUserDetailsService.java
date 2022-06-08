package br.edu.utfpr.tdsapi.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.utfpr.tdsapi.model.UserModel;
import br.edu.utfpr.tdsapi.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserModel> opUser = userRepository.findByEmail(email);
		
		UserModel user = opUser
				.orElseThrow( ()-> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
	
		return new User(email, user.getSenha(), getPermissions(user));
	}

	private Collection<? extends GrantedAuthority> getPermissions(UserModel user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRules().forEach(u -> authorities.add(new SimpleGrantedAuthority(u.getDescription().toUpperCase())));
		return authorities;
	}
}

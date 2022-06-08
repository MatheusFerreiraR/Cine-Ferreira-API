package br.edu.utfpr.tdsapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.tdsapi.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long>{

	public Optional<UserModel> findByEmail(String email);
}

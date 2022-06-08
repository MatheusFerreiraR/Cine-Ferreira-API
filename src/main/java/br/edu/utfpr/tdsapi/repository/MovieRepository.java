package br.edu.utfpr.tdsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.tdsapi.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

}

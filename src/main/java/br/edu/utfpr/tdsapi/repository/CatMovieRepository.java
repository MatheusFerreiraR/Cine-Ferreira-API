package br.edu.utfpr.tdsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.tdsapi.model.CatMovie;

public interface CatMovieRepository extends JpaRepository<CatMovie, Long>{

}

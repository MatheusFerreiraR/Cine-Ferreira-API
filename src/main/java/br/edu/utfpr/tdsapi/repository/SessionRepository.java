package br.edu.utfpr.tdsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.tdsapi.model.Session;
import br.edu.utfpr.tdsapi.repository.session.SessionRepositoryQuery;

public interface SessionRepository extends JpaRepository<Session, Long>, SessionRepositoryQuery {

}

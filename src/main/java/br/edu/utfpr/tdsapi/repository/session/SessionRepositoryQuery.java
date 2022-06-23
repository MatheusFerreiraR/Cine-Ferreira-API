package br.edu.utfpr.tdsapi.repository.session;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.utfpr.tdsapi.model.Session;
import br.edu.utfpr.tdsapi.model.bodyRequest.BodyRequestGetHours;
import br.edu.utfpr.tdsapi.repository.filter.SessionFilter;

public interface SessionRepositoryQuery {
	public Page<Session> listSessionsWithfilter(SessionFilter sessionFilter, Pageable pageable);

	public List<String> listHorsAvailable(BodyRequestGetHours bodyRequestGetHours);
}

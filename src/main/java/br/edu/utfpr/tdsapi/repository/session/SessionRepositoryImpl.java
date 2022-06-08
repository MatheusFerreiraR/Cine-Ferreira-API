package br.edu.utfpr.tdsapi.repository.session;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.edu.utfpr.tdsapi.model.Movie;
import br.edu.utfpr.tdsapi.model.Session;
import br.edu.utfpr.tdsapi.repository.filter.SessionFilter;

public class SessionRepositoryImpl implements SessionRepositoryQuery {

	@Autowired
	private EntityManager manager;
	
	@Override
	public Page<Session> listSessionsWithfilter(SessionFilter sessionFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Session> criteria = builder.createQuery(Session.class);
		Root<Session> root = criteria.from(Session.class);
		
		Predicate[] predicates = createPredicates(sessionFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Session> query = manager.createQuery(criteria);
		
		addRestrictionsPagination(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(sessionFilter));
	}	


	private Predicate[] createPredicates(
			SessionFilter sessionFilter,
			CriteriaBuilder builder,
			Root<Session> root
			) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (!sessionFilter.getMovieName().isEmpty()) {
			Join<Session, Movie> join = root.join("movie");
			
			predicates.add(builder.like(
					builder.lower(join.get("name")), "%" + sessionFilter.getMovieName().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private Long total(SessionFilter sessionFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Session> root = criteria.from(Session.class);
		
		criteria.where(createPredicates(sessionFilter, builder, root));
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
	
	private void addRestrictionsPagination(TypedQuery<Session> query, Pageable pageable) {
	
		int currentPage = pageable.getPageNumber();
		int contentPerPage = pageable.getPageSize();
		int firtPageRegistry = currentPage * contentPerPage;
		
		query.setFirstResult(firtPageRegistry);
		query.setMaxResults(contentPerPage);
	}
	
}

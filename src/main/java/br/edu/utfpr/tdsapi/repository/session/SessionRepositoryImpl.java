package br.edu.utfpr.tdsapi.repository.session;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.utfpr.tdsapi.model.Movie;
import br.edu.utfpr.tdsapi.model.MovieTheater;
import br.edu.utfpr.tdsapi.model.Session;
import br.edu.utfpr.tdsapi.model.bodyRequest.BodyRequestGetHours;
import br.edu.utfpr.tdsapi.repository.filter.SessionFilter;

public class SessionRepositoryImpl implements SessionRepositoryQuery {
	
	private int START_HOUR = 9;
	private int END_HOUR = 23;
	
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
	
	
	@Override
	public List<String> listHorsAvailable(BodyRequestGetHours bodyRequestGetHours) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Session> criteria = builder.createQuery(Session.class);
		Root<Session> root = criteria.from(Session.class);
		
		Predicate[] predicates = createPredicates(bodyRequestGetHours, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Session> query = manager.createQuery(criteria);
	
		return createHourArray(query);
	}
	
	private Predicate[] createPredicates(
			BodyRequestGetHours bodyRequestGetHours,
			CriteriaBuilder builder,
			Root<Session> root
			) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (bodyRequestGetHours.getId() != null) {
			Join<Session, MovieTheater> join = root.join("movieTheater");
			
			predicates.add(builder.equal(
					builder.lower(join.get("id")), bodyRequestGetHours.getId()));
			
			predicates.add(builder.like(root.get("dateTime"), bodyRequestGetHours.getDate() + "%"));
			
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	public List<String> createHourArray(TypedQuery<Session> query) {
		List<String> hourAvailable = new ArrayList<>();
		List<String> hourNotAvailable = new ArrayList<>();
		
		query.getResultList().forEach(session -> {
			
			String[] result = session.getDateTime().toString().split(" ");
			
			hourNotAvailable.add(result[1]);
		});;
		
		
		for(int i = START_HOUR; i < END_HOUR; i+= 3) {
			hourAvailable.add(i + ":00");
		}
		
		hourAvailable.removeAll(hourNotAvailable);
		
		return hourAvailable;
	}
	
}

package br.edu.utfpr.tdsapi.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sessao")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Session {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "filme")
	private Movie movie;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "sala")
	private MovieTheater movieTheater;

	@NotNull
	@Column(name = "date_time")
	private String dateTime;
}

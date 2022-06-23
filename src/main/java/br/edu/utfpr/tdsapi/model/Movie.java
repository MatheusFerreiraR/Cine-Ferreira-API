package br.edu.utfpr.tdsapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "filme")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 3)
	@Column(name = "nome")
	private String name;
	
	@Size(min = 3)
	@Column(name = "sinopse")
	private String synopsis;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "cat_filme")
	private CatMovie catMovie;
}

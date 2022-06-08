package br.edu.utfpr.tdsapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sala")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieTheater {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "disponibilidade")
	private Boolean available;
	
	@NotNull
	@Column(name = "qtd_assento")
	private Integer seatQuantity;
}

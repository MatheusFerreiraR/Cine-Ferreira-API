package br.edu.utfpr.tdsapi.model;

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
@Table(name = "position_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PositionCompany {
	@Id 
	private Long id;
	
	@NotNull
	private String description;
}

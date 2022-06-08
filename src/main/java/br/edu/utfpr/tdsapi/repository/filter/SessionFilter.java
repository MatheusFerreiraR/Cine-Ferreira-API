package br.edu.utfpr.tdsapi.repository.filter;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionFilter {

	private LocalDateTime SessionDateTime;
	private String movieName;
}

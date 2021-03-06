package br.edu.utfpr.tdsapi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class UserModel {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 3, max = 50)
	private String nome;
	
	@NotNull
	@Size(min = 11, max = 11, message = "O CPF deve ter 11 caracteres")
	private String cpf;
	
	@NotNull
	@Size(min = 5, max = 50)
	private String email;
	
	@NotNull
	@Size(min = 5, max = 255)
	private String senha;
	
	@NotNull
	@Column(name = "id_position")
	private Long idPosition;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "position_user_rules", joinColumns = @JoinColumn(name = "id_position"),
		inverseJoinColumns = @JoinColumn(name = "id_position"))
	private List<Rules> rules;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<Rules> getRules() {
		return rules;
	}
	public Long getIdPosition() {
		return idPosition;
	}
	public void setIdPosition(Long idPosition) {
		this.idPosition = idPosition;
	}
	public void setRules(List<Rules> rules) {
		this.rules = rules;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserModel other = (UserModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}

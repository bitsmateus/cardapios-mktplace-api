package br.com.senai.cardapiosmktplaceapi.entity;

import br.com.senai.cardapiosmktplaceapi.entity.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "secoes")
@Entity(name = "Secao")
public class Secao {

	@Id
	@Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Size(min = 3, max = 100, message = "O nome da secao deve conter entre 3 e 100 caracteres")
	@NotBlank(message = "O nome da seção é obrigatorio")
	@Column(name = "nome")
	private String nome;

	@NotNull(message = "O status da categoria é obrigatório")
	@Enumerated(value = EnumType.STRING)
	@Column(name = "status")
	private Status status;
	
	public Secao() {
		this.status = Status.A;
	}
	
	@Transient
	public boolean isPersistido() {
		return getId() != null && getId() > 0;
	}
	
	@Transient
	public boolean isAtivo() {
		return getStatus() == Status.A;
 	}

}

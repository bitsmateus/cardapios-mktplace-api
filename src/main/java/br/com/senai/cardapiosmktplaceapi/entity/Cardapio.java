package br.com.senai.cardapiosmktplaceapi.entity;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.cardapiosmktplaceapi.entity.enums.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "Cardapio")
@Table(name = "cardapios")
public class Cardapio {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Size(min = 3, max = 100, message = "O nome do cardapio deve conter entre 3 e 100 caracteres")
	@NotBlank(message = "O nome do cardapio é obrigatorio")
	@Column(name = "nome")
	private String nome;
	
	@Size(min = 3, max = 100, message = "A descrição do cardapio deve conter entre 3 e 100 caracteres")
	@NotBlank(message = "A descrição do cardapio é obrigatorio")
	@Column(name = "descricao")
	private String descricao;
	
	@NotNull(message = "O status da categoria é obrigatório")
	@Enumerated(value = EnumType.STRING)
	@Column(name = "status")
	private Status status;
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_restaurante")
	@NotNull(message = "O restaurante é obrigatorio")
	private Restaurante restaurante;
	
	@OneToMany(mappedBy = "cardapio", fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OpcaoDoCardapio> opcoes;
 	
	public Cardapio() {
		this.status = Status.A;
		this.opcoes = new ArrayList<>();
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

package br.com.senai.cardapiosmktplaceapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.cardapiosmktplaceapi.entity.Restaurante;
import br.com.senai.cardapiosmktplaceapi.entity.Secao;

@Repository
public interface SecoesRepository extends JpaRepository<Secao, Integer> {

	@Query(value = "SELECT s "
			 + "FROM Secao s "
		     + "WHERE Upper(s.nome) LIKE Upper(:nome) "
		     + "ORDER BY s.nome ",
		     countQuery = "SELECT Count(s) "
		                + "FROM Secao s "
		                + "WHERE Upper(s.nome) LIKE Upper(:nome) ")
	public Page<Secao> listarPor(String nome, Pageable paginacao);
	
	@Query(value = "SELECT s "
			 + "FROM Secao s "
			 + "WHERE Upper(s.nome) = Upper(:nome) ")
	public Secao buscarPor(String nome);
}

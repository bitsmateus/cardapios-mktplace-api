package br.com.senai.cardapiosmktplaceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.cardapiosmktplaceapi.entity.Opcao;

@Repository
public interface OpcoesRepository extends JpaRepository<Opcao, Integer> {

	@Query(value = "SELECT o " +
	        "FROM Opcao o " + // Added "o" here
	        "JOIN FETCH o.categoria " +
	        "JOIN FETCH o.restaurante " +
	        "WHERE o.id = :id ") // Moved "WHERE" here
	public Opcao buscarPor(Integer id);


	
}

package br.com.senai.cardapiosmktplaceapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senai.cardapiosmktplaceapi.entity.Categoria;
import br.com.senai.cardapiosmktplaceapi.entity.Opcao;
import br.com.senai.cardapiosmktplaceapi.entity.Restaurante;
import br.com.senai.cardapiosmktplaceapi.entity.enums.Status;
import br.com.senai.cardapiosmktplaceapi.repository.OpcoesRepository;
import br.com.senai.cardapiosmktplaceapi.repository.RestaurantesRepository;
import br.com.senai.cardapiosmktplaceapi.service.OpcaoService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Service
public class OpcaoServiceImpl implements OpcaoService {

	@Autowired
	private OpcoesRepository opcoesRepository;
	
	@Autowired
	private RestaurantesRepository restaurantesRepository;
	
	@Override
	public Opcao salvar(Opcao opcao) {
		
		return null;
	}

	@Override
	public void atualizarStatusPor(Integer id, Status status) {
		

	}

	@Override
	public Page<Opcao> listarPor(String nome, Pageable paginacao) {
		
		return null;
	}

	@Override
	public Opcao buscarPor(Integer id) {
		
		return null;
	}

	@Override
	public Opcao excluirPor(Integer id) {
		
		return null;
	}

	@Override
	public Page<Opcao> listarPor(
			@NotBlank(message = "O nome é obrigatório!") @Size(min = 3, max = 250, message = "O nome para listagem deve conter entre 3 e 250 caracteres!") String nome,
			Categoria categoria, Pageable paginacao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Opcao> listarPor(
			@NotBlank(message = "O nome é obrigatório!") @Size(min = 3, max = 250, message = "O nome para listagem deve conter entre 3 e 250 caracteres!") String nome,
			Restaurante restaurante, Pageable paginacao) {
		// TODO Auto-generated method stub
		return null;
	}
}
package br.com.senai.cardapiosmktplaceapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.cardapiosmktplaceapi.entity.Secao;
import br.com.senai.cardapiosmktplaceapi.entity.enums.Status;
import br.com.senai.cardapiosmktplaceapi.repository.CardapiosRepository;
import br.com.senai.cardapiosmktplaceapi.repository.SecoesRepository;
import br.com.senai.cardapiosmktplaceapi.service.SecaoService;

@Service
public class SecaoServiceImpl implements SecaoService {

	@Autowired
	private SecoesRepository secoesRepository;

	@Autowired
	private CardapiosRepository cardapiosRepository;

	@Override
	public Secao salvar(Secao secao) {
		Secao outraSecao = secoesRepository.buscarPor(secao.getNome());
		if (outraSecao != null) {
			if (secao.isPersistido()) {
				Preconditions.checkArgument(outraSecao.equals(secao), "O nome da seção ja está e uso");
			}
		}
		Secao secaoSalva = secoesRepository.save(secao);
		return secaoSalva;
	}

	@Override
	public void atualizarStatusPor(Integer id, Status status) {

	}

	@Override
	public Page<Secao> listarPor(String nome, Pageable paginacao) {
		return secoesRepository.listarPor("%" + nome + "%", paginacao);
	}

	@Override
	public Secao buscarPor(Integer id) {

		return null;
	}

	@Override
	public Secao excluirPor(Integer id) {
		Secao secaoParaExclusao = buscarPor(id);
		Long qtdeCardapiosVinculados = cardapiosRepository.ContarPor(id);
		Preconditions.checkArgument(qtdeCardapiosVinculados == 0,
				"Não é possivel remover pois existe restaurantes vinculados");
		this.secoesRepository.deleteById(secaoParaExclusao.getId());
		return secaoParaExclusao;
	}

}

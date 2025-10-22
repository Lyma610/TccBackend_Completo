package br.itb.projeto.seuprojeto.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.itb.projeto.seuprojeto.model.entity.Reacao;
import br.itb.projeto.seuprojeto.model.repository.ReacaoRepository;
import br.itb.projeto.seuprojeto.model.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class ReacaoService {

	private ReacaoRepository reacaoRepository;
	private UsuarioRepository usuarioRepository;

	public ReacaoService(ReacaoRepository reacaoRepository, UsuarioRepository usuarioRepository) {
		super();
		this.reacaoRepository = reacaoRepository;
		this.usuarioRepository = usuarioRepository;
	}

	public Reacao findById(long id) {
		Optional<Reacao> reacao = reacaoRepository.findById(id);
		if (reacao.isPresent()) {
			return reacao.get();
		}
		return null;
	}

	public List<Reacao> findAll() {
		List<Reacao> reacoes = reacaoRepository.findAll();
		return reacoes;
	}

	public List<Reacao> findAllAtivos(){
		List<Reacao> reacoes = reacaoRepository.findByStatusReacao("ATIVO");
		return reacoes;
	}
	
	@Transactional
	public Reacao create(Reacao reacao) {
		reacao.setDataReacao(LocalDateTime.now());
		reacao.setStatusReacao("ATIVO");
		return reacaoRepository.save(reacao);
	}
	
	@Transactional
	public Reacao inativar(long id) {
		Optional<Reacao> _reacao = reacaoRepository.findById(id);
		
		if (_reacao.isPresent()) {
			Reacao reacao = _reacao.get();
			reacao.setDataReacao(LocalDateTime.now());
			reacao.setStatusReacao("INATIVO");
			return reacaoRepository.save(reacao);
		}
		return null;
	}

	// Buscar reações por usuário
	public List<Reacao> findByUsuario(Long usuarioId) {
		return reacaoRepository.findByUsuarioId(usuarioId);
	}

	// Buscar reações por postagem
	public List<Reacao> findByPostagem(Long postagemId) {
		return reacaoRepository.findByPostagemId(postagemId);
	}

	// Buscar reações ativas por postagem
	public List<Reacao> findAtivasByPostagem(Long postagemId) {
		return reacaoRepository.findByPostagemIdAndStatusReacao(postagemId, "ATIVO");
	}

	// Buscar reações por tipo
	public List<Reacao> findByTipo(String tipoReacao) {
		return reacaoRepository.findByTipoReacao(tipoReacao);
	}

	// Buscar reações ativas por tipo
	public List<Reacao> findAtivasByTipo(String tipoReacao) {
		return reacaoRepository.findByTipoReacaoAndStatusReacao(tipoReacao, "ATIVO");
	}

	// Verificar se usuário já reagiu a um post
	public boolean hasUserReacted(Long usuarioId, Long postagemId, String tipoReacao) {
		List<Reacao> reacoes = reacaoRepository.findByUsuarioIdAndPostagemIdAndTipoReacaoAndStatusReacao(
			usuarioId, postagemId, tipoReacao, "ATIVO");
		return !reacoes.isEmpty();
	}

	// Contar reações por postagem e tipo
	public long countByPostagemAndTipo(Long postagemId, String tipoReacao) {
		List<Reacao> reacoes = reacaoRepository.findByPostagemIdAndStatusReacao(postagemId, "ATIVO");
		return reacoes.stream()
			.filter(r -> r.getTipoReacao().equals(tipoReacao))
			.count();
	}
}

package br.itb.projeto.seuprojeto.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.itb.projeto.seuprojeto.model.entity.Reacao;

@Repository
public interface ReacaoRepository extends JpaRepository<Reacao, Long> {
    
    // Buscar reações por status
    List<Reacao> findByStatusReacao(String statusReacao);
    
    // Buscar reações por tipo
    List<Reacao> findByTipoReacao(String tipoReacao);
    
    // Buscar reações por usuário
    List<Reacao> findByUsuarioId(Long usuarioId);
    
    // Buscar reações por postagem
    List<Reacao> findByPostagemId(Long postagemId);
    
    // Buscar reações ativas por usuário
    List<Reacao> findByUsuarioIdAndStatusReacao(Long usuarioId, String statusReacao);
    
    // Buscar reações ativas por postagem
    List<Reacao> findByPostagemIdAndStatusReacao(Long postagemId, String statusReacao);
    
    // Buscar reações por tipo e status
    List<Reacao> findByTipoReacaoAndStatusReacao(String tipoReacao, String statusReacao);
    
    // Buscar reações por usuário, postagem e tipo
    List<Reacao> findByUsuarioIdAndPostagemIdAndTipoReacao(Long usuarioId, Long postagemId, String tipoReacao);
    
    // Buscar reações ativas por usuário, postagem e tipo
    List<Reacao> findByUsuarioIdAndPostagemIdAndTipoReacaoAndStatusReacao(
        Long usuarioId, Long postagemId, String tipoReacao, String statusReacao);
}

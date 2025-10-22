package br.itb.projeto.seuprojeto.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Reacao")
public class Reacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String comentario;
	private LocalDateTime dataReacao;

	@ManyToOne
    @JoinColumn(name = "postagem_id")
	private Postagem postagem;
	
	@ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
	
    private String tipoReacao;
	private String statusReacao;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public LocalDateTime getDataReacao() {
		return dataReacao;
	}
	public void setDataReacao(LocalDateTime dataReacao) {
		this.dataReacao = dataReacao;
	}
	
	public Postagem getPostagem() {
		return postagem;
	}
	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	public String getTipoReacao() {
		return tipoReacao;
	}
	public void setTipoReacao(String tipoReacao) {
		this.tipoReacao = tipoReacao;
	}
	public String getStatusReacao() {
		return statusReacao;
	}
	public void setStatusReacao(String statusReacao) {
		this.statusReacao = statusReacao;
	}
	
	
    
   


	
    
    
}

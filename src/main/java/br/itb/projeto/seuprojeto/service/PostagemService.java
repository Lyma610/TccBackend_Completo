package br.itb.projeto.seuprojeto.service;
 
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
 
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
 
import br.itb.projeto.seuprojeto.model.entity.Categoria;
import br.itb.projeto.seuprojeto.model.entity.Genero;
import br.itb.projeto.seuprojeto.model.entity.Postagem;
import br.itb.projeto.seuprojeto.model.repository.CategoriaRepository;
import br.itb.projeto.seuprojeto.model.repository.GeneroRepository;
import br.itb.projeto.seuprojeto.model.repository.PostagemRepository;
import br.itb.projeto.seuprojeto.model.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
 
@Service
public class PostagemService {
 
	private PostagemRepository postagemRepository;
	private UsuarioRepository usuarioRepository;
	private CategoriaRepository categoriaRepository;
	private GeneroRepository generoRepository;
 
	public PostagemService(PostagemRepository postagemRepository, UsuarioRepository usuarioRepository,
			CategoriaRepository categoriaRepository, GeneroRepository generoRepository) {
		super();
		this.postagemRepository = postagemRepository;
		this.usuarioRepository = usuarioRepository;
		this.categoriaRepository = categoriaRepository;
		this.generoRepository = generoRepository;
	}
 
	public Postagem findById(long id) {
		Optional<Postagem> postagem = postagemRepository.findById(id);
		if (postagem.isPresent()) {
			return postagem.get();
		}
		return null;
	}
 
	public List<Postagem> findAll() {
		List<Postagem> promocoes = postagemRepository.findAll();
		return promocoes;
	}
 
	public List<Postagem> findAllAtivos() {
		List<Postagem> promocoes = postagemRepository.findByStatusPostagem("ATIVO");
		return promocoes;
	}
 
	@Transactional
	public Postagem create(MultipartFile file, Postagem postagem) {
 
		if (file != null && file.getSize() > 0) {
			try {
				postagem.setConteudo(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			postagem.setConteudo(null);
		}
		postagem.setDataCadastro(LocalDateTime.now());
		postagem.setStatusPostagem("ATIVO");
 
		return postagemRepository.save(postagem);
	}
 
	@Transactional
	public Postagem inativar(long id) {
 
		Optional<Postagem> _postagem = postagemRepository.findById(id);
 
		if (_postagem.isPresent()) {
			Postagem postagem = _postagem.get();
			postagem.setDataCadastro(LocalDateTime.now());
			postagem.setStatusPostagem("INATIVO");
 
			return postagemRepository.save(postagem);
		}
 
		return null;
	}

	@Transactional
	public void delete(long id) {
		Optional<Postagem> postagem = postagemRepository.findById(id);
		if (postagem.isPresent()) {
			postagemRepository.deleteById(id);
		} else {
			throw new RuntimeException("Postagem não encontrada com o ID: " + id);
		}
	}
	
	public List<Genero> findGenerosByCategoria(long idCategoria) {
	    Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);
	    if (categoria.isPresent()) {
	        return generoRepository.findByCategoria(categoria.get());
	    }
	    return Collections.emptyList();
	}
 
	public List<Postagem> findByCategoria(long idCategoria) {
 
		Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);
 
		if (categoria.isPresent()) {
 
			List<Postagem> postagens = postagemRepository.findByCategoria(categoria);
			return postagens;
		}
		return null;
	}
	
	public List<Postagem> findByGenero(long idGenero) {
 
		Optional<Genero> genero = generoRepository.findById(idGenero);
 
		if (genero.isPresent()) {
 
			List<Postagem> postagens = postagemRepository.findByGenero(genero);
			return postagens;
		}
		return null;
	}
	
	public List<Postagem> findByCategoriaAndGenero(long idCategoria, long idGenero) {
		Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);
 
		Optional<Genero> genero = generoRepository.findById(idGenero);
 
		if(categoria.isPresent() && genero.isPresent()) {
			List<Postagem> postagens = postagemRepository.findByCategoriaAndGenero(categoria.get(), genero.get());
			return postagens;	
		}
		return null;
	}
	
	public List<Genero> findGeneros() {
		List<Genero> generos = generoRepository.findAll();
		return generos;
	}
 
	public List<Categoria> findCategorias() {
		List<Categoria> categorias = categoriaRepository.findAll();
		return categorias;
	}
}
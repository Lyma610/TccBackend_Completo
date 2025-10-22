package br.itb.projeto.seuprojeto.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.itb.projeto.seuprojeto.model.entity.Categoria;
import br.itb.projeto.seuprojeto.model.entity.Genero;
import br.itb.projeto.seuprojeto.model.entity.Postagem;
import br.itb.projeto.seuprojeto.rest.response.MessageResponse;
import br.itb.projeto.seuprojeto.service.PostagemService;


@RestController
@RequestMapping("/postagem")
public class PostagemController {

	private PostagemService postagemService;

	// Source -> Generate Constructor using Fields...
	public PostagemController(PostagemService postagemService) {
		super();
		this.postagemService = postagemService;
	}

	@GetMapping("/test")
	public String getTest() {
		return "Olá, Postagem!";
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Postagem> findById(@PathVariable long id) {
		Postagem postagem = postagemService.findById(id);
		return new ResponseEntity<Postagem>(postagem, HttpStatus.OK);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Postagem>> findAll(){
		
		List<Postagem> postagems = postagemService.findAll();
		
		return new ResponseEntity<List<Postagem>>(postagems, HttpStatus.OK);
		
	}
	
	@GetMapping("/findGeneros")
	public ResponseEntity<List<Genero>> findGeneros(){
		
		List<Genero> generos = postagemService.findGeneros();
		
		return new ResponseEntity<List<Genero>>(generos, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/findGenerosByCategoria/{idCategoria}")
	public ResponseEntity<List<Genero>> findGenerosByCategoria(@PathVariable long idCategoria) {
	    List<Genero> generos = postagemService.findGenerosByCategoria(idCategoria);
	    return new ResponseEntity<>(generos, HttpStatus.OK);
	}
	
	@GetMapping("/findCategorias")
	public ResponseEntity<List<Categoria>> findCategorias(){
		
		List<Categoria> categorias = postagemService.findCategorias();
		
		return new ResponseEntity<List<Categoria>>(categorias, HttpStatus.OK);
		
	}
	
	@GetMapping("/findByCategoria/{idCategoria}")
	public ResponseEntity<List<Postagem>> findByCategoria(@PathVariable long idCategoria){
		
		List<Postagem> postagems = postagemService.findByCategoria(idCategoria);
		
		return new ResponseEntity<List<Postagem>>(postagems, HttpStatus.OK);
		
	}
	

	@GetMapping("/image/{id}")
	public ResponseEntity<byte[]> getPostImage(@PathVariable long id) {
	    Postagem postagem = postagemService.findById(id);
	    
	    if (postagem != null && postagem.getConteudo() != null) {
	        return ResponseEntity.ok()
	                .contentType(org.springframework.http.MediaType.IMAGE_JPEG)
	                .body(postagem.getConteudo());
	    }
	    
	    return ResponseEntity.notFound().build();
	}

	@GetMapping("/findByGenero/{idGenero}")
	public ResponseEntity<List<Postagem>> findByGenero(@PathVariable long idGenero){
		
		List<Postagem> postagems = postagemService.findByGenero(idGenero);
		
		return new ResponseEntity<List<Postagem>>(postagems, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/findByCategoriaAndGenero/{idCategoria}/{idGenero}")
	public ResponseEntity<List<Postagem>> findByCategoriaAndGenero(@PathVariable long idCategoria, @PathVariable long idGenero){
		
		List<Postagem> postagems = postagemService.findByCategoriaAndGenero(idCategoria, idGenero);
		
		return new ResponseEntity<List<Postagem>>(postagems, HttpStatus.OK);
		
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> create(
			@RequestParam(required = false) MultipartFile file,
			@ModelAttribute Postagem postagem) {

		postagemService.create(file, postagem);

		return ResponseEntity.ok()
				.body(new MessageResponse("Produto cadastrado com sucesso!"));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		try {
			Postagem postagem = postagemService.findById(id);
			if (postagem == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new MessageResponse("Postagem não encontrada"));
			}
			
			postagemService.delete(id);
			return ResponseEntity.ok()
					.body(new MessageResponse("Postagem excluída com sucesso!"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new MessageResponse("Erro ao excluir postagem: " + e.getMessage()));
		}

	}
	}






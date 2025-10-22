package br.itb.projeto.seuprojeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.itb.projeto.seuprojeto.model.entity.Categoria;
import br.itb.projeto.seuprojeto.model.repository.CategoriaRepository;
import jakarta.transaction.Transactional;

@Service
public class CategoriaService {
	
	private CategoriaRepository categoriaRepository;

	public CategoriaService(CategoriaRepository categoriaRepository) {
		super();
		this.categoriaRepository = categoriaRepository;
	}
	
	public Categoria findById(long id) {

		Optional<Categoria> categoria = categoriaRepository.findById(id);

		if (categoria.isPresent()) {
			return categoria.get();
		}

		return null;
	}
	
	public List<Categoria> findAll(){
		List<Categoria> categorias = categoriaRepository.findAll();
	
		return categorias;
	}
	
	@Transactional
	public Categoria save(Categoria categoria){
		
		categoria.setStatusCategoria("ATIVO");
		return categoriaRepository.save(categoria);
	}
	
	public Categoria atualizar(long id, Categoria categoria){
		Optional<Categoria> _categoria = categoriaRepository.findById(id);
		
		if (_categoria.isPresent()) {
			Categoria categoriaAtualizada = _categoria.get();
			
			categoriaAtualizada.setNome(categoria.getNome());
			categoriaAtualizada.setNome(categoria.getDescricao());
			categoriaAtualizada.setNome(categoria.getStatusCategoria());
			
			return categoriaRepository.save(categoriaAtualizada);
		}
		return null;
	}
}






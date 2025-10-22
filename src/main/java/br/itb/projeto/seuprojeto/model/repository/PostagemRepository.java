package br.itb.projeto.seuprojeto.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.itb.projeto.seuprojeto.model.entity.Categoria;
import br.itb.projeto.seuprojeto.model.entity.Genero;
import br.itb.projeto.seuprojeto.model.entity.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>{

	List<Postagem> findByStatusPostagem(String string);

	List<Postagem> findByCategoria(Optional<Categoria> categoria);
	
	List<Postagem> findByGenero(Optional<Genero> genero);
	
	List<Postagem> findByCategoriaAndGenero(Categoria categoria, Genero genero);

}

package br.itb.projeto.seuprojeto.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.itb.projeto.seuprojeto.model.entity.Genero;
import br.itb.projeto.seuprojeto.model.entity.Categoria;

public interface GeneroRepository 
				 extends JpaRepository<Genero, Long> {
	@Query("SELECT g FROM Genero g WHERE g.categoria = :categoria")
    List<Genero> findByCategoria(@Param("categoria") Categoria categoria);

}

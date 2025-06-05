package com.upsolucoes.gestaocomercial.repository.categoria;

import com.upsolucoes.gestaocomercial.model.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}

package br.com.pizzaria.repository;

import br.com.pizzaria.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque,Long> {
}

package br.com.pizzaria.repository;

import br.com.pizzaria.entity.Sabor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaborRepository extends JpaRepository<Sabor,Long> {
}

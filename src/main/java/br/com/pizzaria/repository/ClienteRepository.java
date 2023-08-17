package br.com.pizzaria.repository;

import br.com.pizzaria.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    Cliente findByNome(String nome);
}

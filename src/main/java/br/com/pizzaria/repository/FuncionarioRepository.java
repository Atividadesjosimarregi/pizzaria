package br.com.pizzaria.repository;

import br.com.pizzaria.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {
}

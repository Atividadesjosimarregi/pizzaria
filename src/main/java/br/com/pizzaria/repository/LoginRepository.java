package br.com.pizzaria.repository;

import br.com.pizzaria.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login,Long> {
    Login findByLoginn(String loginn);
}

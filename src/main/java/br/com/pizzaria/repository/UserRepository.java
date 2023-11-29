package br.com.pizzaria.repository;

import br.com.pizzaria.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    public Optional<User> findByUsername(String login);
}

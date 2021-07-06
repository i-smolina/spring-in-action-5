package ru.smolina.repositories.jpa;

import org.springframework.data.repository.CrudRepository;

import ru.smolina.domains.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(String username);
}

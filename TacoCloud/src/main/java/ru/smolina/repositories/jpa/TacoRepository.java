package ru.smolina.repositories.jpa;

import org.springframework.data.repository.CrudRepository;

import ru.smolina.domains.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long>{

}

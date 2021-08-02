package ru.smolina.repositories.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import ru.smolina.domains.Taco;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long>{

}

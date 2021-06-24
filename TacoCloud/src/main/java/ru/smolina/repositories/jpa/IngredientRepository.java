package ru.smolina.repositories.jpa;

import org.springframework.data.repository.CrudRepository;

import ru.smolina.domains.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String>{

}

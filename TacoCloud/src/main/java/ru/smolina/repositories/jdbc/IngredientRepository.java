package ru.smolina.repositories.jdbc;

import ru.smolina.domains.Ingredient;

public interface IngredientRepository {

	Iterable<Ingredient> findAll();

	Ingredient findOne(String id);

	Ingredient save(Ingredient ingredient);

}

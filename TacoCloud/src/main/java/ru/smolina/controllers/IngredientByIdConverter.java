package ru.smolina.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ru.smolina.domains.Ingredient;
import ru.smolina.repositories.jdbc.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {	
	
	private final IngredientRepository ingredientRepo;
	
	@Autowired
	public IngredientByIdConverter(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}

	@Override
	public Ingredient convert(String id) {
		return ingredientRepo.findOne(id);
	}
}

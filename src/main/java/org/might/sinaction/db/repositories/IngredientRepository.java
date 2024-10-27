package org.might.sinaction.db.repositories;

import org.might.sinaction.db.entity.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}

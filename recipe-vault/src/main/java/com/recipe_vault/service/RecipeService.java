package com.recipe_vault.service;

import com.recipe_vault.model.Recipe;
import com.recipe_vault.model.Ingredient;
import com.recipe_vault.model.Difficulty;
import com.recipe_vault.repository.IngredientRepository;
import com.recipe_vault.repository.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }
    

    @Transactional
    public Recipe createRecipe(Recipe recipe) {
        Recipe savedRecipe = recipeRepository.save(recipe);

        
        if (recipe.getIngredients() != null && !recipe.getIngredients().isEmpty()) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.setRecipe(savedRecipe); // Associate ingredient with recipe
                ingredientRepository.save(ingredient); // Save ingredient to DB
            }
        }
    return savedRecipe;
}


  
    @Transactional
    public Recipe updateRecipe(Long id, Recipe updatedRecipe) {
        if (recipeRepository.existsById(id)) {
            updatedRecipe.setId(id);
            return recipeRepository.save(updatedRecipe);
        }
        throw new RuntimeException("Recipe not found");
    }

    @Transactional
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
}

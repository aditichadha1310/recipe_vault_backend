package com.recipe_vault.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ingredientName;

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    @JsonBackReference 
    private Recipe recipe;


    // Constructors
    public Ingredient() {}

    public Ingredient(String ingredientName, Recipe recipe) {
        this.ingredientName = ingredientName;
        this.recipe = recipe;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIngredientName() { return ingredientName; }
    public void setIngredientName(String ingredientName) { this.ingredientName = ingredientName; }

    public Recipe getRecipe() { return recipe; }
    public void setRecipe(Recipe recipe) { this.recipe = recipe; }
}


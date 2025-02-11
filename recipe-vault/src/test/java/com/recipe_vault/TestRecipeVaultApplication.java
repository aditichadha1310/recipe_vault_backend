package com.recipe_vault;

import org.springframework.boot.SpringApplication;

public class TestRecipeVaultApplication {

	public static void main(String[] args) {
		SpringApplication.from(RecipeVaultApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

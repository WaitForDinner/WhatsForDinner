package com.ecse428.WaitForDinner.service;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.ecse428.WaitForDinner.model.Allergen;
import com.ecse428.WaitForDinner.model.DietType;
import com.ecse428.WaitForDinner.model.Ingredient;
import com.ecse428.WaitForDinner.model.Need;
import com.ecse428.WaitForDinner.model.Recipe;

/**
 * Utility class for filtering recipes based on dietary and allergen constraints.
 */
public final class RecipeFilterUtil {

    private RecipeFilterUtil() {}

    /**
     * Filters recipes to those matching at least one allowed diet type
     * and containing no ingredients with forbidden allergens.
     *
     * @author Bilguun Tegshbayar
     * @param recipes              recipes to filter
     * @param allowedDietTypes     diet type names that are permitted (e.g. "Vegan")
     * @param forbiddenAllergens   allergen names to exclude (e.g. "Peanut")
     * @param ingredientAllergens  function resolving an ingredient to its allergens
     * @return filtered list of matching recipes
     */
    public static List<Recipe> filterRecipes(
            List<Recipe> recipes,
            Set<String> allowedDietTypes,
            Set<String> forbiddenAllergens,
            Function<Ingredient, List<Allergen>> ingredientAllergens) {

        return recipes.stream()
                .filter(recipe -> hasAllowedDietType(recipe, allowedDietTypes))
                .filter(recipe -> hasNoForbiddenAllergens(recipe, forbiddenAllergens, ingredientAllergens))
                .collect(Collectors.toList());
    }

    private static boolean hasAllowedDietType(Recipe recipe, Set<String> allowedDietTypes) {
        return recipe.getDietTypes().stream()
                .map(DietType::getName)
                .anyMatch(allowedDietTypes::contains);
    }

    private static boolean hasNoForbiddenAllergens(
            Recipe recipe,
            Set<String> forbiddenAllergens,
            Function<Ingredient, List<Allergen>> ingredientAllergens) {

        return recipe.getNeeds().stream()
                .map(Need::getIngredient)
                .filter(ingredient -> ingredient != null)
                .noneMatch(ingredient -> hasForbiddenAllergen(ingredient, forbiddenAllergens, ingredientAllergens));
    }

    private static boolean hasForbiddenAllergen(
            Ingredient ingredient,
            Set<String> forbiddenAllergens,
            Function<Ingredient, List<Allergen>> ingredientAllergens) {

        List<Allergen> allergens = ingredientAllergens.apply(ingredient);
        return allergens != null && allergens.stream()
                .map(Allergen::getName)
                .anyMatch(forbiddenAllergens::contains);
    }
}
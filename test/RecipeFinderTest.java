package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeFinderTest {

    private Map<String, RecipeFinder.Recipe> recipeDatabase;
    private Set<String> availableIngredients;

    @BeforeEach
    public void setup() {
        // Initialize the recipe database with a few sample recipes
        recipeDatabase = new HashMap<>();
        recipeDatabase.put("Pancakes", new RecipeFinder.Recipe("Pancakes", Arrays.asList("flour", "eggs", "milk", "sugar", "baking powder", "butter", "vanilla extract"), "Breakfast"));
        recipeDatabase.put("Scrambled Eggs", new RecipeFinder.Recipe("Scrambled Eggs", Arrays.asList("eggs", "milk", "butter", "salt", "pepper"), "Breakfast"));
        recipeDatabase.put("Grilled Cheese Sandwich", new RecipeFinder.Recipe("Grilled Cheese Sandwich", Arrays.asList("bread", "butter", "cheddar cheese"), "Lunch"));

        // Example available ingredients
        availableIngredients = new HashSet<>(Arrays.asList("eggs", "milk", "butter"));
    }

    @Test
    public void testSuggestRecipes() {
        // Test the suggestRecipes method
        PriorityQueue<RecipeFinder.Recipe> suggestedRecipes = RecipeFinder.suggestRecipes(recipeDatabase, availableIngredients);
        assertNotNull(suggestedRecipes);
        assertFalse(suggestedRecipes.isEmpty());

        // Check if the recipes are sorted by the number of ingredients matching
        RecipeFinder.Recipe firstRecipe = suggestedRecipes.poll();
        assertEquals("Scrambled Eggs", firstRecipe.name);
    }

    @Test
    public void testGenerateShoppingList() {
        // Test generateShoppingList method
        List<RecipeFinder.Recipe> mealPlan = new ArrayList<>();
        mealPlan.add(recipeDatabase.get("Pancakes"));
        mealPlan.add(recipeDatabase.get("Grilled Cheese Sandwich"));

        Set<String> shoppingList = RecipeFinder.generateShoppingList(mealPlan, availableIngredients);

        // Check that missing ingredients are included in the shopping list
        assertTrue(shoppingList.contains("flour"));
        assertTrue(shoppingList.contains("sugar"));
        assertTrue(shoppingList.contains("bread"));
        assertTrue(shoppingList.contains("cheddar cheese"));
        assertFalse(shoppingList.contains("eggs")); // Already available
    }

    @Test
    public void testHasMatchingIngredients() {
        // Test hasMatchingIngredients method
        RecipeFinder.Recipe recipe = recipeDatabase.get("Pancakes");
        boolean hasMatching = RecipeFinder.hasMatchingIngredients(recipe, availableIngredients);
        assertTrue(hasMatching); // Eggs, milk, and butter should match
    }

    @Test
    public void testCountMatchingIngredients() {
        // Test countMatchingIngredients method
        RecipeFinder.Recipe recipe = recipeDatabase.get("Pancakes");
        int count = RecipeFinder.countMatchingIngredients(recipe, availableIngredients);
        assertEquals(3, count); // eggs, milk, and butter should match
    }
}
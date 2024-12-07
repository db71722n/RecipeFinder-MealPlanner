import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Initialize the Recipe Database
        RecipeDatabase database = new RecipeDatabase();

        // Load recipes from the JSON file
        database.loadRecipes("recipes.json");

        // Example 1: Print all recipes
        System.out.println("All Recipes:");
        System.out.println(database);

        // Example 2: Sort recipes by Meal Type
        System.out.println("Sorted by Meal Type:");
        List<Recipe> sortedRecipes = database.sortByMealType();
        for (Recipe recipe : sortedRecipes) {
            System.out.println(recipe.getRecipeName() + " (" + recipe.getMealType() + ")");
        }

        // Example 3: Filter recipes by ingredient
        System.out.println("\nFilter Recipes by Ingredient (e.g., 'chicken'):");
        List<Recipe> chickenRecipes = database.filterByIngredient("chicken");
        for (Recipe recipe : chickenRecipes) {
            System.out.println(recipe.getRecipeName());
        }

        // Example 4: Add a new recipe
        Set<String> newIngredients = new HashSet<>(Arrays.asList("chicken breast", "broccoli", "olive oil"));
        Recipe newRecipe = new Recipe("Grilled Chicken with Broccoli", newIngredients, "Dinner");
        database.addRecipe(newRecipe);
        System.out.println("\nAdded New Recipe: " + newRecipe.getRecipeName());

        // Example 5: Save the updated recipes back to the JSON file
        database.saveRecipes("recipes.json");
    }
}

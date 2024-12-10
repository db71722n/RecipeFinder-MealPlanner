import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Initialize the Recipe Database
        RecipeDatabase database = new RecipeDatabase();

        // Load recipes from the JSON file (now using "Recipes.json")
        database.loadRecipes("Recipes.json");

        // Print all recipes
        System.out.println("All Recipes:");
        System.out.println(database);

        // Sort recipes by Meal Type (Category)
        System.out.println("Sorted by Meal Type:");
        List<Recipe> sortedRecipes = database.sortByCategory();
        for (Recipe recipe : sortedRecipes) {
            System.out.println(recipe.getName() + " (" + recipe.getCategory() + ")");
        }

        // Filter recipes by ingredient
        System.out.println("\nFilter Recipes by Ingredient (e.g., 'chicken'):");
        List<Recipe> chickenRecipes = database.filterByIngredient("chicken");
        for (Recipe recipe : chickenRecipes) {
            System.out.println(recipe.getName());
        }

        // Add a new recipe
        Set<String> newIngredients = new HashSet<>(Arrays.asList("chicken breast", "broccoli", "olive oil"));
        Recipe newRecipe = new Recipe("Grilled Chicken with Broccoli", newIngredients, "Dinner");
        database.addRecipe(newRecipe);
        System.out.println("\nAdded New Recipe: " + newRecipe.getName());

        // Save the updated recipes back to the JSON file (now using "Recipes.json")
        database.saveRecipes("Recipes.json");
    }
}

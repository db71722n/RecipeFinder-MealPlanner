import com.google.gson.*;
import java.io.*;
import java.util.*;

public class RecipeDatabase {
    private List<Recipe> recipes;

    // Constructor
    public RecipeDatabase() {
        recipes = new ArrayList<>();
    }

    // Method to load recipes from a JSON file
    public boolean loadRecipes(String filePath) {
        try (Reader reader = new FileReader(filePath)) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();

            for (JsonElement recipeElement : jsonArray) {
                JsonObject recipeObject = recipeElement.getAsJsonObject();
                String name = recipeObject.get("recipeName").getAsString();
                String mealType = recipeObject.get("mealType").getAsString();
                Set<String> ingredients = new HashSet<>();

                JsonArray ingredientsArray = recipeObject.getAsJsonArray("ingredients");
                for (JsonElement ingredientElement : ingredientsArray) {
                    ingredients.add(ingredientElement.getAsString());
                }

                Recipe recipe = new Recipe(name, ingredients, mealType);
                recipes.add(recipe);
            }
            return true;

        } catch (IOException | JsonSyntaxException e) {
            System.out.println("Error loading recipes: " + e.getMessage());
            return false;
        }
    }

    // Method to add a new recipe
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    // Method to sort recipes by meal type
    public List<Recipe> sortByMealType() {
        recipes.sort(Comparator.comparing(Recipe::getCategory)); // Meal type -> Category
        return recipes;
    }

    // Method to filter recipes by an ingredient
    public List<Recipe> filterByIngredient(String ingredient) {
        List<Recipe> filteredRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getIngredients().contains(ingredient)) {
                filteredRecipes.add(recipe);
            }
        }
        return filteredRecipes;
    }

    // Method to save the recipes back to the JSON file
    public boolean saveRecipes(String filePath) {
        try (Writer writer = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(recipes, writer);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving recipes: " + e.getMessage());
            return false;
        }
    }

    // Get all recipes
    public List<Recipe> getRecipes() {
        return recipes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Recipe recipe : recipes) {
            sb.append(recipe.toString()).append("\n\n");
        }
        return sb.toString();
    }
}

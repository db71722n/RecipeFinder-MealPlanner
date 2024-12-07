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
    public void loadRecipes(String filePath) {
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

        } catch (IOException | JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    // Method to add a new recipe
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    // Method to sort recipes by meal type
    public List<Recipe> sortByMealType() {
        recipes.sort(Comparator.comparing(Recipe::getMealType));
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
    public void saveRecipes(String filePath) {
        try (Writer writer = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(recipes, writer);
        } catch (IOException e) {
            e.printStackTrace();
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

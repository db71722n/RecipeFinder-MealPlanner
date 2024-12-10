import org.json.*;
import java.io.*;
import java.util.*;

public class RecipeDatabase {
    private List<Recipe> recipes;

    // Constructor
    public RecipeDatabase() {
        recipes = new ArrayList<>();
    }

    // Load recipes from JSON file
    public void loadRecipes(String filePath) {
        try {
            // Read the file content
            FileReader reader = new FileReader(filePath);
            StringBuilder jsonContent = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) {
                jsonContent.append((char) i);
            }
            reader.close();

            // Parse the JSON content
            JSONArray jsonArray = new JSONArray(jsonContent.toString());

            // Loop through each JSON object (representing a recipe)
            for (int j = 0; j < jsonArray.length(); j++) {
                JSONObject recipeObject = jsonArray.getJSONObject(j);
                String name = recipeObject.getString("recipeName");
                String category = recipeObject.getString("mealType");
                Set<String> ingredients = new HashSet<>();

                // Extract the ingredients array
                JSONArray ingredientsArray = recipeObject.getJSONArray("ingredients");
                for (int k = 0; k < ingredientsArray.length(); k++) {
                    ingredients.add(ingredientsArray.getString(k));
                }

                // Add the new Recipe object to the list
                recipes.add(new Recipe(name, ingredients, category));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    // Add a new recipe to the database
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    // Sort recipes by category (meal type)
    public List<Recipe> sortByCategory() {
        recipes.sort(Comparator.comparing(Recipe::getCategory));
        return recipes;
    }

    // Filter recipes by a specific ingredient
    public List<Recipe> filterByIngredient(String ingredient) {
        List<Recipe> filteredRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getIngredients().contains(ingredient)) {
                filteredRecipes.add(recipe);
            }
        }
        return filteredRecipes;
    }

    // Save the recipes back to a JSON file
    public void saveRecipes(String filePath) {
        try {
            JSONArray jsonArray = new JSONArray();

            // Loop through each recipe to convert it into a JSON object
            for (Recipe recipe : recipes) {
                JSONObject recipeObject = new JSONObject();
                recipeObject.put("recipeName", recipe.getName());
                recipeObject.put("mealType", recipe.getCategory());

                // Add the ingredients as a JSON array
                JSONArray ingredientsArray = new JSONArray(recipe.getIngredients());
                recipeObject.put("ingredients", ingredientsArray);

                // Add the recipe object to the JSON array
                jsonArray.put(recipeObject);
            }

            // Write the JSON array back to the file
            FileWriter writer = new FileWriter(filePath);
            writer.write(jsonArray.toString(4));  // Pretty-print with an indentation of 4 spaces
            writer.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    // Get all recipes (useful for printing)
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

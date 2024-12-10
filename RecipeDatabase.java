import java.io.*;
import java.util.*;

public class RecipeDatabase {
    private List<Recipe> recipes;

    // Constructor
    public RecipeDatabase() {
        recipes = new ArrayList<>();
    }

    // Load recipes from the JSON file manually
    public void loadRecipes(String filePath) {
        try {
            // Read the file content
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line.trim());
            }
            reader.close();

            // Parse the JSON content (we'll assume it's a simple list of objects)
            String jsonData = jsonContent.toString();

            // Remove the starting and ending array brackets
            jsonData = jsonData.substring(1, jsonData.length() - 1).trim();

            // Split the data by individual recipes (this assumes simple well-formed JSON objects per line)
            String[] recipesData = jsonData.split("\\},\\{");

            for (String recipeData : recipesData) {
                // Clean the recipe string (remove curly braces if needed)
                recipeData = recipeData.replaceAll("^\\{", "").replaceAll("\\}$", "");

                // Parse the recipe name, category, and ingredients
                String name = extractValue(recipeData, "recipeName");
                String category = extractValue(recipeData, "mealType");

                // Extract ingredients (simple assumption that ingredients are in square brackets)
                String ingredientsString = extractValue(recipeData, "ingredients");
                Set<String> ingredients = new HashSet<>();
                ingredientsString = ingredientsString.substring(1, ingredientsString.length() - 1); // Remove brackets
                String[] ingredientArray = ingredientsString.split(",");
                for (String ingredient : ingredientArray) {
                    ingredients.add(ingredient.trim().replaceAll("^\"|\"$", "")); // Clean quotes and spaces
                }

                // Add the recipe to the list
                recipes.add(new Recipe(name, ingredients, category));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to extract values from the JSON string
    private String extractValue(String data, String key) {
        String keyValuePattern = "\"" + key + "\": \"";
        int startIndex = data.indexOf(keyValuePattern);
        if (startIndex == -1) {
            return ""; // Return empty if key not found
        }
        startIndex += keyValuePattern.length();
        int endIndex = data.indexOf("\"", startIndex);
        return data.substring(startIndex, endIndex);
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

    // Save the recipes back to the JSON file
    public void saveRecipes(String filePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write("[\n");

            for (int i = 0; i < recipes.size(); i++) {
                Recipe recipe = recipes.get(i);
                writer.write("  {\n");
                writer.write("    \"recipeName\": \"" + recipe.getName() + "\",\n");
                writer.write("    \"mealType\": \"" + recipe.getCategory() + "\",\n");

                // Write ingredients array
                writer.write("    \"ingredients\": [");
                Iterator<String> it = recipe.getIngredients().iterator();
                while (it.hasNext()) {
                    writer.write("\"" + it.next() + "\"");
                    if (it.hasNext()) {
                        writer.write(", ");
                    }
                }
                writer.write("]\n");

                writer.write("  }");
                if (i < recipes.size() - 1) {
                    writer.write(",");
                }
                writer.write("\n");
            }

            writer.write("]");
            writer.close();
        } catch (IOException e) {
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
}

package RecipeFinder;

import java.util.Set;

public class Recipe {
    private String name;
    private Set<String> ingredients;
    private String category;

    public Recipe(String name, Set<String> ingredients, String category) {
        this.name = name;
        this.ingredients = ingredients;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Recipe: " + name + " | Category: " + category + " | Ingredients: " + ingredients;
    }
}

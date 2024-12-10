import java.util.Set;

public class Recipe {
    private String name;
    private Set<String> ingredients;
    private String category;

    // Constructor
    public Recipe(String name, Set<String> ingredients, String category) {
        this.name = name;
        this.ingredients = ingredients;
        this.category = category;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for ingredients
    public Set<String> getIngredients() {
        return ingredients;
    }

    // Getter for category
    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Recipe: " + name + " | Category: " + category + " | Ingredients: " + ingredients;
    }
}

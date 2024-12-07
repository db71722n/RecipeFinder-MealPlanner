import java.util.*;

// Recipe class to hold details about each recipe.
class Recipe {
    String name;               // Name of the recipe
    List<String> ingredients;  // Ingredients required for the recipe
    String category;           // Category of the recipe (e.g., breakfast, lunch, dinner)
    boolean isVegetarian;      // Whether the recipe is vegetarian or not

    // Constructor to initialize the recipe
    public Recipe(String name, List<String> ingredients, String category, boolean isVegetarian) {
        this.name = name;
        this.ingredients = new ArrayList<>(ingredients);
        this.category = category;
        this.isVegetarian = isVegetarian;
    }

    // toString() for easy printing of recipe details
    @Override
    public String toString() {
        return String.format("Recipe{name='%s', category='%s', ingredients=%s, vegetarian=%b}",
                name, category, ingredients, isVegetarian);
    }
}

// Recipe Finder and Meal Planner Class
public class RecipeFinder {
    // HashMap to store recipes categorized by their name for quick lookup
    private Map<String, Recipe> recipeMap;
    // HashMap to store recipes by their category (e.g., breakfast, lunch, dinner)
    private Map<String, List<Recipe>> categoryMap;

    // Constructor to initialize the recipe finder with some default recipes
    public RecipeFinder() {
        recipeMap = new HashMap<>();
        categoryMap = new HashMap<>();
        initializeRecipes();
    }

    // Sample recipe data
    private void initializeRecipes() {
        // Existing recipes
        addRecipe("Vegetarian Pizza", Arrays.asList("Flour", "Tomato", "Cheese", "Olives"), "Dinner", true);
        addRecipe("Chicken Salad", Arrays.asList("Chicken", "Lettuce", "Tomato", "Cucumber", "Olives"), "Lunch", false);
        addRecipe("Omelette", Arrays.asList("Eggs", "Cheese", "Onion", "Tomato"), "Breakfast", false);
        addRecipe("Vegetarian Pasta", Arrays.asList("Pasta", "Tomato", "Garlic", "Olives"), "Dinner", true);

        // New recipes
        addRecipe("Spaghetti and Meatballs", Arrays.asList("Spaghetti", "Ground Beef", "Onion", "Garlic", "Tomato Sauce", "Parmesan Cheese"), "Dinner", false);
        addRecipe("Jerk Chicken and Veggies", Arrays.asList("Chicken", "Jerk Seasoning", "Bell Peppers", "Red Onion", "Zucchini", "Sweet Potato", "Lime"), "Dinner", false);
        addRecipe("Hamburger and Fries", Arrays.asList("Ground Beef", "Burger Buns", "Lettuce", "Tomato", "Pickles", "Potatoes", "Cheese"), "Lunch/Dinner", false);
        addRecipe("Hot Honey Chicken Tenders", Arrays.asList("Chicken Tenders", "Flour", "Hot Sauce", "Honey", "Garlic Powder", "Cornstarch", "Olive Oil"), "Dinner", false);
        addRecipe("Shrimp Ramen", Arrays.asList("Shrimp", "Ramen Noodles", "Chicken Broth", "Soy Sauce", "Garlic", "Ginger", "Green Onions", "Spinach"), "Dinner", false);
        
        // Additional recipes
        addRecipe("Chicken Alfredo Pasta", Arrays.asList("Chicken", "Fettuccine Pasta", "Heavy Cream", "Parmesan Cheese", "Garlic", "Olive Oil", "Butter", "Parsley"), "Dinner", false);
        addRecipe("Vegetarian Tacos", Arrays.asList("Corn Tortillas", "Black Beans", "Avocado", "Red Onion", "Bell Peppers", "Corn", "Cilantro", "Lime"), "Dinner", true);
        addRecipe("Baked Salmon with Garlic Butter", Arrays.asList("Salmon", "Garlic", "Lemon", "Butter", "Parsley", "Olive Oil", "Salt", "Pepper"), "Dinner", false);
        addRecipe("BBQ Pulled Pork Sandwiches", Arrays.asList("Pork Shoulder", "BBQ Sauce", "Coleslaw", "Burger Buns", "Pickles", "Onion"), "Lunch/Dinner", false);
        addRecipe("Veggie Stir-Fry", Arrays.asList("Broccoli", "Bell Peppers", "Carrots", "Snow Peas", "Tofu", "Soy Sauce", "Sesame Oil", "Ginger", "Garlic"), "Dinner", true);
        addRecipe("Beef Stew", Arrays.asList("Beef Stew Chunks", "Carrots", "Potatoes", "Celery", "Onion", "Garlic", "Beef Broth", "Tomato Paste", "Bay Leaves", "Thyme"), "Dinner", false);
        addRecipe("Pork Schnitzel", Arrays.asList("Pork Chops", "Flour", "Eggs", "Bread Crumbs", "Lemon", "Parsley", "Olive Oil"), "Dinner", false);
        addRecipe("Vegan Buddha Bowl", Arrays.asList("Quinoa", "Chickpeas", "Avocado", "Sweet Potatoes", "Spinach", "Tahini Dressing", "Lemon", "Olive Oil"), "Lunch/Dinner", true);
        addRecipe("Margherita Pizza", Arrays.asList("Pizza Dough", "Tomato Sauce", "Fresh Mozzarella", "Fresh Basil", "Olive Oil", "Garlic", "Salt", "Pepper"), "Dinner", true);
        addRecipe("Chicken Parmesan", Arrays.asList("Chicken Breasts", "Bread Crumbs", "Eggs", "Parmesan Cheese", "Mozzarella Cheese", "Marinara Sauce", "Basil"), "Dinner", false);
        addRecipe("Vegetable Lasagna", Arrays.asList("Lasagna Noodles", "Ricotta Cheese", "Spinach", "Zucchini", "Mushrooms", "Marinara Sauce", "Mozzarella Cheese", "Parmesan Cheese"), "Dinner", true);
    }

    // Method to add a recipe to the system
    private void addRecipe(String name, List<String> ingredients, String category, boolean isVegetarian) {
        Recipe recipe = new Recipe(name, ingredients, category, isVegetarian);
        recipeMap.put(name, recipe);

        // Add recipe to the category-based map
        categoryMap.putIfAbsent(category, new ArrayList<>());
        categoryMap.get(category).add(recipe);
    }

    // Method to search recipes based on available ingredients
    public List<Recipe> searchRecipes(List<String> availableIngredients) {
        List<Recipe> matchingRecipes = new ArrayList<>();
        
        // Iterate over all recipes and check if the ingredients are available
        for (Recipe recipe : recipeMap.values()) {
            // Check if available ingredients match recipe ingredients
            if (availableIngredients.containsAll(recipe.ingredients)) {
                matchingRecipes.add(recipe);
            }
        }
        return matchingRecipes;
    }

    // Method to plan meals for the week
    public void planMeals(List<String> preferences) {
        // Prioritize recipes based on preferences (e.g., vegetarian vs non-vegetarian)
        PriorityQueue<Recipe> prioritizedMeals = new PriorityQueue<>(Comparator.comparingBoolean(r -> !r.isVegetarian()));
        
        // Add all recipes to the priority queue based on preference
        for (Recipe recipe : recipeMap.values()) {
            if (preferences.contains("vegetarian") && recipe.isVegetarian) {
                prioritizedMeals.add(recipe);
            } else if (!preferences.contains("vegetarian") && !recipe.isVegetarian) {
                prioritizedMeals.add(recipe);
            }
        }

        System.out.println("Meal Plan for the Week:");
        for (Recipe meal : prioritizedMeals) {
            System.out.println(meal);
        }
    }

    // Method to generate a shopping list based on selected recipes
    public Set<String> generateShoppingList(List<Recipe> selectedRecipes) {
        Set<String> shoppingList = new HashSet<>();
        
        // Add ingredients from each selected recipe to the shopping list
        for (Recipe recipe : selectedRecipes) {
            shoppingList.addAll(recipe.ingredients);
        }

        return shoppingList;
    }

    // Method to display all recipes
    public void displayAllRecipes() {
        System.out.println("All Recipes:");
        for (Recipe recipe : recipeMap.values()) {
            System.out.println(recipe);
        }
    }

    public static void main(String[] args) {
        RecipeFinder recipeFinder = new RecipeFinder();

        // Display all recipes
        recipeFinder.displayAllRecipes();

        // Example: Search for recipes based on available ingredients
        List<String> availableIngredients = Arrays.asList("Flour", "Tomato", "Cheese", "Olives");
        System.out.println("\nMatching Recipes based on Ingredients:");
        List<Recipe> foundRecipes = recipeFinder.searchRecipes(availableIngredients);
        for (Recipe recipe : foundRecipes) {
            System.out.println(recipe);
        }

        // Example: Plan meals for the week based on vegetarian preference
        List<String> preferences = Arrays.asList("vegetarian");
        recipeFinder.planMeals(preferences);

        // Example: Generate a shopping list from selected recipes
        System.out.println("\nShopping List for Selected Recipes:");
        Set<String> shoppingList = recipeFinder.generateShoppingList(foundRecipes);
        for (String ingredient : shoppingList) {
            System.out.println(ingredient);
        }
    }
}

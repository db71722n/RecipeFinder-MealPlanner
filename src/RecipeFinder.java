package src;
import java.util.*;

public class RecipeFinder {

    // recipe class to store recipe details
    static class Recipe {
        String name;
        List<String> ingredients;
        String category;

        Recipe(String name, List<String> ingredients, String category) {
            this.name = name;
            this.ingredients = ingredients;
            this.category = category;
        }

        @Override
        public String toString() {
            return name + " (" + category + ") - Ingredients: " + ingredients;
        }
    }

    // Main RecipeFinder class
    public static void main(String[] args) {
        // initialize recipes
        Map<String, Recipe> recipeDatabase = new HashMap<>();
        recipeDatabase.put("Pancakes", new Recipe("Pancakes", Arrays.asList("flour", "eggs", "milk", "sugar", "baking powder", "butter", "vanilla extract"), "Breakfast"));
        recipeDatabase.put("Scrambled Eggs", new Recipe("Scrambled Eggs", Arrays.asList("eggs", "milk", "butter", "salt", "pepper"), "Breakfast"));
        recipeDatabase.put("Omelette", new Recipe("Omelette", Arrays.asList("eggs", "cheese", "mushrooms", "onions", "spinach", "bell peppers", "salt", "pepper"), "Breakfast"));
        recipeDatabase.put("Avocado Toast", new Recipe("Avocado Toast", Arrays.asList("bread", "avocado", "olive oil", "lemon juice", "salt", "pepper"), "Breakfast"));
        recipeDatabase.put("Smoothie Bowl", new Recipe("Smoothie Bowl", Arrays.asList("frozen berries", "banana", "yogurt", "granola", "chia seeds"), "Breakfast"));
        recipeDatabase.put("Chia Pudding", new Recipe("Chia Pudding", Arrays.asList("chia seeds", "almond milk", "honey", "vanilla extract"), "Breakfast"));
        recipeDatabase.put("Bagel with Cream Cheese", new Recipe("Bagel with Cream Cheese", Arrays.asList("bagels", "cream cheese", "smoked salmon", "capers", "red onion"), "Breakfast"));
        recipeDatabase.put("French Toast", new Recipe("French Toast", Arrays.asList("bread", "eggs", "milk", "cinnamon", "vanilla extract", "butter", "maple syrup"), "Breakfast"));
        recipeDatabase.put("Breakfast Burrito", new Recipe("Breakfast Burrito", Arrays.asList("flour tortillas", "eggs", "cheese", "sausage", "bell peppers", "onions"), "Breakfast"));
        recipeDatabase.put("Granola Parfait", new Recipe("Granola Parfait", Arrays.asList("granola", "yogurt", "honey", "fresh berries"), "Breakfast"));
        recipeDatabase.put("Bacon Egg and Cheese", new Recipe("Bacon Egg and Cheese", Arrays.asList("bacon", "egg", "cheese", "bagel", "salt", "pepper"), "Breakfast"));
        
        recipeDatabase.put("Caesar Salad", new Recipe("Caesar Salad", Arrays.asList("romaine lettuce", "Caesar dressing", "croutons", "parmesan cheese", "grilled chicken"), "Lunch"));
        recipeDatabase.put("Chicken Wrap", new Recipe("Chicken Wrap", Arrays.asList("flour tortillas", "grilled chicken", "lettuce", "tomatoes", "cheese", "ranch dressing"), "Lunch"));
        recipeDatabase.put("Tomato Soup", new Recipe("Tomato Soup", Arrays.asList("tomatoes", "garlic", "onion", "vegetable broth", "cream", "basil"), "Lunch"));
        recipeDatabase.put("Grilled Cheese Sandwich", new Recipe("Grilled Cheese Sandwich", Arrays.asList("bread", "butter", "cheddar cheese"), "Lunch"));
        recipeDatabase.put("Caprese Salad", new Recipe("Caprese Salad", Arrays.asList("tomatoes", "mozzarella cheese", "basil", "olive oil", "balsamic vinegar"), "Lunch"));
        recipeDatabase.put("Quinoa Salad", new Recipe("Quinoa Salad", Arrays.asList("quinoa", "cucumber", "tomato", "feta cheese", "olives", "lemon dressing"), "Lunch"));
        recipeDatabase.put("Chicken Caesar Wrap", new Recipe("Chicken Caesar Wrap", Arrays.asList("flour tortillas", "grilled chicken", "romaine lettuce", "Caesar dressing", "parmesan cheese"), "Lunch"));
        recipeDatabase.put("Vegetarian Burrito", new Recipe("Vegetarian Burrito", Arrays.asList("flour tortillas", "rice", "black beans", "corn", "avocado", "salsa", "lettuce", "cheese"), "Lunch"));
        recipeDatabase.put("Avocado Chicken Salad", new Recipe("Avocado Chicken Salad", Arrays.asList("chicken breast", "avocado", "celery", "lemon", "Greek yogurt", "mustard"), "Lunch"));
        recipeDatabase.put("Falafel", new Recipe("Falafel", Arrays.asList("chickpeas", "garlic", "onions", "parsley", "cumin", "coriander", "tahini"), "Lunch"));
        recipeDatabase.put("Pepperoni Pizza", new Recipe("Pepporoni Pizza", Arrays.asList("pizza dough", "marinara", "cheese", "pepperoni"), "Lunch"));

        recipeDatabase.put("Spaghetti Carbonara", new Recipe("Spaghetti Carbonara", Arrays.asList("spaghetti", "eggs", "pancetta", "parmesan cheese", "black pepper"), "Dinner"));
        recipeDatabase.put("Chicken Alfredo", new Recipe("Chicken Alfredo", Arrays.asList("chicken breast", "pasta", "heavy cream", "garlic", "parmesan cheese", "butter"), "Dinner"));
        recipeDatabase.put("Beef Tacos", new Recipe("Beef Tacos", Arrays.asList("ground beef", "taco shells", "lettuce", "cheese", "salsa", "sour cream"), "Dinner"));
        recipeDatabase.put("Grilled Salmon", new Recipe("Grilled Salmon", Arrays.asList("salmon fillets", "olive oil", "garlic", "lemon", "herbs"), "Dinner"));
        recipeDatabase.put("Chicken Stir Fry", new Recipe("Chicken Stir Fry", Arrays.asList("chicken breast", "bell peppers", "broccoli", "soy sauce", "garlic", "ginger"), "Dinner"));
        recipeDatabase.put("Eggplant Parmesan", new Recipe("Eggplant Parmesan", Arrays.asList("eggplant", "marinara sauce", "mozzarella cheese", "parmesan cheese", "breadcrumbs"), "Dinner"));
        recipeDatabase.put("Chili", new Recipe("Chili", Arrays.asList("ground beef", "beans", "tomatoes", "onion", "garlic", "chili powder"), "Dinner"));
        recipeDatabase.put("Chicken Parmesan", new Recipe("Chicken Parmesan", Arrays.asList("chicken breast", "marinara sauce", "mozzarella cheese", "parmesan cheese", "breadcrumbs"), "Dinner"));
        recipeDatabase.put("Vegetable Curry", new Recipe("Vegetable Curry", Arrays.asList("mixed vegetables", "coconut milk", "curry powder", "garlic", "onion", "ginger"), "Dinner"));
        recipeDatabase.put("Pork Schnitzel", new Recipe("Pork Schnitzel", Arrays.asList("pork chops", "breadcrumbs", "eggs", "flour", "lemon", "butter"), "Dinner"));
        recipeDatabase.put("Cheeseburger and Fries", new Recipe("Cheeseburger and Fries", Arrays.asList("ground beef", "cheese", "hamburger buns", "onions", "ketchup", "mustard", "french fries"), "Dinner"));
        // user input for available ingredients
        Set<String> availableIngredients = getUserIngredients();

        // suggest recipes based on available ingredients using a PriorityQueue
        PriorityQueue<Recipe> suggestedRecipes = suggestRecipes(recipeDatabase, availableIngredients);

        if (suggestedRecipes.isEmpty()) {
            System.out.println("I'm sorry. I do not have any recipes for that ingredient.");
        } else {
            System.out.println("\nSuggested Recipes based on available ingredients:");
            List<Recipe> suggestedRecipeList = new ArrayList<>();
            while (!suggestedRecipes.isEmpty()) {
                Recipe recipe = suggestedRecipes.poll();
                suggestedRecipeList.add(recipe);
                System.out.println(suggestedRecipeList.size() + ". " + recipe);
            }

            // ask user to select recipes for the meal plan
            List<Recipe> mealPlan = new ArrayList<>();
            Scanner scanner = new Scanner(System.in);  // Declare once and use in main method
            System.out.println("\nPlease select the recipes you'd like to add to your meal plan.");
            System.out.println("Enter the recipe numbers (comma separated) or type 'done' to finish:");

            while (true) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("done")) {
                    break;
                }

                String[] selectedIndices = input.split(",");
                for (String indexStr : selectedIndices) {
                    try {
                        int index = Integer.parseInt(indexStr.trim()) - 1;
                        if (index >= 0 && index < suggestedRecipeList.size()) {
                            mealPlan.add(suggestedRecipeList.get(index));
                        } else {
                            System.out.println("Invalid number: " + indexStr);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter numbers separated by commas.");
                    }
                }
            }

            // generate a shopping list for missing ingredients
            Set<String> shoppingList = generateShoppingList(mealPlan, availableIngredients);

            // display selected meal plan
            System.out.println("\nYour Meal Plan:");
            for (Recipe recipe : mealPlan) {
                System.out.println(recipe);
            }

            // display shopping list for missing ingredients
            System.out.println("\nShopping List (missing ingredients you need):");
            for (String ingredient : shoppingList) {
                System.out.println(ingredient);
            }

            // close scanner
            scanner.close();
        }
    }

    // get user input for available ingredients
    public static Set<String> getUserIngredients() {
        Set<String> ingredients = new HashSet<>();
        Scanner scanner = new Scanner(System.in);  // Declare scanner here

        System.out.println("Please enter the ingredients you have one at a time (type 'done' when finished):");
        while (true) {
            System.out.print("Ingredient: ");
            String ingredient = scanner.nextLine().trim().toLowerCase();
            if (ingredient.equals("done")) {
                break;
            }
            ingredients.add(ingredient);
        }

        // didn't close the scanner here to prevent closing System.in prematurely
        return ingredients;
    }

    // suggest recipes based on available ingredients
    public static PriorityQueue<Recipe> suggestRecipes(Map<String, Recipe> recipeDatabase, Set<String> availableIngredients) {
        PriorityQueue<Recipe> queue = new PriorityQueue<>((r1, r2) -> {
            int r1Matches = countMatchingIngredients(r1, availableIngredients);
            int r2Matches = countMatchingIngredients(r2, availableIngredients);
            return r2Matches - r1Matches;  // Sort by most ingredients matched
        });

        for (Recipe recipe : recipeDatabase.values()) {
            if (hasMatchingIngredients(recipe, availableIngredients)) {
                queue.offer(recipe);
            }
        }

        return queue;
    }

    // check if a recipe has any matching ingredients
    public static boolean hasMatchingIngredients(Recipe recipe, Set<String> availableIngredients) {
        for (String ingredient : recipe.ingredients) {
            if (availableIngredients.contains(ingredient)) {
                return true;
            }
        }
        return false;
    }

    // count matching ingredients between a recipe and the available ingredients
    public static int countMatchingIngredients(Recipe recipe, Set<String> availableIngredients) {
        int matchCount = 0;
        for (String ingredient : recipe.ingredients) {
            if (availableIngredients.contains(ingredient)) {
                matchCount++;
            }
        }
        return matchCount;
    }

    // generate a shopping list for missing ingredients
    public static Set<String> generateShoppingList(List<Recipe> mealPlan, Set<String> availableIngredients) {
        Set<String> shoppingList = new HashSet<>();
        
        for (Recipe recipe : mealPlan) {
            for (String ingredient : recipe.ingredients) {
                if (!availableIngredients.contains(ingredient)) {
                    shoppingList.add(ingredient);
                }
            }
        }

        return shoppingList;
    }
}

package model.implementation.day;

import model.implementation.AbstractFoodCollection;

/**
 * Represents a meal, which consists of a collection of food all food's macro
 * nutrients totaled and the type of meal it is.
 * 
 * @author Garrett Presley
 *
 */
public class Meal extends AbstractFoodCollection<Food> {

	// Meal Type (MT) constants -------------------

	/** Breakfast meal type string */
	public static final String MT_BREAKFAST = "Breakfast";
	/** Lunch meal type string */
	public static final String MT_LUNCH = "Lunch";
	/** Dinner meal type string */
	public static final String MT_DINNER = "Dinner";
	/** Snack meal type string */
	public static final String MT_SNACK = "Snack";

	// ---------------------------------------

	/** The name of the meal's food collection */
	private static final String FOOD_COLLECTION_NAME = "foods";

	/** The type of the meal */
	private String type;
	/** Counter of the foods that causes the user stomach issues. */
	private int foodsThatContainStomachIssues;

	/**
	 * A full constructor setting all fields.
	 * 
	 * @param type                          the type of meal to set
	 * @param calories                      the number of calories to set
	 * @param protein                       the amount of protein to set
	 * @param fat                           the amount of fat to set
	 * @param carbs                         the amount of carbs to set
	 * @param saturatedFat                  the amount of saturated fat to set
	 * @param transFat                      the amount of trans fat to set
	 * @param fiber                         the amount of fiber to set
	 * @param sugar                         the amount of sugar to set
	 * @param sodium                        the amount of sodium to set
	 * @param cholesterol                   the amount of cholesterol to set
	 * @param calcium                       the amount of calcium to set
	 * @param potassium                     the amount of potassium to set
	 * @param vitaminA                      the amount of Vitamin A to set
	 * @param vitaminC                      the amount of Vitamin C to set
	 * @param vitaminD                      the amount of Vitamin D to set
	 * @param vitaminE                      the amount of Vitamin E to set
	 * @param vitaminK                      the amount of Vitamin K to set
	 * @param vitaminB1                     the amount of Vitamin B1 to set
	 * @param vitaminB2                     the amount of Vitamin B2 to set
	 * @param vitaminB3                     the amount of Vitamin B3 to set
	 * @param vitaminB5                     the amount of Vitamin B5 to set
	 * @param vitaminB6                     the amount of Vitamin B6 to set
	 * @param vitaminB7                     the amount of Vitamin B7 to set
	 * @param vitaminB9                     the amount of Vitamin B9 to set
	 * @param vitaminB12                    the amount of Vitamin B12 to set
	 * @param foodsThatContainStomachIssues the number of foods in the meal that
	 *                                      cause stomach issues
	 */
	public Meal(String type, int calories, int protein, int fat, int carbs, int saturatedFat, int transFat, int fiber,
			int sugar, int sodium, int cholesterol, int calcium, int potassium, int vitaminA, int vitaminC,
			int vitaminD, int vitaminE, int vitaminK, int vitaminB1, int vitaminB2, int vitaminB3, int vitaminB5,
			int vitaminB6, int vitaminB7, int vitaminB9, int vitaminB12, int foodsThatContainStomachIssues) {
		super(calories, protein, fat, carbs, saturatedFat, transFat, fiber, sugar, sodium, cholesterol, calcium,
				potassium, vitaminA, vitaminC, vitaminD, vitaminE, vitaminK, vitaminB1, vitaminB2, vitaminB3, vitaminB5,
				vitaminB6, vitaminB7, vitaminB9, vitaminB12);
		setType(type);
		this.foodsThatContainStomachIssues = foodsThatContainStomachIssues;
	}

	/**
	 * A constructor that only sets the type of meal, sets all other fields to 0.
	 * 
	 * @param type
	 */
	public Meal(String type) {
		this(type, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
	}

	/**
	 * Adds a food object to the list of foods in meal while also adding to the
	 * meal's nutrients totals.
	 * 
	 * @param f the food to add
	 */
	public void add(Food f) {
		addFoodMacrosToTotalMacros(f);
		if (f.causesStomachIssues())
			foodsThatContainStomachIssues++;
		getFoodCollection().addLast(f);
	}

	/**
	 * Removes a food object from the list of foods in meal while also reducing from
	 * the meal's nutrients totals, given the food's name.
	 * 
	 * @param foodName the name of the food to remove
	 * 
	 * @return the food removed
	 */
	public Food remove(String foodName) {
		Food f = getFoodCollection().remove(findFoodIndex(foodName));
		removeFoodMacrosFromTotalMacros(f);
		if (f.causesStomachIssues())
			foodsThatContainStomachIssues--;
		return f;
	}

	public Food getFood(int foodIndex) {
		return getFoodCollection().get(foodIndex);
	}

	public Food getFood(String foodName) {
		return getFoodCollection().get(findFoodIndex(foodName));
	}

	/**
	 * Finds the index of the food to remove from the food list, given the food's
	 * name.
	 * 
	 * @param foodName
	 * @return
	 */
	private int findFoodIndex(String foodName) {
		int foodIndex = -1;
		for (int i = 0; i < getFoodCollection().size(); i++) {
			if (getFoodCollection().get(i).getName().contains(foodName)) {
				foodIndex = i;
				break;
			}
		}
		if (foodIndex == -1) {
			throw new IllegalArgumentException(
					"A food item with the name of " + foodName + " does not exist in " + type + ".");
		}
		return foodIndex;
	}

	@Override
	public String toString() {
		return (type + ", " + super.toString()).replace("foodCollection", FOOD_COLLECTION_NAME);

	}

	@Override
	public String toFullString() {
		return ("Meal [type=" + type + ", foodsThatContainStomachIssues=" + foodsThatContainStomachIssues + ", "
				+ super.toFullString() + "]").replace("foodCollection", FOOD_COLLECTION_NAME);
	}

	public Food[] getFoodArray() {
		Food[] foods = new Food[getFoodCollection().size()];
		for (int i = 0; i < foods.length; i++)
			foods[i] = getFoodCollection().get(i);
		return foods;
	}

	public static int getMealTypeIndex(String type) {
		switch (type) {
		case "Breakfast":
			return 0;
		case "Lunch":
			return 1;
		case "Dinner":
			return 2;
		case "Snack":
			return 3;
		default:
			return -1;
		}
	}

	/**
	 * Sets the meal's type string.
	 * 
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Returns the meal type.
	 * 
	 * @return the type of meal
	 */
	public String getType() {
		return type;
	}
}

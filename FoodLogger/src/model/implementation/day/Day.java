
package model.implementation.day;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.implementation.AbstractFoodCollection;

/**
 * Represents a Day of food, which logs four meals breakfast, lunch, dinner, and
 * snack. Keeps up with the day's total macros. Is considered a Food Collection,
 * with that collection of Food being meals. Keeps track of the date the day was
 * constructed.
 * 
 * @author Garrett Presley
 *
 */
public class Day extends AbstractFoodCollection<Meal> {

	/** The index of the breakfast meal in the food collection */
	public static final int BREAKFAST_INDEX = 0;
	/** The index of the lunch meal in the food collection */
	public static final int LUNCH_INDEX = 1;
	/** The index of the dinner meal in the food collection */
	public static final int DINNER_INDEX = 2;
	/** The index of the snack meal in the food collection */
	public static final int SNACK_INDEX = 3;

	/** The total number of meals in the food collection */
	public static final int NUMBER_OF_MEALS = 4;

	/** The name of the Day's food collection */
	private static final String FOOD_COLLECTION_NAME = "meals";

	/** The format of the date string */
	public static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
	/** The date of the day */
	private Date date;
	/** The string representation of the date, format is yyyy-MM-dd */
	private String dateString;

	/**
	 * A full constructor.
	 * 
	 * @param date         the date to set
	 * @param calories     the number of calories to set
	 * @param protein      the amount of protein to set
	 * @param fat          the amount of fat to set
	 * @param carbs        the amount of carbs to set
	 * @param saturatedFat the amount of saturated fat to set
	 * @param transFat     the amount of trans fat to set
	 * @param fiber        the amount of fiber to set
	 * @param sugar        the amount of sugar to set
	 * @param sodium       the amount of sodium to set
	 * @param cholesterol  the amount of cholesterol to set
	 * @param calcium      the amount of calcium to set
	 * @param potassium    the amount of potassium to set
	 * @param vitaminA     the amount of Vitamin A to set
	 * @param vitaminC     the amount of Vitamin C to set
	 * @param vitaminD     the amount of Vitamin D to set
	 * @param vitaminE     the amount of Vitamin E to set
	 * @param vitaminK     the amount of Vitamin K to set
	 * @param vitaminB1    the amount of Vitamin B1 to set
	 * @param vitaminB2    the amount of Vitamin B2 to set
	 * @param vitaminB3    the amount of Vitamin B3 to set
	 * @param vitaminB5    the amount of Vitamin B5 to set
	 * @param vitaminB6    the amount of Vitamin B6 to set
	 * @param vitaminB7    the amount of Vitamin B7 to set
	 * @param vitaminB9    the amount of Vitamin B9 to set
	 * @param vitaminB12   the amount of Vitamin B12 to set
	 */
	public Day(Date date, int calories, int protein, int fat, int carbs, int saturatedFat, int transFat, int fiber,
			int sugar, int sodium, int cholesterol, int calcium, int potassium, int vitaminA, int vitaminC,
			int vitaminD, int vitaminE, int vitaminK, int vitaminB1, int vitaminB2, int vitaminB3, int vitaminB5,
			int vitaminB6, int vitaminB7, int vitaminB9, int vitaminB12) {
		super(calories, protein, fat, carbs, saturatedFat, transFat, fiber, sugar, sodium, cholesterol, calcium,
				potassium, vitaminA, vitaminC, vitaminD, vitaminE, vitaminK, vitaminB1, vitaminB2, vitaminB3, vitaminB5,
				vitaminB6, vitaminB7, vitaminB9, vitaminB12);
		getFoodCollection().addLast(new Meal(Meal.MT_BREAKFAST));
		getFoodCollection().addLast(new Meal(Meal.MT_LUNCH));
		getFoodCollection().addLast(new Meal(Meal.MT_DINNER));
		getFoodCollection().addLast(new Meal(Meal.MT_SNACK));
		this.date = date;
		dateString = SDF.format(date);
	}

	/**
	 * A constructor that only sets the date of the day. Sets all other fields to 0.
	 * 
	 * @param date the date to set
	 */
	public Day(Date date) {
		this(date, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
	}

	/**
	 * A constructor that sets date of the day to system's current day. Sets all
	 * other fields to 0.
	 */
	public Day() {
		this(new Date());
	}

	@Override
	public String toString() {
		return (dateString + ", " + super.toString()).replace("foodCollection", FOOD_COLLECTION_NAME);
	}

	@Override
	public String toFullString() {
		return ("Day [\ndate=" + dateString + ", " + super.toFullString() + "]").replace("foodCollection",
				FOOD_COLLECTION_NAME);
	}

	/**
	 * Adds a food object to the meal in the food collection located at the given
	 * index.
	 * 
	 * @param food  the food object to add
	 * @param index the index to add to
	 */
	public void addIndexFood(Food food, int index) {
		addFoodMacrosToTotalMacros(food);
		getFoodCollection().get(index).add(food);
	}

	public void addBreakFastFood(Food food) {
		addIndexFood(food, BREAKFAST_INDEX);
	}

	public void addLunchFood(Food food) {
		addIndexFood(food, LUNCH_INDEX);
	}

	public void addDinnerFood(Food food) {
		addIndexFood(food, DINNER_INDEX);
	}

	public void addSnackFood(Food food) {
		addIndexFood(food, SNACK_INDEX);
	}

	public void removeFoodFromIndexMeal(String foodName, int index) {
		removeFoodMacrosFromTotalMacros(getFoodCollection().get(index).remove(foodName));
	}

	public void removeBreakfastMeal(String foodName) {
		removeFoodFromIndexMeal(foodName, BREAKFAST_INDEX);
	}

	public void removeLunchMeal(String foodName) {
		removeFoodFromIndexMeal(foodName, LUNCH_INDEX);
	}

	public void removeDinnerMeal(String foodName) {
		removeFoodFromIndexMeal(foodName, DINNER_INDEX);
	}

	public void removeSnackMeal(String foodName) {
		removeFoodFromIndexMeal(foodName, SNACK_INDEX);
	}

	public Meal getMeal(int index) {
		return getFoodCollection().get(index);
	}

	public Food[] getMealsFoods(int mealIndex) {
		return getFoodCollection().get(mealIndex).getFoodArray();
	}

	public Date getDate() {
		return date;
	}

	public String getDateString() {
		return dateString;
	}
}

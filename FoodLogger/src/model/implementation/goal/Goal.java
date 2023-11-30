package model.implementation.goal;

import model.implementation.AbstractMacroBased;
import model.implementation.day.Day;

/**
 * Represents a nutrient based goal to reach for, is macro based.
 * 
 * @author Garrett Presley
 *
 */
public class Goal extends AbstractMacroBased {

	/** The regular goal type string */
	public static final String REGULAR_GOAL_STRING = "Regular";
	/** The athletic goal type string */
	public static final String ATHELTIC_GOAL_STRING = "Athletic";
	/** The strength goal type string */
	public static final String STRENGTH_GOAL_STRING = "Strength";
	/** The custom goal type string */
	public static final String CUSTOM_GOAL_STRING = "Custom";

	/** The low activity type string */
	public static final String ACTIVITY_LEVEL_LOW = "Low";
	/** The moderate activity type string */
	public static final String ACTIVITY_LEVEL_MODERATE = "Moderate";
	/** The high activity type string */
	public static final String ACTIVITY_LEVEL_HIGH = "Low";
	/** The low activity type string */
	public static final String ACTIVITY_LEVEL_EXTREME = "Low";

	/** The type of goal to be achieved. */
	private String type;

	/**
	 * The boolean flag to see if the goal to be met is only based on checking the
	 * basic macros.
	 */
	private boolean onlyCheckBasicMacros;

	/**
	 * A full constructor with the entirety of the fields being set. Total of 26
	 * fields.
	 * 
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
	public Goal(String type, int calories, int protein, int fat, int carbs, int saturatedFat, int transFat, int fiber,
			int sugar, int sodium, int cholesterol, int calcium, int potassium, int vitaminA, int vitaminC,
			int vitaminD, int vitaminE, int vitaminK, int vitaminB1, int vitaminB2, int vitaminB3, int vitaminB5,
			int vitaminB6, int vitaminB7, int vitaminB9, int vitaminB12, boolean onlyCheckBasicMacros) {
		super(calories, protein, fat, carbs, saturatedFat, transFat, fiber, sugar, sodium, cholesterol, calcium,
				potassium, vitaminA, vitaminC, vitaminD, vitaminE, vitaminK, vitaminB1, vitaminB2, vitaminB3, vitaminB5,
				vitaminB6, vitaminB7, vitaminB9, vitaminB12);
		setType(type);
	}

	/**
	 * Constructs a goal with a little more fields, sets the rest of the fields to
	 * zero of true.
	 * 
	 * @param type     the type of goal to set
	 * @param calories the number of calories to set
	 * @param protein  the amount of protein to set
	 * @param fat      the amount of fat to set
	 * @param carbs    the amount of carbs to set
	 */
	public Goal(String type, int calories, int protein, int fat, int carbs) {
		this(REGULAR_GOAL_STRING, calories, protein, fat, carbs, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, true);
	}

	/**
	 * Constructs a goal object with some given fields, fills the rest of the fields
	 * with zeros or true.
	 * 
	 * @param calories the number of calories to set
	 * @param protein  the amount of protein to set
	 * @param fat      the amount of fat to set
	 * @param carbs    the amount of carbs to set
	 */
	public Goal(int calories, int protein, int fat, int carbs) {
		this(REGULAR_GOAL_STRING, calories, protein, fat, carbs);
	}

	public boolean goalsMeet(Day day) {
		if (getCalories() != 0) {
			if (onlyCheckBasicMacros) {
				return day.getCalories() >= getCalories() && day.getProtein() >= getProtein()
						&& day.getFat() >= getFat() && day.getCarbs() >= getCarbs();
			} else {
				return day.getCalories() >= getCalories() && day.getProtein() >= getProtein()
						&& day.getFat() >= getFat() && day.getCarbs() >= getCarbs()
						&& day.getSaturatedFat() >= getSaturatedFat() && day.getTransFat() >= getTransFat()
						&& day.getFiber() >= getFiber() && day.getSugar() >= getSugar()
						&& day.getSodium() >= getSodium() && day.getCholesterol() >= getCholesterol()
						&& day.getCalcium() >= getCalcium() && day.getPotassium() >= getPotassium()
						&& day.getVitaminA() >= getVitaminA() && day.getVitaminC() >= getVitaminC()
						&& day.getVitaminD() >= getVitaminD() && day.getVitaminE() >= getVitaminE()
						&& day.getVitaminK() >= getVitaminK() && day.getVitaminB1() >= getVitaminB1()
						&& day.getVitaminB2() >= getVitaminB2() && day.getVitaminB3() >= getVitaminB3()
						&& day.getVitaminB5() >= getVitaminB5() && day.getVitaminB6() >= getVitaminB6()
						&& day.getVitaminB7() >= getVitaminB7() && day.getVitaminB9() >= getVitaminB9()
						&& day.getVitaminB12() >= getVitaminB12();
			}
		}
		return false;
	}

	/**
	 * Returns a string representation of a food object with large amount
	 * information.
	 * 
	 * @return a large string object
	 */
	public String toFullString() {
		return "Goal [type=" + type + ", " + super.toFullString() + ", onlyCheckBasicMacros=" + onlyCheckBasicMacros
				+ "]";
	}

	/**
	 * Determines how much macros a user should consume based on the type of goal
	 * they want to pursue, body weight, height, and their activity level.
	 * 
	 * @param typeToCalculate the type of goal to pursue
	 * @param bW              the body weight of the user in kg
	 * @param height          the height of the user in cm
	 * @param activityLevel   the activity level of the user their is low, moderate,
	 *                        high, and extreme
	 */
	public void setCalculatedGoalType(String typeToCalculate, double bW, double height, String activityLevel) {
		// TODO
	}

	/**
	 * Returns the type of goal.
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Set the type of goal.
	 * 
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Returns if onlyCheckBasicMacros is true or false.
	 * 
	 * @return onlyCheckBasicMacros boolean status
	 */
	public boolean isOnlyCheckBasicMacros() {
		return onlyCheckBasicMacros;
	}

	/**
	 * Sets the only checkBasicMacros boolean flag.
	 * 
	 * @param onlyCheckBasicMacros the boolean status to set onlyCheckBasicMacros to
	 */
	public void setOnlyCheckBasicMacros(boolean onlyCheckBasicMacros) {
		this.onlyCheckBasicMacros = onlyCheckBasicMacros;
	}

}

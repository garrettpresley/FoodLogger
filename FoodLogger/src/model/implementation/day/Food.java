package model.implementation.day;

import model.implementation.AbstractMacroBased;

/**
 * Represents a food object (which is macro based), which keeps track of
 * multiple different specific fields, including all the macros, vitamins, and
 * whether or not the food causes issues with the user's stomach.
 * 
 * @author Garrett Presley
 *
 */
public class Food extends AbstractMacroBased {

	// The name and serving size of the food -

	/** The name of the food. */
	private String name;
	/**
	 * The serving size of the food.
	 */
	private float servingSize;
	/** The number of servings of the food. */
	private float numberOfServings;

	// ---------------------------------------

	// Whether or not the food causes user stomach issues

	/** Determines if the food causes the user stomach issues. */
	private boolean causesStomachIssues;

	// ---------------------------------------

	/**
	 * A full constructor with the entirety of the fields being set.
	 * 
	 * @param name                the name to set
	 * @param servingSize         the serving size to set
	 * @param numberOfServings    the number of servings to set
	 * @param calories            the number of calories to set
	 * @param protein             the amount of protein to set
	 * @param fat                 the amount of fat to set
	 * @param carbs               the amount of carbs to set
	 * @param saturatedFat        the amount of saturated fat to set
	 * @param transFat            the amount of trans fat to set
	 * @param fiber               the amount of fiber to set
	 * @param sugar               the amount of sugar to set
	 * @param sodium              the amount of sodium to set
	 * @param cholesterol         the amount of cholesterol to set
	 * @param calcium             the amount of calcium to set
	 * @param potassium           the amount of potassium to set
	 * @param vitaminA            the amount of Vitamin A to set
	 * @param vitaminC            the amount of Vitamin C to set
	 * @param vitaminD            the amount of Vitamin D to set
	 * @param vitaminE            the amount of Vitamin E to set
	 * @param vitaminK            the amount of Vitamin K to set
	 * @param vitaminB1           the amount of Vitamin B1 to set
	 * @param vitaminB2           the amount of Vitamin B2 to set
	 * @param vitaminB3           the amount of Vitamin B3 to set
	 * @param vitaminB5           the amount of Vitamin B5 to set
	 * @param vitaminB6           the amount of Vitamin B6 to set
	 * @param vitaminB7           the amount of Vitamin B7 to set
	 * @param vitaminB9           the amount of Vitamin B9 to set
	 * @param vitaminB12          the amount of Vitamin B12 to set
	 * @param causesStomachIssues the boolean flag to set
	 */
	public Food(String name, float servingSize, float numberOfServings, int calories, int protein, int fat, int carbs,
			int saturatedFat, int transFat, int fiber, int sugar, int sodium, int cholesterol, int calcium,
			int potassium, int vitaminA, int vitaminC, int vitaminD, int vitaminE, int vitaminK, int vitaminB1,
			int vitaminB2, int vitaminB3, int vitaminB5, int vitaminB6, int vitaminB7, int vitaminB9, int vitaminB12,
			boolean causesStomachIssues) {
		super((int) (calories * numberOfServings), (int) (protein * numberOfServings), (int) (fat * numberOfServings),
				(int) (carbs * numberOfServings), (int) (saturatedFat * numberOfServings),
				(int) (transFat * numberOfServings), (int) (fiber * numberOfServings), (int) (sugar * numberOfServings),
				(int) (sodium * numberOfServings), (int) (cholesterol * numberOfServings),
				(int) (calcium * numberOfServings), (int) (potassium * numberOfServings),
				(int) (vitaminA * numberOfServings), (int) (vitaminC * numberOfServings),
				(int) (vitaminD * numberOfServings), (int) (vitaminE * numberOfServings),
				(int) (vitaminK * numberOfServings), (int) (vitaminB1 * numberOfServings),
				(int) (vitaminB2 * numberOfServings), (int) (vitaminB3 * numberOfServings),
				(int) (vitaminB5 * numberOfServings), (int) (vitaminB6 * numberOfServings),
				(int) (vitaminB7 * numberOfServings), (int) (vitaminB9 * numberOfServings),
				(int) (vitaminB12 * numberOfServings));
		setName(name);
		setServingSize(servingSize);
		setNumberOfServings(numberOfServings);
		setCausesStomachIssues(causesStomachIssues);
	}

	/**
	 * Constructs a food object with some given fields, fills the rest of the fields
	 * with zeros or false.
	 * 
	 * @param name             the name to set
	 * @param servingSize      the serving size to set
	 * @param servingSizeUnit  the serving size unit to set
	 * @param numberOfServings the number of servings to set
	 * @param calories         the number of calories to set
	 * @param protein          the amount of protein to set
	 * @param fat              the amount of fat to set
	 * @param carbs            the amount of carbs to set
	 */
	public Food(String name, float servingSize, float numberOfServings, int calories, int protein, int fat, int carbs) {
		this(name, servingSize, numberOfServings, calories, protein, fat, carbs, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, false);
	}

	/**
	 * Constructs a food object with the minimum amount of macro definition, sets
	 * all the other fields value to 0 or false. servingSizeUnit is set to grams.
	 * 
	 * @param name             the name to set
	 * @param numberOfServings the number of servings to set
	 * @param calories         the number of calories to set
	 * @param protein          the amount of protein to set
	 * @param fat              the amount of fat to set
	 * @param carbs            the amount of carbs to set
	 */
	public Food(String name, float numberOfServings, int calories, int protein, int fat, int carbs) {
		this(name, 0, numberOfServings, calories, protein, fat, carbs);
	}

	/**
	 * Constructs a food object with the minimum amount of macro definition, sets
	 * all the other fields value to 0 or false. servingSizeUnit is set to grams.
	 * 
	 * @param name     the name to set
	 * @param calories the number of calories to set
	 * @param protein  the amount of protein to set
	 * @param fat      the amount of fat to set
	 * @param carbs    the amount of carbs to set
	 */
	public Food(String name, int calories, int protein, int fat, int carbs) {
		this(name, 1, calories, protein, fat, carbs);
	}

	@Override
	public String toString() {
		return name + ", numberOfServings=" + numberOfServings + ", " + super.toString();
	}

	@Override
	public String toFullString() {
		return "Food [name=" + name + ", servingSize=" + servingSize + ", numberOfServings=" + numberOfServings + ", "
				+ super.toFullString() + ", causesStomachIssues=" + causesStomachIssues + "]";
	}

	/**
	 * Returns the name of the food.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the food.
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		checkString(name);
		this.name = name;
	}

	/**
	 * Returns the serving size of the food.
	 * 
	 * @return the serving size
	 */
	public float getServingSize() {
		return servingSize;
	}

	/**
	 * Sets the serving size of the food.
	 * 
	 * @param servingSize the serving size to set
	 */
	public void setServingSize(float servingSize) {
		checkFloat(servingSize);
		this.servingSize = servingSize;
	}

	/**
	 * Returns the number of serving the user ate of the food.
	 * 
	 * @return the number of servings consumed of the food
	 */
	public float getNumberOfServings() {
		return numberOfServings;
	}

	/**
	 * Sets the number of servings of the food that was ate.
	 * 
	 * @param numberOfServings the number of servings to set
	 */
	public void setNumberOfServings(float numberOfServings) {
		checkFloat(numberOfServings);
		this.numberOfServings = numberOfServings;
	}

	/**
	 * Returns if the food causes the user stomach issues.
	 * 
	 * @return if the food causes user stomach issues
	 */
	public boolean causesStomachIssues() {
		return causesStomachIssues;
	}

	/**
	 * Sets the causes stomach issues flag.
	 * 
	 * @param causesStomachIssues the boolean flag to set
	 */
	public void setCausesStomachIssues(boolean causesStomachIssues) {
		this.causesStomachIssues = causesStomachIssues;
	}

	/**
	 * Checks the format of string, cannot be null or empty (""). Their are also
	 * various other names that cannot be used since these words are used in File IO
	 * and String manipulations.
	 * 
	 * @param str the string to check
	 */
	private void checkString(String str) {
		if (str == null || str == "") {
			String type = str == null ? "null" : "empty";
			throw new IllegalArgumentException("The name of the food cannot be " + type + ".");
		}
		checkIfStringContains(str, "foodCollection");
	}

	/**
	 * Helper method for checking if a String, stringToCheck, contains the String,
	 * stringToCheckAgainst.
	 * 
	 * @param stringToCheck        the string to check
	 * @param stringToCheckAgainst the string to check in stringToCheck
	 */
	private void checkIfStringContains(String stringToCheck, String stringToCheckAgainst) {
		if (stringToCheck.contains(stringToCheckAgainst)) {
			throw new IllegalArgumentException("The name of the food cannot contain " + stringToCheckAgainst
					+ ". (Due to this String's usage in the back end implementation)");
		}
	}
}

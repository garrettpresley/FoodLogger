package model.implementation;

/**
 * Represents a Macro based idea which focuses on all the key nutrition details.
 * All measurements are in either g (for basic macros), mg (for in depth
 * macros), or mcg (for vitamins).
 * 
 * @author Garrett Presley
 *
 */
public abstract class AbstractMacroBased implements MacroBased {

	/** The number of macros being tracked */
	public static final int NUM_OF_MACROS = 25;

	// Basic macros --------------------------

	/** The amount of calories the food contains. */
	private int calories;
	/** The amount of protein the food contains, measured in grams. */
	private int protein;
	/** The amount of fat the food contains, measured in grams. */
	private int fat;
	/** The amount of carbohydrates the food contains, measured in grams. */
	private int carbs;

	// ---------------------------------------

	// In depth macros -----------------------

	/** The amount of saturated fat the food contains, measured in grams. */
	private int saturatedFat;
	/** The amount of trans fat the food contains, measured in grams. */
	private int transFat;
	/** The amount of fiber the food contains, measured in grams. */
	private int fiber;
	/** The amount of sugar the food contains, measured in grams. */
	private int sugar;
	/** The amount of sodium the food contains, measured in milligrams. */
	private int sodium;
	/** The amount of cholesterol the food contains, measured in milligrams. */
	private int cholesterol;
	/** The amount of calcium the food contains, measured in milligrams. */
	private int calcium;
	/** The amount of potassium the food contains, measured in milligrams. */
	private int potassium;

	// ---------------------------------------

	// Vitamins ------------------------------

	/** The amount of vitamin A the food contains, measured in micrograms. */
	private int vitaminA;
	/** The amount of vitamin C the food contains, measured in micrograms. */
	private int vitaminC;
	/** The amount of vitamin D the food contains, measured in micrograms. */
	private int vitaminD;
	/** The amount of vitamin E the food contains, measured in micrograms. */
	private int vitaminE;
	/** The amount of vitamin K the food contains, measured in micrograms. */
	private int vitaminK;
	/**
	 * The amount of vitamin B1 (thiamine) the food contains, measured in
	 * micrograms.
	 */
	private int vitaminB1;
	/**
	 * The amount of vitamin B2 (riboflavin) the food contains, measured in
	 * micrograms.
	 */
	private int vitaminB2;
	/**
	 * The amount of vitamin B3 (niacin) the food contains, measured in micrograms.
	 */
	private int vitaminB3;
	/**
	 * The amount of vitamin B5 (pantothenic) the food contains, measured in
	 * micrograms.
	 */
	private int vitaminB5;
	/**
	 * The amount of vitamin B6 (pyridxine) the food contains, measured in
	 * micrograms.
	 */
	private int vitaminB6;
	/**
	 * The amount of vitamin B7 (biotin) the food contains, measured in micrograms.
	 */
	private int vitaminB7;
	/**
	 * The amount of vitamin B9 (folic acid and folates) the food contains, measured
	 * in micrograms.
	 */
	private int vitaminB9;
	/**
	 * The amount of vitamin B12 (cobalamins) the food contains, measured in
	 * micrograms.
	 */
	private int vitaminB12;

	// ---------------------------------------

	/**
	 * A full constructor that sets all fields.
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
	public AbstractMacroBased(int calories, int protein, int fat, int carbs, int saturatedFat, int transFat, int fiber,
			int sugar, int sodium, int cholesterol, int calcium, int potassium, int vitaminA, int vitaminC,
			int vitaminD, int vitaminE, int vitaminK, int vitaminB1, int vitaminB2, int vitaminB3, int vitaminB5,
			int vitaminB6, int vitaminB7, int vitaminB9, int vitaminB12) {
		setCalories(calories);
		setProtein(protein);
		setFat(fat);
		setCarbs(carbs);
		setSaturatedFat(saturatedFat);
		setTransFat(transFat);
		setFiber(fiber);
		setSugar(sugar);
		setSodium(sodium);
		setCholesterol(cholesterol);
		setCalcium(calcium);
		setPotassium(potassium);
		setVitaminA(vitaminA);
		setVitaminC(vitaminC);
		setVitaminD(vitaminD);
		setVitaminE(vitaminE);
		setVitaminK(vitaminK);
		setVitaminB1(vitaminB1);
		setVitaminB2(vitaminB2);
		setVitaminB3(vitaminB3);
		setVitaminB5(vitaminB5);
		setVitaminB6(vitaminB6);
		setVitaminB7(vitaminB7);
		setVitaminB9(vitaminB9);
		setVitaminB12(vitaminB12);
	}

	/**
	 * Returns the macro based in index, starting with 0 for calories, and 1 for
	 * protein and so on. In the order of constructor fields.
	 * 
	 * @param index the macro to get
	 * @return the Macro wrapped item
	 */
	public MacroWrap getMacroInfo(int index) {
		switch (index) {
		case 0:
			return new MacroWrap("Calories", calories);
		case 1:
			return new MacroWrap("Protein", protein);
		case 2:
			return new MacroWrap("Fat", fat);
		case 3:
			return new MacroWrap("Carbs", carbs);
		case 4:
			return new MacroWrap("Saturated Fat", saturatedFat);
		case 5:
			return new MacroWrap("Trans Fat", transFat);
		case 6:
			return new MacroWrap("Fiber", fiber);
		case 7:
			return new MacroWrap("Sugar", sugar);
		case 8:
			return new MacroWrap("Sodium", sodium);
		case 9:
			return new MacroWrap("Cholesterol", cholesterol);
		case 10:
			return new MacroWrap("Calcium", calcium);
		case 11:
			return new MacroWrap("Potassium", potassium);
		case 12:
			return new MacroWrap("Vitamin A", vitaminA);
		case 13:
			return new MacroWrap("Vitamin C", vitaminC);
		case 14:
			return new MacroWrap("Vitamin D", vitaminD);
		case 15:
			return new MacroWrap("Vitamin E", vitaminE);
		case 16:
			return new MacroWrap("Vitamin K", vitaminK);
		case 17:
			return new MacroWrap("Vitamin B1", vitaminB1);
		case 18:
			return new MacroWrap("Vitamin B2", vitaminB2);
		case 19:
			return new MacroWrap("Vitamin B3", vitaminB3);
		case 20:
			return new MacroWrap("Vitamin B5", vitaminB5);
		case 21:
			return new MacroWrap("Vitamin B6", vitaminB6);
		case 22:
			return new MacroWrap("Vitamin B7", vitaminB7);
		case 23:
			return new MacroWrap("Vitamin B9", vitaminB9);
		case 24:
			return new MacroWrap("Vitamin B12", vitaminB12);
		default:
			throw new IllegalArgumentException("Index " + index + " is out of range for macros. Range = 0 - 24");
		}
	}

	@Override
	public String toString() {
		return "calories=" + calories + ", protein=" + protein + "g, fat=" + fat + "g, carbs=" + carbs + "g";
	}

	@Override
	public String toFullString() {
		return "calories=" + calories + ", protein=" + protein + ", fat=" + fat + ", carbs=" + carbs + ", saturatedFat="
				+ saturatedFat + ", transFat=" + transFat + ", fiber=" + fiber + ", sugar=" + sugar + ", sodium="
				+ sodium + ", cholesterol=" + cholesterol + ", calcium=" + calcium + ", potassium=" + potassium
				+ ", vitaminA=" + vitaminA + ", vitaminC=" + vitaminC + ", vitaminD=" + vitaminD + ", vitaminE="
				+ vitaminE + ", vitaminK=" + vitaminK + ", vitaminB1=" + vitaminB1 + ", vitaminB2=" + vitaminB2
				+ ", vitaminB3=" + vitaminB3 + ", vitaminB5=" + vitaminB5 + ", vitaminB6=" + vitaminB6 + ", vitaminB7="
				+ vitaminB7 + ", vitaminB9=" + vitaminB9 + ", vitaminB12=" + vitaminB12;
	}

	@Override
	public int getCalories() {
		return calories;
	}

	@Override
	public void setCalories(int calories) {
		checkInteger(calories);
		this.calories = calories;
	}

	@Override
	public int getProtein() {
		return protein;
	}

	@Override
	public void setProtein(int protein) {
		checkInteger(protein);
		this.protein = protein;
	}

	@Override
	public int getFat() {
		return fat;
	}

	@Override
	public void setFat(int fat) {
		checkInteger(fat);
		this.fat = fat;
	}

	@Override
	public int getCarbs() {
		return carbs;
	}

	@Override
	public void setCarbs(int carbs) {
		checkInteger(carbs);
		this.carbs = carbs;
	}

	@Override
	public int getSaturatedFat() {
		return saturatedFat;
	}

	@Override
	public void setSaturatedFat(int saturatedFat) {
		checkInteger(saturatedFat);
		this.saturatedFat = saturatedFat;
	}

	@Override
	public int getTransFat() {
		return transFat;
	}

	@Override
	public void setTransFat(int transFat) {
		checkInteger(transFat);
		this.transFat = transFat;
	}

	@Override
	public int getFiber() {
		return fiber;
	}

	@Override
	public void setFiber(int fiber) {
		checkInteger(fiber);
		this.fiber = fiber;
	}

	@Override
	public int getSugar() {
		return sugar;
	}

	@Override
	public void setSugar(int sugar) {
		checkInteger(sugar);
		this.sugar = sugar;
	}

	@Override
	public int getSodium() {
		return sodium;
	}

	@Override
	public void setSodium(int sodium) {
		checkInteger(sodium);
		this.sodium = sodium;
	}

	@Override
	public int getCholesterol() {
		return cholesterol;
	}

	@Override
	public void setCholesterol(int cholesterol) {
		checkInteger(cholesterol);
		this.cholesterol = cholesterol;
	}

	@Override
	public int getCalcium() {
		return calcium;
	}

	@Override
	public void setCalcium(int calcium) {
		checkInteger(calcium);
		this.calcium = calcium;
	}

	@Override
	public int getPotassium() {
		return potassium;
	}

	@Override
	public void setPotassium(int potassium) {
		checkInteger(potassium);
		this.potassium = potassium;
	}

	@Override
	public int getVitaminA() {
		return vitaminA;
	}

	@Override
	public void setVitaminA(int vitaminA) {
		checkInteger(vitaminA);
		this.vitaminA = vitaminA;
	}

	@Override
	public int getVitaminC() {
		return vitaminC;
	}

	@Override
	public void setVitaminC(int vitaminC) {
		checkInteger(vitaminC);
		this.vitaminC = vitaminC;
	}

	@Override
	public int getVitaminD() {
		return vitaminD;
	}

	@Override
	public void setVitaminD(int vitaminD) {
		checkInteger(vitaminD);
		this.vitaminD = vitaminD;
	}

	@Override
	public int getVitaminE() {
		return vitaminE;
	}

	@Override
	public void setVitaminE(int vitaminE) {
		checkInteger(vitaminE);
		this.vitaminE = vitaminE;
	}

	@Override
	public int getVitaminK() {
		return vitaminK;
	}

	@Override
	public void setVitaminK(int vitaminK) {
		checkInteger(vitaminK);
		this.vitaminK = vitaminK;
	}

	@Override
	public int getVitaminB1() {
		return vitaminB1;
	}

	@Override
	public void setVitaminB1(int vitaminB1) {
		checkInteger(vitaminB1);
		this.vitaminB1 = vitaminB1;
	}

	@Override
	public int getVitaminB2() {
		return vitaminB2;
	}

	@Override
	public void setVitaminB2(int vitaminB2) {
		checkInteger(vitaminB2);
		this.vitaminB2 = vitaminB2;
	}

	@Override
	public int getVitaminB3() {
		return vitaminB3;
	}

	@Override
	public void setVitaminB3(int vitaminB3) {
		checkInteger(vitaminB3);
		this.vitaminB3 = vitaminB3;
	}

	@Override
	public int getVitaminB5() {
		return vitaminB5;
	}

	@Override
	public void setVitaminB5(int vitaminB5) {
		checkInteger(vitaminB5);
		this.vitaminB5 = vitaminB5;
	}

	@Override
	public int getVitaminB6() {
		return vitaminB6;
	}

	@Override
	public void setVitaminB6(int vitaminB6) {
		checkInteger(vitaminB6);
		this.vitaminB6 = vitaminB6;
	}

	@Override
	public int getVitaminB7() {
		return vitaminB7;
	}

	@Override
	public void setVitaminB7(int vitaminB7) {
		checkInteger(vitaminB7);
		this.vitaminB7 = vitaminB7;
	}

	@Override
	public int getVitaminB9() {
		return vitaminB9;
	}

	@Override
	public void setVitaminB9(int vitaminB9) {
		checkInteger(vitaminB9);
		this.vitaminB9 = vitaminB9;
	}

	@Override
	public int getVitaminB12() {
		return vitaminB12;
	}

	@Override
	public void setVitaminB12(int vitaminB12) {
		checkInteger(vitaminB12);
		this.vitaminB12 = vitaminB12;
	}

	/**
	 * Check a integer cannot be negative.
	 * 
	 * @param num the integer to check
	 */
	public void checkInteger(int num) {
		if (num < 0)
			throw new IllegalArgumentException("Cannot assign a negative value for a macro for macro item.");
	}

	/**
	 * Check a float cannot be negative.
	 * 
	 * @param num the float to check
	 */
	public void checkFloat(float num) {
		if (num < 0)
			throw new IllegalArgumentException("Cannot assign a negative value for a macro for macro item.");
	}

	/**
	 * A class to wrap up a macro's name and their amount.
	 * 
	 * @author Garrett Presley
	 *
	 */
	public class MacroWrap {
		/** The type of macro */
		private String type;
		/** The amount of that macro as an integer */
		private int amount;

		/**
		 * Constructs a macro wrapped item given the type and its amount.
		 * 
		 * @param type   the type of macro to set
		 * @param amount the amount of that macro to set
		 */
		public MacroWrap(String type, int amount) {
			this.type = type;
			this.amount = amount;
		}

		/**
		 * Return the type of macro.
		 * 
		 * @return the type
		 */
		public String getType() {
			return type;
		}

		/**
		 * Return the amount of that macro.
		 * 
		 * @return the amount
		 */
		public int getAmount() {
			return amount;
		}

	}
}

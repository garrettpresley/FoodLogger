package model.implementation;

/**
 * Represents a Macro Base object blue print, which will focus on all the key
 * nutrition specifics.
 * 
 * @author Garrett Presley
 *
 */
public interface MacroBased {
	/**
	 * Returns the number of calories the food contains.
	 * 
	 * @return the amount of calories
	 */
	public int getCalories();

	/**
	 * Sets the number of calories the food contains.
	 * 
	 * @param calories the calories to set
	 */
	public void setCalories(int calories);

	/**
	 * Returns the amount of protein the food contains in grams.
	 * 
	 * @return the amount of protein
	 */
	public int getProtein();

	/**
	 * Sets the amount of protein the food contains.
	 * 
	 * @param protein the amount of protein to set
	 */
	public void setProtein(int protein);

	/**
	 * Returns the amount of fat the food contains in grams.
	 * 
	 * @return the amount of fat
	 */
	public int getFat();

	/**
	 * Sets the amount of fat the food contains.
	 * 
	 * @param fat the fat to set
	 */
	public void setFat(int fat);

	/**
	 * Returns the amount of carbs the food contains in grams.
	 * 
	 * @return the amount of carbs
	 */
	public int getCarbs();

	/**
	 * Sets the amount of carbs the food contains.
	 * 
	 * @param carbs the amount of carbs to set
	 */
	public void setCarbs(int carbs);

	/**
	 * Returns the amount of saturated fat a food contains in grams.
	 * 
	 * @return the amount of saturated fat
	 */
	public int getSaturatedFat();

	/**
	 * Sets the amount of saturated fat the food contains.
	 * 
	 * @param saturatedFat the amount of saturated fat to set
	 */
	public void setSaturatedFat(int saturatedFat);

	/**
	 * Returns the amount of trans fat a food contains in grams.
	 * 
	 * @return the amount of trans fat
	 */
	public int getTransFat();

	/**
	 * Sets the amount of trans fat a food contains.
	 * 
	 * @param transFat the amount of trans fat to set
	 */
	public void setTransFat(int transFat);

	/**
	 * Returns the amount of fiber a food contains in grams.
	 * 
	 * @return the amount of fiber
	 */
	public int getFiber();

	/**
	 * Sets the amound a fiber a food has.
	 * 
	 * @param fiber the fiber to set
	 */
	public void setFiber(int fiber);

	/**
	 * Returns the amount of sugar the food contains in grams.
	 * 
	 * @return the amount of sugar
	 */
	public int getSugar();

	/**
	 * Sets the amount of sugar a food contains.
	 * 
	 * @param sugar the sugar to set
	 */
	public void setSugar(int sugar);

	/**
	 * Returns the amount of sodium a food contains in milligrams.
	 * 
	 * @return the amount of sodium
	 */
	public int getSodium();

	/**
	 * Sets the amount of sodium a food contains.
	 * 
	 * @param sodium the amount of sodium to set
	 */
	public void setSodium(int sodium);

	/**
	 * Returns the amount of cholesterol a food contains in milligrams.
	 * 
	 * @return the amount of cholesterol
	 */
	public int getCholesterol();

	/**
	 * Sets the amount of cholesterol a food contains.
	 * 
	 * @param cholesterol the cholesterol to set
	 */
	public void setCholesterol(int cholesterol);

	/**
	 * Returns the amount of calcium a food contains in milligrams.
	 * 
	 * @return the amount of calcium
	 */
	public int getCalcium();

	/**
	 * Sets the amount of calcium a food contains.
	 * 
	 * @param calcium the amount of calcium to set
	 */
	public void setCalcium(int calcium);

	/**
	 * Returns the amount of potassium a food contains in milligrams.
	 * 
	 * @return the potassium
	 */
	public int getPotassium();

	/**
	 * Sets the amount potassium a food contains.
	 * 
	 * @param potassium the amount of potassium
	 */
	public void setPotassium(int potassium);

	/**
	 * Returns the amount of Vitamin A a food contains in micrograms.
	 * 
	 * @return the amount of Vitamin A
	 */
	public int getVitaminA();

	/**
	 * Sets the amount of Vitamin A a food contains.
	 * 
	 * @param vitaminA the amount of Vitamin A to set
	 */
	public void setVitaminA(int vitaminA);

	/**
	 * Returns the amount of Vitamin C a food contains in micrograms.
	 * 
	 * @return the amount of Vitamin C
	 */
	public int getVitaminC();

	/**
	 * Sets the amount of Vitamin C a food contains.
	 * 
	 * @param vitaminC the amount of Vitamin C to set
	 */
	public void setVitaminC(int vitaminC);

	/**
	 * Returns the amount of Vitamin D a food contains in micrograms.
	 * 
	 * @return the amound of Vitamin D
	 */
	public int getVitaminD();

	/**
	 * Sets the amount of Vitamin D a food contains.
	 * 
	 * @param vitaminD the amount of Vitamin D to set
	 */
	public void setVitaminD(int vitaminD);

	/**
	 * Returns the amount of Vitamin E a food contains in micrograms.
	 * 
	 * @return the amount of Vitamin E
	 */
	public int getVitaminE();

	/**
	 * Sets the amount of Vitamin E a food time contains.
	 * 
	 * @param vitaminE the amount of Vitamin E a food item contains
	 */
	public void setVitaminE(int vitaminE);

	/**
	 * Returns the amount of Vitamin K a food item contains in micrograms.
	 * 
	 * @return the amount of Vitamin K
	 */
	public int getVitaminK();

	/**
	 * Sets the amount of Vitamin K a food time contains.
	 * 
	 * @param vitaminK the amount of Vitamin K a food item contains
	 */
	public void setVitaminK(int vitaminK);

	/**
	 * Returns the amount of Vitamin B1 a food item contains in micrograms.
	 * 
	 * @return the amount of Vitamin B1
	 */
	public int getVitaminB1();

	/**
	 * Sets the amount of Vitamin B1 a food time contains.
	 * 
	 * @param vitaminB1 the amount of Vitamin B1 a food item contains
	 */
	public void setVitaminB1(int vitaminB1);

	/**
	 * Returns the amount of Vitamin B2 a food item contains in micrograms.
	 * 
	 * @return the amount of Vitamin B2
	 */
	public int getVitaminB2();

	/**
	 * Sets the amount of Vitamin B2 a food time contains.
	 * 
	 * @param vitaminB2 the amount of Vitamin B2 a food item contains
	 */
	public void setVitaminB2(int vitaminB2);

	/**
	 * Returns the amount of Vitamin B3 a food item contains in micrograms.
	 * 
	 * @return the amount of Vitamin B3
	 */
	public int getVitaminB3();

	/**
	 * Sets the amount of Vitamin B3 a food time contains.
	 * 
	 * @param vitaminB3 the amount of Vitamin B3 a food item contains
	 */
	public void setVitaminB3(int vitaminB3);

	/**
	 * Returns the amount of Vitamin B5 a food item contains in micrograms.
	 * 
	 * @return the amount of Vitamin B5
	 */
	public int getVitaminB5();

	/**
	 * Sets the amount of Vitamin B5 a food time contains.
	 * 
	 * @param vitaminB5 the amount of Vitamin B5 a food item contains
	 */
	public void setVitaminB5(int vitaminB5);

	/**
	 * Returns the amount of Vitamin B6 a food item contains in micrograms.
	 * 
	 * @return the amount of Vitamin B6
	 */
	public int getVitaminB6();

	/**
	 * Sets the amount of Vitamin B6 a food time contains.
	 * 
	 * @param vitaminB6 the amount of Vitamin B6 a food item contains
	 */
	public void setVitaminB6(int vitaminB6);

	/**
	 * Returns the amount of Vitamin B7 a food item contains in micrograms.
	 * 
	 * @return the amount of Vitamin B7
	 */
	public int getVitaminB7();

	/**
	 * Sets the amount of Vitamin B7 a food time contains.
	 * 
	 * @param vitaminB7 the amount of Vitamin B7 a food item contains
	 */
	public void setVitaminB7(int vitaminB7);

	/**
	 * Returns the amount of Vitamin B9 a food item contains in microgorams.
	 * 
	 * @return the amount of Vitamin B9
	 */
	public int getVitaminB9();

	/**
	 * Sets the amount of Vitamin B9 a food time contains.
	 * 
	 * @param vitaminB9 the amount of Vitamin B9 a food item contains
	 */
	public void setVitaminB9(int vitaminB9);

	/**
	 * Returns the amount of Vitamin B12 a food item contains in micrograms.
	 * 
	 * @return the amount of Vitamin B12
	 */
	public int getVitaminB12();

	/**
	 * Sets the amount of Vitamin B12 a d food item contains.
	 * 
	 * @param vitaminB12 the amount of Vitamin B12 to set
	 */
	public void setVitaminB12(int vitaminB12);

	/**
	 * Returns a string representation of a macro based object with minimal amount
	 * information.
	 * 
	 * @return a small string object
	 */
	@Override
	public String toString();

	/**
	 * Returns a string representation of a macro based object with large amount
	 * information.
	 * 
	 * @return a large string object
	 */
	public String toFullString();
}

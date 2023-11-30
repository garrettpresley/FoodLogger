package model.implementation;

import model.implementation.day.Food;

/**
 * Represents the idea of an object which keeps track of the total amount of
 * macros every time it adds food to its collection or removed.
 * 
 * @author Garrett Presley
 *
 */
public interface FoodCollection {

	/**
	 * Adds the food's macros to the total macros.
	 * 
	 * @param f the food to add to macros
	 */
	public void addFoodMacrosToTotalMacros(Food f);

	/**
	 * Removes the food's macros from the total macros.
	 * 
	 * @param f the food to remove from macros
	 */
	public void removeFoodMacrosFromTotalMacros(Food f);
}

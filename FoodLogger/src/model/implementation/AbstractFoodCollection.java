package model.implementation;

import model.implementation.day.Food;
import model.util.list.ArrayList;
import model.util.list.List;

/**
 * Represents the abstract idea of an object which keeps track of the total
 * amount of macros every time it adds food to its collection or removed.
 * 
 * @author Garrett Presley
 * @param <E>
 *
 */
public class AbstractFoodCollection<E extends MacroBased> extends AbstractMacroBased implements FoodCollection {

	/** The collection of food to maintain */
	private List<E> foodCollection;

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
	public AbstractFoodCollection(int calories, int protein, int fat, int carbs, int saturatedFat, int transFat,
			int fiber, int sugar, int sodium, int cholesterol, int calcium, int potassium, int vitaminA, int vitaminC,
			int vitaminD, int vitaminE, int vitaminK, int vitaminB1, int vitaminB2, int vitaminB3, int vitaminB5,
			int vitaminB6, int vitaminB7, int vitaminB9, int vitaminB12) {
		super(calories, protein, fat, carbs, saturatedFat, transFat, fiber, sugar, sodium, cholesterol, calcium,
				potassium, vitaminA, vitaminC, vitaminD, vitaminE, vitaminK, vitaminB1, vitaminB2, vitaminB3, vitaminB5,
				vitaminB6, vitaminB7, vitaminB9, vitaminB12);
		foodCollection = new ArrayList<E>();
	}

	@Override
	public String toString() {
		return super.toString() + ", foodCollection=\n" + foodCollectionToString(false);
	}

	@Override
	public String toFullString() {
		return super.toFullString() + ", foodCollection=\n" + foodCollectionToString(true);
	}

	/**
	 * Helper method for getting a string representation of the food list. Has to
	 * option to return minimal info, or full info.
	 * 
	 * @param toFullString whether or not display full info
	 * @return String representation of the food list
	 */
	private String foodCollectionToString(boolean toFullStirng) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < foodCollection.size(); i++)
			str.append((toFullStirng ? foodCollection.get(i).toFullString() : foodCollection.get(i).toString()) + "\n");
		return str.toString();
	}

	@Override
	public void addFoodMacrosToTotalMacros(Food f) {
		setCalories(getCalories() + f.getCalories());
		setProtein(getProtein() + f.getProtein());
		setFat(getFat() + f.getFat());
		setCarbs(getCarbs() + f.getCarbs());
		setSaturatedFat(getSaturatedFat() + f.getSaturatedFat());
		setTransFat(getTransFat() + f.getTransFat());
		setFiber(getFiber() + f.getTransFat());
		setSugar(getSugar() + f.getSugar());
		setSodium(getSodium() + f.getSodium());
		setCholesterol(getCholesterol() + f.getCholesterol());
		setCalcium(getCalcium() + f.getCalcium());
		setPotassium(getPotassium() + f.getPotassium());
		setVitaminA(getVitaminA() + f.getVitaminA());
		setVitaminC(getVitaminC() + f.getVitaminC());
		setVitaminD(getVitaminD() + f.getVitaminD());
		setVitaminE(getVitaminE() + f.getVitaminE());
		setVitaminK(getVitaminK() + f.getVitaminK());
		setVitaminB1(getVitaminB1() + f.getVitaminB1());
		setVitaminB2(getVitaminB2() + f.getVitaminB2());
		setVitaminB3(getVitaminB3() + f.getVitaminB3());
		setVitaminB5(getVitaminB5() + f.getVitaminB5());
		setVitaminB6(getVitaminB6() + f.getVitaminB6());
		setVitaminB7(getVitaminB7() + f.getVitaminB7());
		setVitaminB9(getVitaminB9() + f.getVitaminB9());
		setVitaminB12(getVitaminB12() + f.getVitaminB12());
	}

	@Override
	public void removeFoodMacrosFromTotalMacros(Food f) {
		setCalories(getCalories() - f.getCalories());
		setProtein(getProtein() - f.getProtein());
		setFat(getFat() - f.getFat());
		setCarbs(getCarbs() - f.getCarbs());
		setSaturatedFat(getSaturatedFat() - f.getSaturatedFat());
		setTransFat(getTransFat() - f.getTransFat());
		setFiber(getFiber() - f.getTransFat());
		setSugar(getSugar() - f.getSugar());
		setSodium(getSodium() - f.getSodium());
		setCholesterol(getCholesterol() - f.getCholesterol());
		setCalcium(getCalcium() - f.getCalcium());
		setPotassium(getPotassium() - f.getPotassium());
		setVitaminA(getVitaminA() - f.getVitaminA());
		setVitaminC(getVitaminC() - f.getVitaminC());
		setVitaminD(getVitaminD() - f.getVitaminD());
		setVitaminE(getVitaminE() - f.getVitaminE());
		setVitaminK(getVitaminK() - f.getVitaminK());
		setVitaminB1(getVitaminB1() - f.getVitaminB1());
		setVitaminB2(getVitaminB2() - f.getVitaminB2());
		setVitaminB3(getVitaminB3() - f.getVitaminB3());
		setVitaminB5(getVitaminB5() - f.getVitaminB5());
		setVitaminB6(getVitaminB6() - f.getVitaminB6());
		setVitaminB7(getVitaminB7() - f.getVitaminB7());
		setVitaminB9(getVitaminB9() - f.getVitaminB9());
		setVitaminB12(getVitaminB12() - f.getVitaminB12());
	}

	public int size() {
		return foodCollection.size();
	}

	public List<E> getFoodCollection() {
		return foodCollection;
	}

}

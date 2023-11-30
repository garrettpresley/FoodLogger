package model.implementation.food_log;

import model.implementation.day.Food;
import model.util.map.Map;
import model.util.map.SkipListMap;
import model.util.list.List;
import model.util.list.ArrayList;

/**
 * Keeps track of all the food that was logged by Food Logger Users, so when
 * user's look up food to add recommendations come up. Interacts with FoodLogger
 * Controller.
 * 
 * @author Garrett Presley
 *
 */
public class LoggedFoodDatabase {

	/**
	 * The map type to return for logged food database, is a skip list map so when
	 * displaying contents of map it will show the foods in alphabetical order.
	 */
	private static final Map<String, Food> LOGGED_FOOD_DATABASE_MAP_TYPE = new SkipListMap<String, Food>();

	/**
	 * The database that keeps track of all the food that food log users have logged
	 */
	private Map<String, Food> database;

	/**
	 * Constructs a LoggedFoodDatabase object by instantiating database map.
	 */
	public LoggedFoodDatabase() {
		database = LOGGED_FOOD_DATABASE_MAP_TYPE;
	}

	/**
	 * Puts the Food item f into the database map.
	 * 
	 * @param f the food to put into the map
	 */
	public void saveFoodToDatabase(Food f) {
		database.put(f.getName(), f);
	}

	/**
	 * Returns a minimal information String of the LoggedFoodDatabase.
	 * 
	 * @return minimal information String
	 */
	@Override
	public String toString() {
		return "LoggedFoodDatabase\n" + databaseToString(false);
	}

	/**
	 * Returns a full information String of LoggedFoodDatabase.
	 * 
	 * @return full information String
	 */
	public String toFullString() {
		return "LoggedFoodDatabase [\n" + databaseToString(true) + "\n]";

	}

	public Food checkIfFoodIsInDatabase(String foodName) {
		return database.get(foodName);
	}

	public Food[] foodsThatContainSubstring(String subString) {
		// Transfer all names that contain subString
		List<Food> foodsaAL = new ArrayList<Food>();
		for (Map.Entry<String, Food> entry : database.entrySet()) {
			String foodName = entry.getKey();
			Food f = entry.getValue();
			if (foodName.toLowerCase().contains(subString.toLowerCase()))
				foodsaAL.addLast(f);
		}

		// Convert ArrayList to array
		Food[] foods = new Food[foodsaAL.size()];
		for (int i = 0; i < foods.length; i++) {
			foods[i] = foodsaAL.get(i);
		}

		// Return the foods array
		return foods;
	}

	public String[] foodNamesThatContainSubstring(String subString) {
		// Transfer all names that contain subString
		List<Food> foodsaAL = new ArrayList<Food>();
		for (Map.Entry<String, Food> entry : database.entrySet()) {
			String foodName = entry.getKey();
			Food f = entry.getValue();
			if (foodName.toLowerCase().contains(subString.toLowerCase()))
				foodsaAL.addLast(f);
		}

		// Convert ArrayList to array
		String[] foodNames = new String[foodsaAL.size()];
		for (int i = 0; i < foodNames.length; i++) {
			foodNames[i] = foodsaAL.get(i).getName();
		}

		// Return the foodNames array
		return foodNames;
	}

	private String databaseToString(boolean toFullString) {
		StringBuilder s = new StringBuilder();
		for (Map.Entry<String, Food> entry : database.entrySet()) {
			Food val = entry.getValue();
			s.append(toFullString ? val.toFullString() : val.toString());
			s.append("\n");
		}
		return s.toString();
	}

}

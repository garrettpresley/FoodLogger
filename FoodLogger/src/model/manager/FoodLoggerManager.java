package model.manager;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.implementation.day.Day;
import model.implementation.day.Food;
import model.implementation.day.Meal;
import model.implementation.food_log.FoodLog;
import model.implementation.food_log.LoggedFoodDatabase;
import model.implementation.goal.Goal;
import model.implementation.user.FoodLoggerUser;
import model.io.FoodLoggerIO;
import model.util.map.Map;
import model.util.string.StringZoo;

public class FoodLoggerManager {

	private static FoodLoggerManager instance;

	private Map<String, Integer> userNameMap;
	private LoggedFoodDatabase loggedFoodDatabase;
	private Integer userCount;
	private FoodLoggerUser currentUser;

	// Controller methods and constructor ---------------------------------------

	private FoodLoggerManager() throws FileNotFoundException {
		// Load in all the user names stored in the local system
		userNameMap = FoodLoggerIO.loadFoodLoggerUserNames();
		// Sets the user count based on the size of the map
		userCount = userNameMap.size();
		// Load in the logged food database
		loggedFoodDatabase = FoodLoggerIO.loadLoggedFoodDatabase();
		// Set the current user to null
		currentUser = null;
	}

	public static FoodLoggerManager loginToManager() throws FileNotFoundException {
		// Check if their is a current instance
		if (instance != null)
			return instance;
		// If no instance construct one and return it
		instance = new FoodLoggerManager();
		return instance;
	}

	public boolean logoutOfManager() throws IOException {
		// Check if their is an instance to logout of
		if (instance == null)
			return false;
		// Logout the current user if that has not been done
		if (currentUser != null)
			logoutUser();
		// Save the map of user names to the system
		FoodLoggerIO.saveFoodLoggerUserNames(userNameMap);
		// Save the logged food database
		FoodLoggerIO.saveLoggedFoodDataBase(loggedFoodDatabase);
		// Set instance to null and return that logout was a success
		instance = null;
		return true;
	}

	public LoggedFoodDatabase getLoggedFoodDatabase() {
		return loggedFoodDatabase;
	}

	// End of controller based methods ----------------------------------------

	// Current user based methods ---------------------------------------------

	public void signUpUser(String userName) throws IOException {
		// Checks to see if a the user name being added already exists
		if (userNameMap.get(userName) != null)
			throw new IllegalArgumentException(
					"A user with the name '" + userName + "' already exists in local database");
		// Construct a new Food Log User and save their data into system
		FoodLoggerUser newUser = new FoodLoggerUser(userName);
		userNameMap.put(userName, userCount++);
		FoodLoggerIO.saveFoodLoggerUserData(newUser);
	}

	private void loadFoodLoggerUserData(String userName) throws FileNotFoundException {
		this.currentUser = FoodLoggerIO.loadFoodLoggerUserData(userName);
	}

	private void saveCurrentUserData() throws IOException {
		FoodLoggerIO.saveFoodLoggerUserData(currentUser);
	}

	public boolean loginUser(String userName) throws FileNotFoundException {
		// Check if user name exists in system
		if (userNameMap.get(userName) == null)
			return false;
		// Check if a user is already signed in
		if (currentUser != null)
			throw new IllegalArgumentException("A user (" + userName + ") is already logged in.");
		// Login user if checks pass
		loadFoodLoggerUserData(userName);
		currentUser.startNewDayIfNeeded();
		return true;
	}

	/**
	 * Logs out current user and saves their data. If their is no current user than
	 * nothing is done.
	 * 
	 * @throws IOException if file could not be found
	 */
	public void logoutUser() throws IOException {
		if (currentUser == null)
			return;
		// Save the user data before logging out
		saveCurrentUserData();
		// Logout the user from the manager by setting user to null
		currentUser = null;
	}

	public FoodLoggerUser getUser() {
		return currentUser;
	}

	public void userStartNewDayIfNeeded() {
		currentUser.startNewDayIfNeeded();
	}

	public void addFoodToUsersCurrentDayIndexMeal(Food foodToAdd, int mealIndex) {
		currentUser.getCurrentDay().addIndexFood(foodToAdd, mealIndex);
		loggedFoodDatabase.saveFoodToDatabase(foodToAdd);
	}

	public void removeFoodFromUserCurrentDayIndexMeal(String foodName, int mealIndex) {
		currentUser.getCurrentDay().removeFoodFromIndexMeal(foodName, mealIndex);
	}

	/**
	 * Checks if a user stayed logged in last time FoodLogger was used, if they were
	 * then go ahead an log them and return true. If they where not logged in than
	 * do nothing and return false.
	 * 
	 * @return true if user stayed logged in, false if otherwise
	 * @throws IOException if data files could not be located
	 */
	public boolean checkIfUserStayedLoggedIn() throws IOException {
		String userName = FoodLoggerIO.checkIfUserIsStayingLoggedIn();
		if (userName == null)
			return false;
		loginUser(userName);
		return true;
	}

	public void keepUserNameLoggedIn(FoodLoggerUser user) throws IOException {
		FoodLoggerIO.saveUserNameOfUserStayingLoggedIn(user.getUserName());
	}

	public void removeUserNameLoggedIn() throws IOException {
		FoodLoggerIO.clearUerNameOfUserStayingLoggedIn();
	}

	// End current user based methods ---------------------------------------

	// Display Strings for GUI ----------------------------------------------

	public String getCurrentDayMacrosVsGoalString() {
		FoodLoggerUser u = getUser();
		Goal g = u.getUsersGoal();
		Day d = u.getCurrentDay();
		String caloriePercentage = "calories-" + g.getCalories() + "/" + d.getCalories();
		String proteinPercentage = ", protien-" + g.getProtein() + "/" + d.getProtein();
		String fatPercentage = ", fat-" + g.getFat() + "/" + d.getFat();
		String carbsPercentage = ", carbs-" + g.getCarbs() + "/" + d.getCarbs();

		StringBuilder sB = new StringBuilder("Goal: ");
		sB.append(caloriePercentage + proteinPercentage + fatPercentage + carbsPercentage + "\n");
		sB.append("Goal met today? " + (getUser().getUsersGoal().goalsMeet(d) ? "Yes" : "No"));
		return sB.toString();
	}

	public String getCurrentDayInfoString() {
		String currentDayString = getUser().getCurrentDay().toString();
		StringBuilder sB = new StringBuilder("\nCurrent Day:\n");
		sB.append(currentDayString.substring(currentDayString.indexOf("Breakfast,")) + "\n");
		return sB.toString();
	}

	public String getMealInfoString(int index) {
		FoodLoggerUser u = getUser();
		Day d = u.getCurrentDay();
		Meal m = d.getFoodCollection().get(index);
		return m.toString();
	}

	public String getFoodLogInfoString() {
		FoodLog log = getUser().getFoodLog();
		String foodLogStringFull = log.toString();
		int indexToStart = log.isContainsALog() ? 2 : 1;
		String foodLogString = foodLogStringFull
				.substring(foodLogStringFull.indexOf("log=") + "log=".length() + indexToStart);
		StringBuilder sB = new StringBuilder(foodLogString);
		sB.append("First Logged Day: " + log.getFirstLoggedDay());
		sB.append("Days where goals where met: " + log.getDaysWereGoalsWereMet());
		sB.append("Number of days logged: " + log.getDaysCounter());
		sB.append("Food Log" + StringZoo.repeatString("-", 100));
		sB.append(foodLogString);
		sB.append(StringZoo.repeatString("-", "Food Log".length() + 100));
		return sB.toString();
	}

	// End display strings for GUI ---------------------------------------

	// String arrays for GUI ---------------------------------------------

	// -------------------------------------------------------------------

}
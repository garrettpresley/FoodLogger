package model.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

import model.implementation.day.Day;
import model.implementation.day.Food;
import model.implementation.food_log.FoodLog;
import model.implementation.food_log.LoggedFoodDatabase;
import model.implementation.goal.Goal;
import model.implementation.user.FoodLoggerUser;
import model.util.map.HashMap;
import model.util.map.Map;
import model.util.string.StringZoo;
import model.util.wrapper.PrimitiveWrapper;

/**
 * Handles all the FoodLog appliaction's File input and output for users.
 * 
 * @author Garrett Presley
 *
 */
public class FoodLoggerIO {

	/** The sub path to files */
//	private static final String DIR = Paths.get("FoodLog").toAbsolutePath().toString() + "/";
//	private static final String TRIM_DIR = DIR.substring(0, DIR.length() - "FoodLog".length() - 1);

	/** The folder for Food Log Users' data */
	private static final String USERS_DATA_FOLDER = "FOODLOGGER_USER_DATA/";
	/** The folder for Food Log User names */
	private static final String USER_NAMES_FOLDER = "FOODLOGGER_USERNAME_MAP/";
	/** The folder with the user name if a user decides to stay logged in */
	private static final String USER_NAME_LOGGED_IN_FOLDER = "FOODLOGGER_STAY_LOGGED_IN/";
	/** The folder with the logged food database */
	private static final String LOGGED_FOOD_DATABASE_FOLDER = "LOGGED_FOODS_DATABASE/";
	/** The file name for Food Log User names */
	private static final String USER_NAMES_FILE = "FoodLogger_Usernames.txt";
	/** The file name with the user name who is staying logged in */
	private static final String USER_NAME_LOGGED_IN_FILE = "Name_of_User_to_Stay_Logged_In.txt";
	/** The file name with the logged foods database */
	private static final String LOGGED_FOOD_DATABASE_FILE = "Logged_Foods_Map.txt";

	/**
	 * The map type to return for food logger username database, is hash map for
	 * efficient look up.
	 */
	private static final Map<String, Integer> FOOD_LOGGER_USERNAMES_MAP_TYPE = new HashMap<String, Integer>();

	public static FoodLoggerUser loadFoodLoggerUserData(String userName) throws FileNotFoundException {
		// Convert contents of file to String
		String fileString = fileToString(USERS_DATA_FOLDER + userName + "_FoodLog_Data.txt");

		/*
		 * Break apart file string into goal, currentDay, and foodLog strings, first
		 * find all correct indices.
		 */
		int begIndexOfGoal = fileString.indexOf("usersGoal=") + "usersGoal=".length() + 1;
		int endIndexOfGoal = fileString.indexOf("currentDay=") - 1;
		int begIndexOfCurrentDay = endIndexOfGoal + "currentDay=".length() + 2;
		int endIndexOfCurrentDay = fileString.indexOf("foodLog=") - 1;
		int begIndexOfFoodLog = endIndexOfCurrentDay + "foodLog=".length() + 2;
		int endIndexOfFoodLog = fileString.length() - 2;

		// Get all value strings
		String goalString = fileString.substring(begIndexOfGoal, endIndexOfGoal);
		String currentDayString = fileString.substring(begIndexOfCurrentDay, endIndexOfCurrentDay);
		String foodLogString = fileString.substring(begIndexOfFoodLog, endIndexOfFoodLog);

		// Read in the users goal if any
		Goal usersGoal = readGoal(goalString);

		// Read in the users current day if any
		Day usersCurrentDay = readCurrentDay(currentDayString);

		// Read in the users food log
		FoodLog usersFoodLog = readFoodLog(foodLogString);

		// Construct a FoodLog user to store info in and return
		FoodLoggerUser user = new FoodLoggerUser(userName, usersGoal, usersFoodLog, usersCurrentDay);

		return user;
	}

	public static Map<String, Integer> loadFoodLoggerUserNames() throws FileNotFoundException {
		// Construct a new reader, reading from
		// FOODLOG_USERNAME_MAP/FoodLog_Usernames.txt
		Scanner reader = new Scanner(new FileInputStream(USER_NAMES_FOLDER + USER_NAMES_FILE));

		// Construct the hash map for storing the user names
		Map<String, Integer> userNameMap = FOOD_LOGGER_USERNAMES_MAP_TYPE;

		/*
		 * Read from file storing the names and when they were signed up, format is
		 * "userName,(current user count when they signed up)"
		 */
		while (reader.hasNextLine()) {
			// Get the current line to dissect values from
			String line = reader.nextLine();

			// Get the index of ',' in line
			int commaIndex = line.indexOf(',');

			// Get the user name from the the value left of the ','
			String userName = line.substring(0, commaIndex);

			// Get the current user count when they signed up
			int userCount = Integer.parseInt(line.substring(commaIndex + 1, line.length()));

			// Store values in map
			userNameMap.put(userName, userCount);
		}

		// Close the reader
		reader.close();

		// Return the map
		return userNameMap;
	}

	public static String checkIfUserIsStayingLoggedIn() throws IOException {
		// Put whole file into string
		String fileString = fileToString(USER_NAME_LOGGED_IN_FOLDER + USER_NAME_LOGGED_IN_FILE);

		// Check to see if any name was provided
		if (fileString.isEmpty())
			return null;

		// Return the name if was provided
		return fileString;
	}

	public static LoggedFoodDatabase loadLoggedFoodDatabase() throws FileNotFoundException {
		// Construct the map to be returned
		LoggedFoodDatabase database = new LoggedFoodDatabase();

		// Put the file containing the Food database
		String fileStirng = fileToString(LOGGED_FOOD_DATABASE_FOLDER + LOGGED_FOOD_DATABASE_FILE);

		try (// Constructs a Scanner to read in the file that contains the logged food that
				// uses a delimiter to make reading in easier
				Scanner reader = (new Scanner(fileStirng)).useDelimiter("Food ")) {

			reader.next();
			// Assign values to database
			while (reader.hasNext())
				database.saveFoodToDatabase(readFood(reader.next()));

			// Close the reader
			reader.close();
		}

		// Return the read in database
		return database;
	}

	/**
	 * Writes the given FoodLog users data to a standardized file name which is
	 * user.getUserName() + "_FoodLog_Data.txt", all data will be stored in the
	 * FOOD_LOG_USER_FILES folder within the java project labeled FoodLog.
	 * 
	 * @param user the user of which to save data
	 * @throws IOException if their is an error writing to file
	 */
	public static void saveFoodLoggerUserData(FoodLoggerUser user) throws IOException {
		// Construct the standardized file name.
		String fileName = USERS_DATA_FOLDER + user.getUserName() + "_FoodLog_Data.txt";

		// Overwrite user's file
		overWriteFile(fileName, user.toFullString());
	}

	/**
	 * Saves a map of all the user names that have signed up with FoodLog. Loads the
	 * user names into a hash map from the file location of
	 * "FOODLOG_USERNAME_MAP/FoodLog_Usernames.txt".
	 * 
	 * @param userNameMap the map of user names to save
	 * @throws IOException if file cannot be opened
	 */
	public static void saveFoodLoggerUserNames(Map<String, Integer> userNameMap) throws IOException {
		// Construct the standardized file name.
		String fileName = USER_NAMES_FOLDER + USER_NAMES_FILE;

		// Save the contents to write in a string builder object.
		StringBuilder sb = new StringBuilder("");

		// Append to the builder.
		for (Map.Entry<String, Integer> entry : userNameMap.entrySet())
			sb.append(entry.getKey() + "," + entry.getValue() + "\n");

		// Overwrite user name folder
		overWriteFile(fileName, sb.toString());
	}

	/**
	 * Writes the user name of user to the stay logged in file in the stay logged in
	 * folder.
	 * 
	 * @param userName the String of user's name to save
	 * @throws IOException if file cannot be opened
	 */
	public static void saveUserNameOfUserStayingLoggedIn(String userName) throws IOException {
		// Construct the standardized file name
		String fileName = USER_NAME_LOGGED_IN_FOLDER + USER_NAME_LOGGED_IN_FILE;

		// Overwrite stay logged in folder
		overWriteFile(fileName, userName);
	}

	public static void clearUerNameOfUserStayingLoggedIn() throws IOException {
		// Overwrite stay logged in folder
		overWriteFile(USER_NAME_LOGGED_IN_FOLDER + USER_NAME_LOGGED_IN_FILE, "");
	}

	public static void saveLoggedFoodDataBase(LoggedFoodDatabase database) throws IOException {
		// Overwrite the logged food database
		overWriteFile(LOGGED_FOOD_DATABASE_FOLDER + LOGGED_FOOD_DATABASE_FILE, database.toFullString());
	}

	private static void overWriteFile(String fileName, String contentsToWrite) throws IOException {
		// Delete to old file so as to overwrite data stored in the txt file previously.
		File fold = new File(fileName);
		fold.delete();

		// Create the new file object
		File fnew = new File(fileName);

		// Construct fileWriter object setting the append setting to off since whole
		// file is being overridden.
		FileWriter fileWriter = new FileWriter(fnew, false);

		// Write to the file.
		fileWriter.write(contentsToWrite);

		// Close the file.
		fileWriter.close();
	}

	private static String fileToString(String fileName) throws FileNotFoundException {
		// Construct a new reader, reading from
		// fileName.txt
		Scanner fileReader = new Scanner(new FileInputStream(fileName));

		// Put whole file into string
		StringBuilder fileStringBuilder = new StringBuilder("");
		while (fileReader.hasNextLine())
			fileStringBuilder.append(fileReader.nextLine() + "\n");
		String fileString = fileStringBuilder.toString().trim();

		// Close the file reader
		fileReader.close();

		// Return the file contents in a string
		return fileString;
	}

	private static Goal readGoal(String goalString) {
		// Check if a goal was provided
		if (goalString.equals("No goal provdied"))
			return null;

		PrimitiveWrapper<Integer> indexToReadFrom = new PrimitiveWrapper<Integer>(0);

		// The type of goal ----------------------
		indexToReadFrom.setValue(goalString.indexOf(", calories"));
		String type = goalString.substring(goalString.indexOf("type=") + "type=".length(), indexToReadFrom.getValue());

		// Basic macros --------------------------
		int calories = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');
		int protein = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');
		int fat = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');
		int carbs = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');

		// In depth macros -----------------------
		int saturatedFat = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');
		int transFat = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');
		int fiber = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');
		int sugar = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');
		int sodium = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');
		int cholesterol = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');
		int calcium = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');
		int potassium = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');

		// Vitamins ------------------------------
		int vitaminA = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');
		int vitaminC = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');
		int vitaminD = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');
		int vitaminE = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');
		int vitaminK = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');
		int vitaminB1 = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');
		int vitaminB2 = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');
		int vitaminB3 = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');
		int vitaminB5 = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');
		int vitaminB6 = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');
		int vitaminB7 = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');
		int vitaminB9 = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');
		int vitaminB12 = StringZoo.getNextInt(goalString, indexToReadFrom, '=', ',');

		// Boolean flag --------------------------
		String booleanFlagString = goalString
				.substring(indexToReadFrom.getValue() + "onlyCheckBasicMacros=".length() + 2, goalString.length() - 1);
		boolean onlyCheckBasicMacros = booleanFlagString.equals("true") ? true : false;

		Goal goal = new Goal(type, calories, protein, fat, carbs, saturatedFat, transFat, fiber, sugar, sodium,
				cholesterol, calcium, potassium, vitaminA, vitaminC, vitaminD, vitaminE, vitaminK, vitaminB1, vitaminB2,
				vitaminB3, vitaminB5, vitaminB6, vitaminB7, vitaminB9, vitaminB12, onlyCheckBasicMacros);

		// A newly constructed Goal with the fields from file
		return goal;
	}

	private static FoodLog readFoodLog(String foodLogString) {
		// Indices of the user goal and log
		int begUsersGoalIndex = foodLogString.indexOf("usersGoal=") + "usersGoal=".length() + 1;
		int endUsersGoalIndex = foodLogString.indexOf("firstLoggedDay=") - 2;
		int begLogIndex = foodLogString.indexOf("log=") + "log=".length() + 1;
		int endLogIndex = foodLogString.length() - 2;

		// Get substring of user goal and log
		String usersGoalString = foodLogString.substring(begUsersGoalIndex, endUsersGoalIndex);
		String logString = foodLogString.substring(begLogIndex, endLogIndex);

		// Assign usersGoal
		Goal usersGoal = readGoal(usersGoalString);

		// Assign Log
		FoodLog log = new FoodLog(usersGoal);
		assignLog(log, logString);

		// Return log
		return log;
	}

	private static Day readCurrentDay(String currentDayString) {
		if (currentDayString.equals("User is not on a current day"))
			return null;

		return readDay(currentDayString);
	}

	private static void assignLog(FoodLog log, String logString) {
		// Checked if any days where logged
		if (logString.equals("No days logged"))
			return;

		// Use a scanner to read through logString, using the delimiter "Day "
		Scanner reader = (new Scanner(logString)).useDelimiter("Day ");

		// Add days to log for however many their are
		while (reader.hasNext())
			log.logDay(readDay(reader.next().trim()));

		// Close the scanner
		reader.close();
	}

	private static Day readDay(String dayString) {
		// Read in date
		String dateString = dayString.substring(dayString.indexOf("date=") + "date=".length(),
				dayString.indexOf("calories=") - 2);
		Date date = null;
		try {
			date = Day.SDF.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		try (
				// Use a scanner to read through meals, using the delimiter "Meal "
				Scanner reader = (new Scanner(dayString)).useDelimiter("Meal ")) {
			// Construct a day object to add to
			Day day = new Day(date);

			// Add meals to day, get to the next delimiter first
			reader.next();
			for (int i = 0; i < Day.NUMBER_OF_MEALS; i++)
				assignMealInDay(day, reader.next().trim(), i);

			// Close the scanner
			reader.close();

			// Return the day
			return day;
		}
	}

	private static void assignMealInDay(Day day, String mealString, int mealIndex) {
		// Use a scanner to read through foods, using the delimiter "Food "
		Scanner reader = (new Scanner(mealString)).useDelimiter("Food ");

		// Go through foods assigning to the meal in mealIndex, get to the next
		// delimiter first
		reader.next();
		while (reader.hasNext())
			day.addIndexFood(readFood(reader.next().trim()), mealIndex);

		// Close the scanner
		reader.close();
	}

	private static Food readFood(String foodString) {
		// Get food's name
		int endFoodIndex = foodString.indexOf(", servingSize=");
		String name = foodString.substring(foodString.indexOf("name=") + "name=".length(), endFoodIndex);

		// Set the wrapper index to be moved forward in foodString
		PrimitiveWrapper<Integer> indexToReadFrom = new PrimitiveWrapper<Integer>(endFoodIndex);

		// Get serving specifics
		float servingSize = StringZoo.getNextFloat(foodString, indexToReadFrom, '=', ',');
		float numberOfServings = StringZoo.getNextFloat(foodString, indexToReadFrom, '=', ',');

		// Get all macros

		// Basic macros --------------------------
		int calories = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');
		int protein = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');
		int fat = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');
		int carbs = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');

		// In depth macros -----------------------
		int saturatedFat = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');
		int transFat = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');
		int fiber = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');
		int sugar = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');
		int sodium = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');
		int cholesterol = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');
		int calcium = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');
		int potassium = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');

		// Vitamins ------------------------------
		int vitaminA = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');
		int vitaminC = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');
		int vitaminD = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');
		int vitaminE = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');
		int vitaminK = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');
		int vitaminB1 = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');
		int vitaminB2 = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');
		int vitaminB3 = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');
		int vitaminB5 = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');
		int vitaminB6 = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');
		int vitaminB7 = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');
		int vitaminB9 = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');
		int vitaminB12 = StringZoo.getNextInt(foodString, indexToReadFrom, '=', ',');

		// Boolean flag --------------------------
		String booleanFlagString = foodString
				.substring(indexToReadFrom.getValue() + "causesStomachIssues=".length() + 2, foodString.length() - 1);
		boolean causesStomachIssues = booleanFlagString.equals("true") ? true : false;

		Food f = new Food(name, servingSize, 1, calories, protein, fat, carbs, saturatedFat, transFat, fiber, sugar,
				sodium, cholesterol, calcium, potassium, vitaminA, vitaminC, vitaminD, vitaminE, vitaminK, vitaminB1,
				vitaminB2, vitaminB3, vitaminB5, vitaminB6, vitaminB7, vitaminB9, vitaminB12, causesStomachIssues);

		/*
		 * Added since update which uses number of servings to set food macros by
		 * multiplying it by number servings right when is constructed. The set number
		 * of servings method only updates the number of servings and does not multiply
		 * to other food macros, since those are already correct.
		 */
		f.setNumberOfServings(numberOfServings);

		return f;
	}

}

package model.implementation.user;

import java.util.Date;

import model.implementation.day.Day;
import model.implementation.food_log.FoodLog;
import model.implementation.goal.Goal;

/**
 * Represents a FoodLogger user which keep tracks of a user name, a goal (which
 * a user must have, if they don't have one then all macro goals will be set to
 * 0), a Log of all the days they logged, and the current food day they are on.
 * 
 * @author Garrett Presley
 *
 */
public class FoodLoggerUser extends AbstractUser {

	/** Represents the user's Goal */
	private Goal usersGoal;
	/** Represents the user's food log */
	private FoodLog foodLog;
	/** Represents the user's current food day */
	private Day currentDay;

	/**
	 * A full constructor which sets the user name, goal, log, and current day.
	 * 
	 * @param userName   the name to set
	 * @param usersGoal  the goal to set
	 * @param foodLog    the log to set
	 * @param currentDay the current day to set
	 */
	public FoodLoggerUser(String userName, Goal usersGoal, FoodLog foodLog, Day currentDay) {
		super(userName);
		setFoodLog(foodLog);
		setUsersGoal(usersGoal);
		setCurrentDay(currentDay);
	}

	/**
	 * An almost full constructor which sets the user name, goal, and food log. Sets
	 * the current day to null. If current day is null it is set to today.
	 * 
	 * @param userName  the name to set
	 * @param usersGoal the goal to set
	 * @param foodLog   the log to set
	 */
	public FoodLoggerUser(String userName, Goal usersGoal, FoodLog foodLog) {
		this(userName, usersGoal, foodLog, null);
	}

	/**
	 * A half full constructor that sets the user name, and goal.
	 * 
	 * @param userName  the name to set
	 * @param usersGoal the goal to set
	 */
	public FoodLoggerUser(String userName, Goal usersGoal) {
		this(userName, usersGoal, new FoodLog());
	}

	/**
	 * A partially full constructor setting only the user name. Goal is set an all
	 * 0's goal meaning all macros within the goal are set to zero.
	 * 
	 * @param userName the name to set
	 */
	public FoodLoggerUser(String userName) {
		this(userName, null);
	}

	/**
	 * Logs a day in the user's log.
	 * 
	 * @param day the day to log
	 */
	public void logDay(Day day) {
		foodLog.logDay(day);
	}

	/**
	 * Starts the users current day.
	 * 
	 * @param day the day to set to current day
	 */
	private void startCurrentDay(Day day) {
		logDay(getCurrentDay());
		setCurrentDay(day);
	}

	/**
	 * Checks if the user needs to start a new current day. Checks to see if it is a
	 * new day, if it is old current day is logged in users log, and new current day
	 * is started.
	 */
	public void startNewDayIfNeeded() {
		Date dateToCheck = new Date();
		String dateStringToCheck = Day.SDF.format(dateToCheck);
		if (!dateStringToCheck.equals(currentDay.getDateString()))
			startCurrentDay(new Day());
	}

	/**
	 * Resets the user's goal given the new goal provided.
	 * 
	 * @param g the goal to set too
	 * @return the old goal
	 */
	public Goal resetUsersGoal(Goal g) {
		Goal oldGoal = usersGoal;
		usersGoal = g;
		return oldGoal;
	}

	/**
	 * Returns a almost full string representation of a user, revealing their name,
	 * current day, and log info.
	 * 
	 * @return String representation of user
	 */
	public String toString() {
		return getUserName() + ", currentDay=\n" + currentDay.toString() + ", foodLog=\n" + foodLog.toString();
	}

	/**
	 * Returns a fill string representation of a user, revealing their name, current
	 * day, goal, and log info.
	 * 
	 * @return a full String representation of user
	 */
	public String toFullString() {
		return ("FoodLoggerUser [\nuserName=\n" + getUserName() + "\nusersGoal=\n"
				+ getUsersGoal().toFullString().trim() + "\ncurrentDay=\n" + getCurrentDay().toFullString().trim()
				+ "\nfoodLog=\n" + foodLog.toFullString().trim() + "\n]").trim();
	}

	/**
	 * Returns the user's food log.
	 * 
	 * @return the log
	 */
	public FoodLog getFoodLog() {
		return foodLog;
	}

	/**
	 * Sets the user food log, can only be done once.
	 * 
	 * @param foodLog the log to set
	 */
	private void setFoodLog(FoodLog foodLog) {
		this.foodLog = foodLog;
	}

	/**
	 * Returns the user's current day.
	 * 
	 * @return the current day
	 */
	public Day getCurrentDay() {
		return currentDay;
	}

	/**
	 * Sets the user's current day.
	 * 
	 * @param currentDay the current day to set
	 */
	private void setCurrentDay(Day currentDay) {
		if (currentDay == null)
			currentDay = new Day();
		this.currentDay = currentDay;
	}

	/**
	 * Returns the user's goal.
	 * 
	 * @return the user's goal
	 */
	public Goal getUsersGoal() {
		return usersGoal;
	}

	/**
	 * Sets the user's goal. Can be changed at any time. Checks if the goal is null,
	 * if it is automatically sets it an all zero goal. Also sets the goal in the
	 * food log to check.
	 * 
	 * @param usersGoal the goal to set
	 */
	public void setUsersGoal(Goal usersGoal) {
		if (usersGoal == null)
			usersGoal = new Goal(0, 0, 0, 0);
		this.usersGoal = usersGoal;
		foodLog.setUsersGoal(usersGoal);
	}

}

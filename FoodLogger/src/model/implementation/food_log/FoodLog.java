package model.implementation.food_log;

import model.implementation.day.Day;
import model.implementation.goal.Goal;
import model.util.map.Map;
import model.util.map.SkipListMap;

public class FoodLog {

	/**
	 * A map of all the food contained in the food log, key=dateString, value=day
	 */
	private Map<String, Day> log;

	private Goal usersGoal;

	private String firstLoggedDay;

	private boolean containsALog;

	private int daysWereGoalsWhereMet;

	private int daysCounter;

	private FoodLog(Map<String, Day> log, String firstLoggedDay, boolean containsALog, int daysWereGoalsWhereMet,
			int daysCounter, Goal usersGoal) {
		this.log = log;
		this.firstLoggedDay = firstLoggedDay;
		this.containsALog = containsALog;
		this.daysWereGoalsWhereMet = daysWereGoalsWhereMet;
		this.daysCounter = daysCounter;
		this.usersGoal = usersGoal;
	}

	public FoodLog(String firstLoggedDay, boolean containsALog, int daysWereGoalsWhereMet, int daysCounter,
			Goal usersGoal) {
		this(new SkipListMap<String, Day>(), firstLoggedDay, containsALog, daysWereGoalsWhereMet, daysCounter,
				usersGoal);
	}

	public FoodLog(Goal usersGoal) {
		this("No food logs yet", false, 0, 0, usersGoal);

	}

	public FoodLog() {
		this(new Goal(0, 0, 0, 0));
	}

	public boolean logDay(Day day) {
		if (log.get(day.getDateString()) != null)
			return false;
		log.put(day.getDateString(), day);
		if (usersGoal != null && usersGoal.getCalories() != 0 && usersGoal.goalsMeet(day))
			daysWereGoalsWhereMet++;
		if (!isContainsALog()) {
			setContainsALog(true);
			setFirstLoggedDay(day.getDateString());
		}
		daysCounter++;
		return true;
	}

	public boolean removeLogDay(String daysDateString) {
		if (daysCounter == 0 || log.get(daysDateString) == null)
			return false;
		Day dayToRemove = log.remove(daysDateString);
		if (usersGoal != null && usersGoal.getCalories() != 0 && usersGoal.goalsMeet(dayToRemove))
			daysWereGoalsWhereMet--;
		if (daysCounter == 0) {
			setContainsALog(false);
			setFirstLoggedDay("No food logs yet");
		}
		daysCounter--;
		return true;
	}

	public Day getLoggedDay(String dateString) {
		return log.get(dateString);
	}

	public String[] getDatesStringArray() {
		String[] dates = new String[log.size()];
		int i = 0;
		for (Map.Entry<String, Day> entry : log.entrySet()) {
			String key = entry.getKey();
			dates[i++] = key;
		}
		return dates;
	}

	public Map<Integer, Year> getYears() {
		// Construct a map of years to return at end of method
		Map<Integer, Year> years = new SkipListMap<Integer, Year>();

		for (Map.Entry<String, Day> entry : log.entrySet()) {
			Day val = entry.getValue();

			// Parse day's year and month numbers into numbers
			String key = entry.getKey();
			int indexOfMonth = key.indexOf("-");
			String yearString = key.substring(0, indexOfMonth);
			String monthString = key.substring(indexOfMonth + 1, key.length() - 3);
			if (monthString.startsWith("0"))
				monthString.replace("0", "");

			Integer year = Integer.parseInt(yearString);
			Integer month = Integer.parseInt(monthString);

			if (years.get(year) == null)
				years.put(year, new Year(yearString));

			if (years.get(year).getMonths().get(month) == null)
				years.get(year).getMonths().put(month, Month.getMonth(month));

			years.get(year).getMonths().get(month).getDays().put(key.substring(indexOfMonth + 4), val);
		}

		// Return the years
		return years;
	}

	private String logToString(boolean toFullString) {
		if (log.isEmpty())
			return "No days logged";
		StringBuilder logString = new StringBuilder("");
		for (Map.Entry<String, Day> entry : log.entrySet()) {
			Day day = entry.getValue();
			logString.append(toFullString ? day.toFullString() : day.toString());
		}
		return logString.toString();
	}

	@Override
	public String toString() {
		String stringOfUsersGoal = usersGoal == null ? "No goal provdied" : usersGoal.toString();
		return ("usersGoal=" + stringOfUsersGoal + ", firstLoggedDay=" + firstLoggedDay + ", containsALog="
				+ containsALog + ", daysWereGoalsWhereMet=" + daysWereGoalsWhereMet + ", daysCounter=" + daysCounter
				+ ", log=\n" + logToString(false)).trim();
	}

	public String toFullString() {
		String stringOfUsersGoal = usersGoal == null ? "No goal provdied" : usersGoal.toFullString();
		return ("FoodLog [\nusersGoal=\n" + stringOfUsersGoal + ",\nfirstLoggedDay=" + firstLoggedDay
				+ ",\ncontainsALog=" + containsALog + ",\ndaysWereGoalsWhereMet=" + daysWereGoalsWhereMet
				+ ",\ndaysCounter=" + daysCounter + ",\nlog=\n" + logToString(true) + "\n]").trim();
	}

	public Map<String, Day> getLog() {
		return log;
	}

	public String getFirstLoggedDay() {
		return firstLoggedDay;
	}

	public void setFirstLoggedDay(String firstLoggedDay) {
		this.firstLoggedDay = firstLoggedDay;
	}

	public boolean isContainsALog() {
		return containsALog;
	}

	public void setContainsALog(boolean containsALog) {
		this.containsALog = containsALog;
	}

	public int getDaysWereGoalsWereMet() {
		return daysWereGoalsWhereMet;
	}

	public void setDaysWereGoalsWereMet(int daysWereGoalsWereMet) {
		this.daysWereGoalsWhereMet = daysWereGoalsWereMet;
	}

	public int getDaysCounter() {
		return daysCounter;
	}

	public void setDaysCounter(int daysCounter) {
		this.daysCounter = daysCounter;
	}

	public Goal getUsersGoal() {
		return usersGoal;
	}

	public void setUsersGoal(Goal usersGoal) {
		this.usersGoal = usersGoal;
	}

}

package model.implementation.food_log;

import model.implementation.day.Day;
import model.util.map.Map;
import model.util.map.SkipListMap;

public class Month {
	// All months
	private static final Month JAN = new Month("January", 1);
	private static final Month FEB = new Month("February", 2);
	private static final Month MAR = new Month("March", 3);
	private static final Month APR = new Month("April", 4);
	private static final Month MAY = new Month("May", 5);
	private static final Month JUN = new Month("June", 6);
	private static final Month JUL = new Month("July", 7);
	private static final Month AUG = new Month("August", 8);
	private static final Month SEP = new Month("September", 9);
	private static final Month OCT = new Month("October", 10);
	private static final Month NOV = new Month("November", 11);
	private static final Month DEC = new Month("December", 12);

	private Map<String, Day> days;
	private String monthName;
	private int monthPlace;

	private Month(Map<String, Day> days, String monthName, int monthPlace) {
		this.days = days;
		this.monthName = monthName;
		this.monthPlace = monthPlace;
	}

	private Month(String monthName, int monthPlace) {
		this(new SkipListMap<String, Day>(), monthName, monthPlace);
	}

	public static Month getMonth(int monthPlace) {
		switch (monthPlace) {
		case 1:
			return JAN;
		case 2:
			return FEB;
		case 3:
			return MAR;
		case 4:
			return APR;
		case 5:
			return MAY;
		case 6:
			return JUN;
		case 7:
			return JUL;
		case 8:
			return AUG;
		case 9:
			return SEP;
		case 10:
			return OCT;
		case 11:
			return NOV;
		case 12:
			return DEC;
		default:
			throw new IllegalArgumentException("Their is not a " + monthPlace + " month in a year.");
		}
	}

	public static int convertMonthNameToNum(String monthName) {
		switch (monthName) {
		case "January":
			return JAN.getMonthPlace();
		case "February":
			return FEB.getMonthPlace();
		case "March":
			return MAR.getMonthPlace();
		case "April":
			return APR.getMonthPlace();
		case "May":
			return MAY.getMonthPlace();
		case "June":
			return JUN.getMonthPlace();
		case "July":
			return JUL.getMonthPlace();
		case "August":
			return AUG.getMonthPlace();
		case "September":
			return SEP.getMonthPlace();
		case "October":
			return OCT.getMonthPlace();
		case "November":
			return NOV.getMonthPlace();
		case "December":
			return DEC.getMonthPlace();
		default:
			throw new IllegalArgumentException("Their is not a " + monthName + " month in a year.");

		}
	}

	public Map<String, Day> getDays() {
		return days;
	}

	public String getMonthName() {
		return monthName;
	}

	public int getMonthPlace() {
		return monthPlace;
	}

	public class MonthFinder {

	}

}

package model.implementation.food_log;

import model.util.map.Map;
import model.util.map.SkipListMap;

public class Year {
	private Map<Integer, Month> months;
	private String yearString;

	private Year(Map<Integer, Month> months, String yearString) {
		this.months = months;
		setYearString(yearString);
	}

	public Year(String yearString) {
		this(new SkipListMap<Integer, Month>(), yearString);
	}

	public Map<Integer, Month> getMonths() {
		return months;
	}

	public String getYearString() {
		return yearString;
	}

	public void setYearString(String yearString) {
		this.yearString = yearString;
	}

}

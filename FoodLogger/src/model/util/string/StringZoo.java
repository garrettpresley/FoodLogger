package model.util.string;

import model.util.wrapper.PrimitiveWrapper;

public class StringZoo {

	public static final int DEFAULT_BOX_STRING_WIDTH = 100;
	public static final char DEFAULT_BOX_STRING_BORDER_CHAR = '%';
	public static final String DEFAULT_BOX_STRING_BORDER_STRING = "%";

	public static String repeatString(String s, int times) {

		StringBuilder str = new StringBuilder();
		for (int i = 0; i < times; i++)
			str.append(s);
		return str.toString();
	}

	public static String stringBoxRow(String s, int maxChars, char charAtEnds) {
		StringBuilder str = new StringBuilder();
		str.append(charAtEnds);
		boolean isCharAt = true;
		for (int i = 0; i < maxChars; i++) {
			if (isCharAt) {
				try {
					str.append(s.charAt(i));
				} catch (IndexOutOfBoundsException e) {
					isCharAt = false;
					str.append(' ');
				}
			} else {
				str.append(' ');
			}
		}
		str.append(charAtEnds);
		return str.toString();
	}

	/**
	 * Returns a String that is surrounds the given String s with a character c.
	 * 
	 * @param s the String to surround
	 * @param c the character to surround String s with
	 * @return the string s surrounded by c
	 */
	public static String surroundString(String s, char c) {
		StringBuilder sB = new StringBuilder();
		sB.append(repeatString(Character.toString(c), s.length() + 2));
		sB.append("\n");
		sB.append(c);
		sB.append(s);
		sB.append(c);
		sB.append("\n");
		sB.append(repeatString(Character.toString(c), s.length() + 2));
		return sB.toString();
	}

	/**
	 * Takes in a String and starts reading from begIndex, finds the closest Integer
	 * near begIndex and returns it.
	 * 
	 * @param s        the string to read from
	 * @param begIndex the beginning index of which to start reading from s
	 * @return the nearest integer from begIndex
	 */
	public static int getNextInt(String s, PrimitiveWrapper<Integer> begIndex) {
		StringBuilder intInStringBuiilder = new StringBuilder("");
		for (int i = begIndex.getValue(); i < s.length(); i++) {
			char curr = s.charAt(i);
			if (Character.isDigit(curr)) {
				for (; Character.isDigit(curr); i++, curr = s.charAt(i)) {
					if (i == s.length()) {
						throw new IllegalArgumentException("String is too long to parse.");
					}
					intInStringBuiilder.append(curr);
				}
				begIndex.setValue(i);
				break;
			}
		}
		String intInString = intInStringBuiilder.toString();

		// If their were no integers in s
		if (intInString.isEmpty())
			throw new IllegalArgumentException("Thier are no ints in '" + s + "'.");

		return Integer.parseInt(intInString);
	}

	/**
	 * Takes in a String and starts reading from begIndex, finds the closest Integer
	 * near begIndex and returns it. Only seeks out integers in strictly in between
	 * beforeDil and afterDil.
	 * 
	 * @param s         the string to read from
	 * @param begIndex  the beginning index of which to start reading from s
	 * @param beforeDil the character delimiter before the integer
	 * @param afterDil  the character delimiter after the integer
	 * 
	 * @return the nearest integer from begIndex
	 */
	public static int getNextInt(String s, PrimitiveWrapper<Integer> begIndex, char beforeDil, char afterDil) {
		StringBuilder intInStringBuiilder = new StringBuilder("");
		for (int i = begIndex.getValue(); i < s.length(); i++) {
			char curr = s.charAt(i);
			if (curr == beforeDil) {
				curr = s.charAt(++i);
				for (; curr != afterDil && Character.isDigit(curr); i++, curr = s.charAt(i)) {
					if (i == s.length()) {
						throw new IllegalArgumentException("String is too long to parse.");
					}
					intInStringBuiilder.append(curr);
				}
				begIndex.setValue(i);
				break;
			}
		}
		String intInString = intInStringBuiilder.toString();

		// If their were no integers in s
		if (intInString.isEmpty())
			throw new IllegalArgumentException("Thier are no ints in '" + s + "'.");

		return Integer.parseInt(intInString);
	}

	/**
	 * Takes in a String and starts reading from begIndex, finds the closest Float
	 * near begIndex and returns it.
	 * 
	 * @param s        the string to read from
	 * @param begIndex the beginning index of which to start reading from s
	 * @return the nearest float from begIndex
	 */
	public static float getNextFloat(String s, PrimitiveWrapper<Integer> begIndex) {
		StringBuilder floatInStringBuilder = new StringBuilder("");
		for (int i = begIndex.getValue(); i < s.length(); i++) {
			char curr = s.charAt(i);
			if (Character.isDigit(curr)) {
				for (; Character.isDigit(curr) || curr == '.'; i++, curr = s.charAt(i)) {
					if (i == s.length()) {
						throw new IllegalArgumentException("String is too long to parse.");
					}
					floatInStringBuilder.append(curr);
				}
				begIndex.setValue(i);
				break;
			}
		}
		String floatInString = floatInStringBuilder.toString();

		// If their were no integers in s
		if (floatInString.isEmpty())
			throw new IllegalArgumentException("Thier are no ints in '" + s + "'.");

		return Float.parseFloat(floatInString);
	}

	/**
	 * Takes in a String and starts reading from begIndex, finds the closest float
	 * near begIndex and returns it. Only seeks out float in strictly in between
	 * beforeDil and afterDil.
	 * 
	 * @param s         the string to read from
	 * @param begIndex  the beginning index of which to start reading from s
	 * @param beforeDil the character delimiter before the integer
	 * @param afterDil  the character delimiter after the integer
	 * 
	 * @return the nearest float from begIndex
	 */
	public static float getNextFloat(String s, PrimitiveWrapper<Integer> begIndex, char beforeDil, char afterDil) {
		StringBuilder floatInStringBuilder = new StringBuilder("");
		for (int i = begIndex.getValue(); i < s.length(); i++) {
			char curr = s.charAt(i);
			if (curr == beforeDil) {
				curr = s.charAt(++i);
				for (; curr != afterDil && (Character.isDigit(curr) || curr == '.'); i++, curr = s.charAt(i)) {
					if (i == s.length()) {
						throw new IllegalArgumentException("String is too long to parse.");
					}
					floatInStringBuilder.append(curr);
				}
				begIndex.setValue(i);
				break;
			}
		}
		String floatInString = floatInStringBuilder.toString();

		// If their were no integers in s
		if (floatInString.isEmpty())
			throw new IllegalArgumentException("Thier are no floats in '" + s + "'.");

		return Float.parseFloat(floatInString);
	}

	/**
	 * Pads the String s with paddingChar up to maxSize.
	 * 
	 * @param s           the String to pad
	 * @param maxSize     the max size to pad to
	 * @param paddingChar the character to pad with
	 * @return the String padded
	 */
	public static String rightPad(String s, int maxSize, char paddingChar) {
		StringBuilder sB = new StringBuilder(s);
		for (int i = s.length(); i < maxSize; i++)
			sB.append(paddingChar);

		return sB.toString();
	}
}
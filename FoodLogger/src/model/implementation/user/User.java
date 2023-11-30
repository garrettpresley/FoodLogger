package model.implementation.user;

/**
 * Represents an Idea of a user. For the current version of FoodLogger all data
 * is stored locally on device so only thing to worry about is User's Name, can
 * only set user name once in the constructor, so only allowed to get user name
 * after construction.
 * 
 * @author Garrett Presley
 *
 */
public interface User {

	/**
	 * Returns the name of the user
	 * 
	 * @return the name of the user
	 */
	public String getUserName();

}

package model.implementation.user;

/**
 * Represents an abstract User, FoodLogger only worries about User Name at this
 * point, so the only thing it maintains is a User Name.
 * 
 * @author Garrett Presley
 *
 */
public class AbstractUser implements User {

	/**
	 * A reference to the name of the user, can only be set once, and must not
	 * contain spaces and certain other words.
	 * 
	 */
	private String userName;

	/**
	 * Constructs an Abstract User, setting the name of the user to the provided
	 * String userName. User Name can only be set once, must not contain spaces, and
	 * certain words cannot be user. The words that cannot be used are:
	 * 
	 * @param userName the user name to set only once
	 * @throws IllegalArgumentException if name does not meet criteria
	 */
	public AbstractUser(String userName) {
		setUserName(userName);
	}

	/**
	 * Sets the user name of the user, this can only be done once. Checks the user
	 * name for spaces and certain words.
	 * 
	 * @param userName the user name to check
	 * @throws IllegalArgumentException if name does not meet criteria
	 */
	private void setUserName(String userName) {
		checkUsername(userName);
		this.userName = userName;
	}

	/**
	 * Returns the name of the user.
	 * 
	 * @return the name of the user
	 */
	@Override
	public String getUserName() {
		return userName;
	}

	/**
	 * Checks the given user name for spaces and certain prohibited words. The
	 * reason these words are prohibited is that they are used in IO or similar
	 * implementations in the model. Also name cannot be null or empty
	 * 
	 * @param userName the user name to check
	 * @throws IllegalArgumentException if name does not meet criteria
	 */
	private void checkUsername(String userName) {
		if (userName == null)
			throw new IllegalArgumentException("A user name cannot be null.");
		if (userName.isEmpty())
			throw new IllegalArgumentException("A user name cannot empty.");
		if (userName.contains(" "))
			throw new IllegalArgumentException("A user name cannot contains spaces.");
	}

}

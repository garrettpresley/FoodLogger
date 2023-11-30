package model.util.wrapper;

/**
 * Represents a wrapper class of any type so as to pass them by reference into
 * method parameters.
 * 
 * @author Garrett Presley
 *
 * @param <E> the primitive type to store
 */
public interface Wrapper<E> {
	
	/**
	 * Returns the value.
	 * 
	 * @return the value
	 */
	public E getValue();

	/**
	 * Sets the value.
	 * 
	 * @param value the value to set
	 */
	public void setValue(E value);
}
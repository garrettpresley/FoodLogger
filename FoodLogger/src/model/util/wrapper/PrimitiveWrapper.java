package model.util.wrapper;

/**
 * Represents a wrapper class of primitive types so as to pass them by reference
 * into method parameters.
 * 
 * @author Garrett Presley
 *
 * @param <E> the primitive type to store
 */
public class PrimitiveWrapper<E> implements Wrapper<E> {
	
	/**
	 * The primitive type to store.
	 */
	private E value;

	/**
	 * Constructs a PrimitiveWrapper object and setting its value to the given
	 * value.
	 * 
	 * @param value the value to set
	 */
	public PrimitiveWrapper(E value) {
		setValue(value);
	}

	/**
	 * Returns the primitive value.
	 * 
	 * @return the primitive value
	 */
	public E getValue() {
		return value;
	}

	/**
	 * Sets the primitive value.
	 * 
	 * @param value the value to set
	 */
	public void setValue(E value) {
		this.value = value;
	}

	/**
	 * Prints the value stored.
	 * 
	 * @return String representation of value
	 */
	@Override
	public String toString() {
		return value.toString();
	}

}
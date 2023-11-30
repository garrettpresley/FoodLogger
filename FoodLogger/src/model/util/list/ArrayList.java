package model.util.list;

import java.util.Iterator;

/**
 * A custom ArrayList class that checks for null objects and duplicate objects,
 * all other functionality is equivalent to ArrayList class.
 * 
 * @author Garrett Presley
 * @author andyzhao
 *
 * @param <E> the type of object to be contained in list
 */
public class ArrayList<E> implements List<E> {

	/** The initial length of the array */
	private final static int INIT_SIZE = 10;
	/** The generic type array to be used for storing objects */
	private E[] list;
	/** Size stores the number of objects inside of the list */
	private int size;

	/**
	 * Constructs an empty list with an initial length of 10.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		list = (E[]) new Object[INIT_SIZE];
		size = 0;
	}

	/**
	 * Adds an object at given index. Checks if index is in bounds. Checks if object
	 * is null or duplicate.
	 * 
	 * @param index  the index of which to add the object
	 * @param object the object to add
	 * @throws IndexOutOfBoundsException if the index is less than zero or greater
	 *                                   than size
	 */
	public void add(int index, E object) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		checkObject(object);
		checkDuplicate(object);
		// Grow the array if needed
		growArray();
		for (int i = size; i > index; i--) {
			list[i] = list[i - 1];
		}
		list[index] = object;
		size++;
	}

	/**
	 * Helper method for add method, checks to see if size is at the length of the
	 * list if it is the contents of list are copied over to another list so the
	 * length of list can be doubled and contents are move back into list.
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		if (size + 1 >= list.length) {
			E[] copy = (E[]) new Object[list.length * 2];
			for (int i = 0; i < list.length; i++) {
				copy[i] = list[i];
			}
			list = copy;
		}
	}

	/**
	 * Removes the object at given index. Checks if index is in bounds.
	 * 
	 * @param index the index of the object to be removed
	 * @return E the object that was removed
	 * @throws IndexOutOfBoundsException if the index is less than zero or greater
	 *                                   than size - 1
	 */
	public E remove(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		E objectRemoved = list[index];
		for (int i = index; i < size - 1; i++) {
			list[i] = list[i + 1];
		}
		size--;
		list[size] = null;
		return objectRemoved;
	}

	/**
	 * Sets the object at the given index to the given object. Checks if index is in
	 * bounds. Checks if object is null or duplicate.
	 * 
	 * @param index  the index of which to set object at
	 * @param object the object to set
	 * @return E the object that was removed
	 * @throws IndexOutOfBoundsException if the index is less than zero or greater
	 *                                   than size - 1
	 */
	public E set(int index, E object) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		checkObject(object);
		checkDuplicate(object);
		E objectRemoved = list[index];
		list[index] = object;
		return objectRemoved;
	}

	/**
	 * Returns the object at given index. Checks if index is in bounds.
	 * 
	 * @param index the index of which to find the object
	 * @return E the object at the given index
	 * @throws IndexOutOfBoundsException if the index is less than zero or greater
	 *                                   than size - 1
	 */
	public E get(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		return list[index];
	}

	/**
	 * Returns the size of the array list.
	 *
	 * @return size the size
	 */
	public int size() {
		return size;
	}

	/**
	 * Checks to see if the given object is valid, meaning the object is not null.
	 * 
	 * @param object the object to check
	 * @throws NullPointerException if object is null
	 */
	private void checkObject(E object) {
		if (object == null) {
			throw new NullPointerException();
		}
	}

	/**
	 * Checks to see if given object is duplicate of another object within the list.
	 * 
	 * @param object the object to check
	 * @throws IllegalArgumentException if the object is a duplicate of another
	 */
	private void checkDuplicate(E object) {
		for (int i = 0; i < size; i++) {
			if (object.equals(list[i])) {
				throw new IllegalArgumentException();
			}
		}
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFirst(E element) {
		add(0, element);
	}

	@Override
	public void addLast(E element) {
		add(size(), element);
	}

	@Override
	public E first() {
		return get(0);
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public E last() {
		return get(size() - 1);
	}

	@Override
	public E removeFirst() {
		return remove(0);
	}

	@Override
	public E removeLast() {
		return remove(size() - 1);
	}

}
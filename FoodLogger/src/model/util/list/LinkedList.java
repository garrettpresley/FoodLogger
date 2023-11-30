
package model.util.list;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Class used for LinkedList objects which will extend the
 * AbstractSequentialList class and will be able to use ListIterator.
 * 
 * @author Jimmy Do, Garrett Presley, JR Boos
 *
 * @param <E> the type of data to store
 */
public class LinkedList<E> extends AbstractSequentialList<E> implements List<E> {

	/** The front of the list */
	private ListNode front;
	/** The back of the list */
	private ListNode back;
	/** The size of the list */
	private int size;

	/**
	 * Constructs a LinkedAbstractList.
	 */
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null);
		front.next = back;
		back.prev = front;
		size = 0;
	}

	/**
	 * Creates a ListIterator object.
	 * 
	 * @param index the index to set the iterator to
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		return new LinkedListIterator(index);
	}

	/**
	 * Returns the size of the list
	 * 
	 * @return the size
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Sets the given element at the given index
	 * 
	 * @param index   the index of the element to override
	 * @param element the element to override
	 */
	@Override
	public E set(int index, E element) {
		if (super.contains(element)) {
			throw new IllegalArgumentException();
		}
		return super.set(index, element);
	}

	/**
	 * Adds an element at the given index
	 * 
	 * @param index   the index at which to add
	 * @param element the element to add
	 */
	@Override
	public void add(int index, E element) {
		if (super.contains(element)) {
			throw new IllegalArgumentException();
		}
		super.add(index, element);
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. More formally, returns
	 * the lowest index {@code i} such that {@code Objects.equals(o, get(i))}, or -1
	 * if there is no such index.
	 *
	 * @param o element to search for
	 * @return the index of the first occurrence of the specified element in this
	 *         list, or -1 if this list does not contain the element
	 */
	@Override
	public int indexOf(Object o) {
		int index = -1;

		for (ListNode current = front; current != null; current = current.next) {
			if (o.equals(current.data)) {
				return index;
			}
			index++;
		}

		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. More formally, returns
	 * the highest index {@code i} such that {@code Objects.equals(o, get(i))}, or
	 * -1 if there is no such index.
	 *
	 * @param o element to search for
	 * @return the index of the last occurrence of the specified element in this
	 *         list, or -1 if this list does not contain the element
	 */
	@Override
	public int lastIndexOf(Object o) {
		int index = -1;
		int lastIndexOfItem = 0;

		boolean inList = false;

		for (ListNode current = front; current != null; current = current.next) {
			if (o.equals(current.data)) {
				inList = true;
				lastIndexOfItem = index;
			}
			index++;
		}
		if (inList) {
			return lastIndexOfItem;
		} else {
			return -1;
		}
	}

	@Override
	public void addFirst(E element) {
		add(0, element);
	}

	@Override
	public void addLast(E element) {
		add(size, element);
	}

	@Override
	public E first() {
		return get(0);
	}

	@Override
	public E last() {
		return get(size - 1);
	}

	@Override
	public E removeFirst() {
		return remove(0);
	}

	@Override
	public E removeLast() {
		return remove(size - 1);
	}

	/**
	 * Represents data in the form of ListNode objects.
	 * 
	 * @author Garrett Presley
	 *
	 */
	private class ListNode {
		/** The type of data to store in the list */
		private E data;
		/** The next node in the list */
		public ListNode next;
		/** The previous node in the list */
		public ListNode prev;

		/**
		 * Constructs a ListNode object assigning its data, previous node and the next
		 * node.
		 * 
		 * @param prev the previous node
		 * @param data the type of data to be stored
		 * @param next the next node
		 */
		public ListNode(ListNode prev, E data, ListNode next) {
			this.prev = prev;
			this.data = data;
			this.next = next;
		}

		/**
		 * Constructs a list node with just data and assigning the previous and next
		 * nodes to null
		 * 
		 * @param data the type of data to be stored
		 */
		public ListNode(E data) {
			this(null, data, null);
		}

	}

	/**
	 * An iterator to iterate through elements in the LinkedList.
	 * 
	 * @author garrettpresley
	 *
	 */
	private class LinkedListIterator implements ListIterator<E> {
		/** The next node in the list */
		private ListNode next;
		/** The previous node in the list */
		private ListNode prev;
		/** The previous index */
		private int previousIndex;
		/** The next index */
		private int nextIndex;
		/** The last ListNode that was retrieved */
		private ListNode lastRetrieved;

		/**
		 * Constructs an iterator at the given index.
		 * 
		 * @param index the index to set iterator at
		 */
		public LinkedListIterator(int index) {
			if (index < 0 || index > size) {
				throw new IndexOutOfBoundsException("Index out of bounds.");
			}
			next = front;
			prev = front;
			for (int i = 0; i <= index; i++) {
				next = next.next;
			}
			for (int i = 0; i <= index - 1; i++) {
				prev = prev.next;
			}
			nextIndex = index;
			previousIndex = index - 1;
			lastRetrieved = null;

		}

		/**
		 * Checks to see if the list has a next node.
		 * 
		 * @return true if list has a next node
		 */
		@Override
		public boolean hasNext() {
			return next.data != null;
		}

		/**
		 * Returns the next node in the list.
		 * 
		 * @return the next node's data
		 * @throws NoSuchElementException if there is not a next element
		 */
		@Override
		public E next() {
			if (next.data == null) {
				throw new NoSuchElementException("No such element.");
			} else {
				lastRetrieved = next;
				E nextData = next.data;
				next = next.next;
				return nextData;
			}
		}

		/**
		 * Checks to see if the list has a previous node.
		 * 
		 * @return true if list has a previous node
		 */
		@Override
		public boolean hasPrevious() {
			return prev.data != null;
		}

		/**
		 * Returns the previous node in the list.
		 * 
		 * @return the previous node's data
		 * @throws NoSuchElementException if there is not a previous element
		 */
		@Override
		public E previous() {
			if (prev.data == null) {
				throw new NoSuchElementException("No such element.(previous)");
			} else {
				lastRetrieved = prev;
				E prevData = prev.data;
				prev = prev.prev;
				return prevData;
			}
		}

		/**
		 * Returns the next index.
		 * 
		 * @return the next index
		 */
		@Override
		public int nextIndex() {
			return nextIndex;
		}

		/**
		 * Returns the previous index.
		 * 
		 * @return the previous index
		 */
		@Override
		public int previousIndex() {
			return previousIndex;
		}

		/**
		 * Removes an element where the iterator is at.
		 * 
		 * @throws IllegalStateException if iterator is not in a state to remove
		 */
		@Override
		public void remove() {
			// Check if iterator is in correct state to remove
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}

			prev.next = next;
			lastRetrieved.prev = null;

			next.prev = prev;
			lastRetrieved.next = null;

			// Remove data
			lastRetrieved.data = null;
			// Decrement size
			size--;
			// Update next if needed

			nextIndex--;

			// Remove node
			lastRetrieved = null;

		}

		/**
		 * Sets an element where the iterator is at.
		 * 
		 * @param e the element to set
		 * @throws NullPointerException  if the element being set is null
		 * @throws IllegalStateException if iterator is not in a state to set
		 */
		@Override
		public void set(E e) {
			// Error checking
			if (e == null) {
				throw new NullPointerException();
			}
			// Check if iterator is in correct state to set
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			// Set the element in lastRetrieved's spot
			lastRetrieved.data = e;
		}

		/**
		 * Adds an element where the iterator is at.
		 * 
		 * @param e the element to add
		 * @throws NullPointerException if the element being added is null
		 */
		@Override
		public void add(E e) {
			// Set lastRetrieved to null
			lastRetrieved = null;
			// Error checking
			if (e == null) {
				throw new NullPointerException();
			}

			// Add node in between previous and next
			ListNode newNode = new ListNode(prev, e, next);
			// Update next and previous
			ListNode beforeChange = prev;
			prev = newNode;
			beforeChange.next = newNode;

			// Increment data
			nextIndex++;
			size++;
		}

	}

}
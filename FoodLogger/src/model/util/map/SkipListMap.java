package model.util.map;

import java.util.Comparator;
import java.util.Random;

/**
 * A SkipListMap is an ordered (meaning entries are stored in a sorted order
 * based on the keys of the entries) linked-memory representation of the Map
 * abstract data type. This link-based map maintains several levels of linked
 * lists to help approximate the performance of binary search using a
 * linked-memory structure. SkipListMap ensures a O(logn) expected/average
 * runtime for lookUps, insertions, and deletions.
 *
 * The SkipListMap class is based on algorithms developed for use with the
 * textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 *
 * @author Garrett Presley
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class SkipListMap<K extends Comparable<K>, V> extends AbstractOrderedMap<K, V> {
	

	/**
	 * Coin tosses are used when inserting entries into the data structure to ensure
	 * 50/50 probability that an entry will be added to the current level of the
	 * skip list structure
	 */
	private Random coinToss;

	/**
	 * Start references the topmost, leftmost corner of the skip list. In other
	 * words, start references the sentinel front node at the top level of the skip
	 * list
	 */
	private SkipListNode<K, V> start;

	/**
	 * The number of entries stored in the map
	 */
	private int size;

	/**
	 * The number of levels of the skip list data structure
	 */
	private int height;

	/**
	 * Constructs a new SkipListMap where keys of entries are compared based on
	 * their natural ordering based on {@link Comparable#compareTo}
	 */
	public SkipListMap() {
		this(null);
	}

	/**
	 * Constructs a new SkipListMap where keys of entries are compared based on a
	 * provided {@link Comparator}
	 *
	 * @param compare a Comparator that defines comparisons rules for keys in the
	 *                map
	 */
	public SkipListMap(Comparator<K> compare) {
		super(compare);
		coinToss = new Random();
		// Create a dummy head node for the left "-INFINITY" sentinel tower
		start = new SkipListNode<K, V>(null);
		// Create a dummy tail node for the right "+INFINITY" sentinel tower
		start.setNext(new SkipListNode<K, V>(null));
		// Set the +INFINITY tower's previous to be the "start" node
		start.getNext().setPrevious(start);
		size = 0;
		height = 0;
	}

	/**
	 * Helper method to determine if an entry is one of the sentinel -INFINITY or
	 * +INFINITY nodes (containing a null key)
	 * 
	 * @param node the node to check
	 * @return if the node is a Sentinel
	 */
	private boolean isSentinel(SkipListNode<K, V> node) {
		return node.getEntry() == null;
	}

	/**
	 * Helper method to find the location of the SkipListNode with the given key
	 * 
	 * @param key the key to check
	 * @return the node with the given key
	 */
	private SkipListNode<K, V> lookUp(K key) {
		SkipListNode<K, V> current = start;
		while (current.below != null) {
			current = current.below;
			while (!isSentinel(current.next) && compare(key, current.next.getEntry().getKey()) >= 0) {
				current = current.next;
			}
		}
		return current;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public V get(K key) {
		SkipListNode<K, V> temp = lookUp(key);
		if (temp == null || temp.getEntry() == null || compare(key, temp.getEntry().getKey()) != 0) {
			return null;
		} else {
			return temp.getEntry().getValue();
		}
	}

	/**
	 * Helper method to insert a node above in the next higher level while
	 * connecting are nodes in that level together.
	 * 
	 * @param prev  the node before entry
	 * @param down  the node below entry
	 * @param entry the entry to be added
	 * @return the new node with the contents of entry all linked up with rest of
	 *         list
	 */
	@SuppressWarnings("unchecked")
	private SkipListNode<K, V> insertAfterAbove(SkipListNode<K, V> prev, SkipListNode<K, V> down, Entry<K, V> entry) {
		// Create a new skip list node
		@SuppressWarnings({ "rawtypes" })
		SkipListNode newNode = new SkipListNode(entry);
		// Set the below and previous entries
		newNode.setBelow(down);
		newNode.setPrevious(prev);
		// Update the next and previous entry pointers
		if (prev != null) {
			newNode.setNext(prev.getNext());
			newNode.getPrevious().setNext(newNode);
		}
		if (newNode.getNext() != null) {
			newNode.getNext().setPrevious(newNode);
		}
		// Update the below entry pointers
		if (down != null) {
			down.setAbove(newNode);
		}
		return newNode;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public V put(K key, V value) {
		// Get the bottom-most entry immediately before the insertion location
		SkipListNode<K, V> temp = lookUp(key);
		// If entry with the given key already exists
		if (temp.getEntry() != null && compare(key, temp.getEntry().getKey()) == 0) {
			V valueRepacled = temp.getEntry().getValue();
			MapEntry<K, V> entryToAdd = new MapEntry<K, V>(key, value);
			while (temp != null) {
				temp.setEntry(entryToAdd);
				temp = temp.getAbove();
			}
			return valueRepacled;
		} else {
			// Use q to represent the new entry as we move to the level "above" after
			// inserting into the bottom-most list
			SkipListNode<K, V> q = null;
			// Keep track of the current level we are at
			int currentLevel = -1;
			do {
				currentLevel++;
				// Check if we need to add a new level to the top of the skip list
				if (currentLevel >= height) {
					// Increase the height of the skip list
					height++;
					// Create a pointer to the current "tail" of the topmost list
					SkipListNode<K, V> tail = start.next;
					// Insert a new sentinel start node above
					start = insertAfterAbove(null, start, null);
					// Insert a new sentinel tail node above
					insertAfterAbove(start, tail, null);
				}
				MapEntry<K, V> entryToAdd = new MapEntry<K, V>(key, value);
				// Insert the new entry into current level of the list
				q = insertAfterAbove(temp, q, entryToAdd);
				// Backtrack to the entry immediately before the insertion location in the level
				// "above"
				while (temp != null && temp.getAbove() != null) {
					temp = temp.getPrevious();
				}
				if (temp != null) {
					temp = temp.getAbove();
				}
			} while (coinToss.nextBoolean());
			size++;
			return null;

		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public V remove(K key) {
		SkipListNode<K, V> temp = lookUp(key);
		// If value is not in list return null
		if (temp == null || temp.getEntry() == null || compare(key, temp.getEntry().getKey()) != 0) {
			return null;
		}
		// Otherwise remove value from all levels of list
		else {
			// Save value that is going to be removed
			V valueRemoved = temp.getEntry().getValue();
			// Start removal process from bottom up
			while (temp != null) {
				// Skip temp in level effectively removing it
				if (temp.getPrevious() != null) {
					temp.getPrevious().setNext(temp.getNext());
				}
				if (temp.getNext() != null) {
					temp.getNext().setPrevious(temp.getPrevious());
				}
				// Go up a level
				temp = temp.getAbove();
			}
			// Adjust the top level, just in case temp was the only value at that level,
			// effectively getting rid of empty top levels
			while (start.getNext() == null && start.getBelow() != null) {
				start = start.getBelow();
				start.setAbove(null);
			}
			size--;
			return valueRemoved;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		EntryCollection set = new EntryCollection();
		SkipListNode<K, V> current = start;
		while (current.below != null) {
			current = current.below;
		}
		current = current.next;
		while (!isSentinel(current)) {
			set.add(current.getEntry());
			current = current.next;
		}
		return set;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("SkipListMap[");
		SkipListNode<K, V> cursor = start;
		while (cursor.below != null) {
			cursor = cursor.below;
		}
		cursor = cursor.next;
		while (cursor != null && !isSentinel(cursor) && cursor.getEntry().getKey() != null) {
			sb.append(cursor.getEntry().getKey());
			if (!isSentinel(cursor.next)) {
				sb.append(", ");
			}
			cursor = cursor.next;
		}
		sb.append("]");

		return sb.toString();
	}

	/**
	 * Helpful method for debugging, since it displays much more information about
	 * map.
	 * 
	 * @return detailed string representation of SkipListMap
	 */
	public String toFullString() {
		StringBuilder sb = new StringBuilder("SkipListMap[\n");
		SkipListNode<K, V> cursor = start;
		SkipListNode<K, V> firstInList = start;
		while (cursor != null) {
			firstInList = cursor;
			sb.append("-INF -> ");
			cursor = cursor.next;
			while (cursor != null && !isSentinel(cursor)) {
				sb.append(cursor.getEntry().getKey() + " -> ");
				cursor = cursor.next;
			}
			sb.append("+INF\n");
			cursor = firstInList.below;
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Stores MapEntries in the form of skip list nodes which have nodes surrounding
	 * them in all directions.
	 * 
	 * @author Dr.King
	 * @author Garrett Presley
	 *
	 * @param <K> The type of key to use
	 * @param <V> The type of values to store
	 */
	private static class SkipListNode<K, V> {
		/** The entry to occupy the node */
		private Entry<K, V> entry;
		/** The node above */
		private SkipListNode<K, V> above;
		/** The node below */
		private SkipListNode<K, V> below;
		/** The node before */
		private SkipListNode<K, V> prev;
		/** The node after */
		private SkipListNode<K, V> next;

		/**
		 * Constructs a SkipListNode using the given entry as the data to store.
		 * 
		 * @param entry the entry to store
		 */
		public SkipListNode(Entry<K, V> entry) {
			setEntry(entry);
			setAbove(null);
			setBelow(null);
			setPrevious(null);
			setNext(null);
		}

		/**
		 * Returns the above node.
		 * 
		 * @return the node above
		 */
		public SkipListNode<K, V> getAbove() {
			return above;
		}

		/**
		 * Returns the below node.
		 * 
		 * @return the node below
		 */
		public SkipListNode<K, V> getBelow() {
			return below;
		}

		/**
		 * Returns the entry that is stored in the node.
		 * 
		 * @return the entry
		 */
		public Entry<K, V> getEntry() {
			return entry;
		}

		/**
		 * Returns the next node.
		 * 
		 * @return the next node
		 */
		public SkipListNode<K, V> getNext() {
			return next;
		}

		/**
		 * Returns the previous node.
		 * 
		 * @return the previous node
		 */
		public SkipListNode<K, V> getPrevious() {
			return prev;
		}

		/**
		 * Sets the node's entry.
		 * 
		 * @param entry the entry to store in node
		 */
		public void setEntry(Entry<K, V> entry) {
			this.entry = entry;
		}

		/**
		 * Sets the node above.
		 * 
		 * @param up the node above to set
		 */
		public void setAbove(SkipListNode<K, V> up) {
			this.above = up;
		}

		/**
		 * Sets the node below.
		 * 
		 * @param down the node below to set
		 */
		public void setBelow(SkipListNode<K, V> down) {
			this.below = down;
		}

		/**
		 * Sets the next node.
		 * 
		 * @param next the next node to set
		 */
		public void setNext(SkipListNode<K, V> next) {
			this.next = next;
		}

		/**
		 * Sets the previous node.
		 * 
		 * @param prev the previous node sto set
		 */
		public void setPrevious(SkipListNode<K, V> prev) {
			this.prev = prev;
		}
	}
}
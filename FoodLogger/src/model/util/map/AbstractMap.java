package model.util.map;

import java.util.Iterator;

import model.util.list.LinkedList;
import model.util.list.List;

/**
 * A skeletal implementation of the Map abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the map
 * abstract data type.
 * 
 * @author Garrett Presley
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {
	

	/**
	 * MapEntry implements the Entry abstract data type.
	 * 
	 * @author Dr. King
	 *
	 * @param <K> the type of key stored in the entry
	 * @param <V> the type of value stored in the entry
	 */
	protected static class MapEntry<K, V> implements Entry<K, V> {
		/** Represent the key of a map entry */
		private K key;
		/** Represents the value of a map entry */
		private V value;

		/**
		 * Constructs a MapEntry with a provided key and a provided value
		 * 
		 * @param key   the key to store in the entry
		 * @param value the value to store in the entry
		 */
		public MapEntry(K key, V value) {
			setKey(key);
			setValue(value);
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		/**
		 * Set the key of the entry to the provided key
		 * 
		 * @param key the key to store in the entry
		 */
		private void setKey(K key) {
			this.key = key;
		}

		/**
		 * Set the value of the entry to the provided value
		 * 
		 * @param value the value to store in the entry
		 */
		public void setValue(V value) {
			this.value = value;
		}

		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(Entry<K, V> o) {
			return ((Comparable<K>) this.key).compareTo(o.getKey());
		}
	}

	/**
	 * EntryCollection implements the {@link Iterable} interface to allow traversing
	 * through the entries stored in the map. EntryCollection does not allow removal
	 * operations
	 * 
	 * @author Dr. King
	 *
	 */
	protected class EntryCollection implements Iterable<Entry<K, V>> {
		/** Declaration of a list to be used when going through entries */
		private List<Entry<K, V>> list;

		/**
		 * Creates EntryCollection object and initializes list.
		 */
		public EntryCollection() {
			list = new LinkedList<Entry<K, V>>();
		}

		/**
		 * Adds an entry to the end of list
		 * 
		 * @param entry the entry to add
		 */

		public void add(Entry<K, V> entry) {
			list.addLast(entry);
		}

		/**
		 * Returns an iterator to traverse through the list
		 * 
		 * @return the iterator
		 */
		public Iterator<Entry<K, V>> iterator() {
			return new EntryCollectionIterator();
		}

		/**
		 * EntryCollectionIterator implements the {@link Iterator} interface to allow
		 * traversing through the entries stored in the map
		 * 
		 * @author Dr.King
		 *
		 */
		private class EntryCollectionIterator implements Iterator<Entry<K, V>> {
			/** Declaration of iterator to be used */
			private Iterator<Entry<K, V>> it;

			/**
			 * Constructs EntryCollectionIterator object, initializes the iterator.
			 */
			public EntryCollectionIterator() {
				it = list.iterator();
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public Entry<K, V> next() {
				return it.next();
			}

			/**
			 * Operation is not supported.
			 * 
			 * @throws UnsupportedOperationException since operation is not supported
			 */
			@Override
			public void remove() {
				throw new UnsupportedOperationException("The remove operation is not supported yet.");
			}
		}
	}

	/**
	 * KeyIterator implements the {@link Iterator} interface to allow traversing
	 * through the keys stored in the map
	 * 
	 * @author Dr. King
	 *
	 */
	protected class KeyIterator implements Iterator<K> {
		/** Declaration of iterator to be used */
		private Iterator<Entry<K, V>> it;

		/**
		 * Constructs KeyIterator object, initializes the iterator.
		 */
		public KeyIterator() {
			it = entrySet().iterator();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public K next() {
			return it.next().getKey();
		}

		/**
		 * Operation is not supported.
		 * 
		 * @throws UnsupportedOperationException since operation is not supported
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException("The remove operation is not supported yet.");
		}
	}

	/**
	 * ValueIterator implements the {@link Iterator} interface to allow traversing
	 * through the values stored in the map
	 * 
	 * @author Dr. King
	 *
	 */
	protected class ValueIterator implements Iterator<V> {
		/** Declaration of iterator to be used */
		private Iterator<Entry<K, V>> it;

		/**
		 * Constructs ValueIterator object, initializes the iterator.
		 */
		public ValueIterator() {
			it = entrySet().iterator();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public V next() {
			return it.next().getValue();
		}

		/**
		 * Operation is not supported.
		 * 
		 * @throws UnsupportedOperationException since operation is not supported
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException("The remove operation is not supported yet.");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns an iterator over the keys of entries.
	 * 
	 * @return iterator over the keys
	 */
	@Override
	public Iterator<K> iterator() {
		return new KeyIterator();
	}

	/**
	 * Returns an Iterable over the values of entries.
	 * 
	 * @return iterable over the values
	 */
	@Override
	public Iterable<V> values() {
		return new ValueIterable();
	}

	/**
	 * ValueIterable implements the {@link Iterable} interface to allow construction
	 * of a value iterator.
	 * 
	 * @author Garrett Presley
	 *
	 */
	private class ValueIterable implements Iterable<V> {
		/**
		 * Constructs an iterator over the values of the map
		 * 
		 * @return iterator over the values
		 */
		@Override
		public Iterator<V> iterator() {
			return new ValueIterator();
		}
	}

}
package model.util.map;

/**
 * A blueprint of map ADT for Food Log.
 * 
 * @author Garrett Presley
 *
 * @param <K> the data type for the keys to be stored in the map
 * @param <V> the data type for the values to be stored in the map
 */
public interface Map<K, V> extends Iterable<K> {
	

	/**
	 * Represents an object representation of an item stored in a map (an entry)
	 * which hold two types of data, a key, and a value. The key is used in storing
	 * the entry in the map and retrieving it, while the value is used for storing
	 * the information to be contained in the map.
	 * 
	 * @author Garrett Presley
	 *
	 * @param <K> the data type for the keys to be stored in the entry
	 * @param <V> the data type for the values to be stored in the entry
	 */
	interface Entry<K, V> extends Comparable<Entry<K, V>> {

		/**
		 * Returns the key of an entry.
		 * 
		 * @return the key
		 */
		K getKey();

		/**
		 * Returns the value of an entry.
		 * 
		 * @return the value
		 */
		V getValue();

		/**
		 * Sets the value of an entry returning the old value of the entry or null if
		 * there was no old entry.
		 * 
		 * @return the value of old entry or null if no old entry
		 */
		void setValue(V value);

	}

	/**
	 * Returns an iterable collection of the entries stored in the map.
	 * 
	 * @return iterable collection of entries
	 */
	Iterable<Entry<K, V>> entrySet();

	/**
	 * Returns an iterable collection of the values stores in the map.
	 * 
	 * @return iterable collection of values
	 */
	Iterable<V> values();

	/**
	 * Puts an entry with the data of key and value into map, returns null if there
	 * was no previous entry with the given key, or returns the previous value if
	 * there was already an existing entry with the provided key.
	 * 
	 * @param key   the key to store so as to find the entry in the map
	 * @param value the value to store in the entry
	 * @return null if no previous entry with key, or previous value if there was a
	 *         previous existing entry with the given key
	 */
	V put(K key, V value);

	/**
	 * Removes the entry with the given key.
	 * 
	 * @param key the key of the entry to remove
	 * @return the value of the entry that is removed, or null if no such entry with
	 *         the key exists.
	 */
	V remove(K key);

	/**
	 * Returns the value of the entry with the given key.
	 * 
	 * @param key the key of the entry to retrieve
	 * @return the value of the entry with the given key, or null if no such entry
	 *         with the key exists
	 */
	V get(K key);

	/**
	 * Returns the number of entries in the map.
	 * 
	 * @return number of entries in map
	 */
	int size();

	/**
	 * Returns if the map is empty, meaning there are no entries in it.
	 * 
	 * @return true it there are no entries in map
	 */
	boolean isEmpty();

}
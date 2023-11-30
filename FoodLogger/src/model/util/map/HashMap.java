package model.util.map;

/**
 * The LinearProbingHashMap is implemented as a hash table that uses linear
 * probing for collision resolution.
 * 
 * The hash map uses a multiply-and-divide compression strategy for calculating
 * hash functions. The hash map ensures expected O(1) performance of
 * {@link Map#put}, {@link Map#get}, and {@link Map#remove}.
 * 
 * The hash table resizes if the load factor exceeds 0.5.
 * 
 * The LinearProbingHashMap class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Garrett Presley
 *
 * @param <K> the type of keys stored in the hash map
 * @param <V> the type of values associated with keys in the hash map
 */
public class HashMap<K, V> extends AbstractHashMap<K, V> {
	

	private TableEntry<K, V>[] table;
	private int size;

	/**
	 * Constructs a new linear probing hash map that uses natural ordering of keys
	 * when performing comparisons. The created hash table uses the
	 * {@link AbstractHashMap#DEFAULT_CAPACITY}
	 */
	public HashMap() {
		this(AbstractHashMap.DEFAULT_CAPACITY, false);
	}

	/**
	 * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
	 * natural ordering of keys when performing comparisons. The created hash table
	 * uses the {@link AbstractHashMap#DEFAULT_CAPACITY}
	 * 
	 * @param isTesting if true, the hash table uses a predictable series of random
	 *                  values for deterministic and repeatable testing
	 */
	public HashMap(boolean isTesting) {
		this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
	}

	/**
	 * Constructs a new linear probing hash map that uses natural ordering of keys
	 * when performing comparisons. The created hash table is initialized to have
	 * the provided capacity.
	 * 
	 * @param capacity the initial capacity of the hash table
	 */
	public HashMap(int capacity) {
		this(capacity, false);
	}

	/**
	 * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
	 * natural ordering of keys when performing comparisons. The created hash table
	 * is initialized to have the provided capacity.
	 * 
	 * @param capacity  the initial capacity of the hash table
	 * @param isTesting if true, the hash table uses a predictable series of random
	 *                  values for deterministic and repeatable testing
	 */
	public HashMap(int capacity, boolean isTesting) {
		super(capacity, isTesting);
		size = 0;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		EntryCollection collection = new EntryCollection();
		for (int i = 0; i < table.length; i++) {
			if (!isAvailable(i)) {
				collection.add(table[i]);
			}
		}
		return collection;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void createTable(int capacity) {
		table = (TableEntry<K, V>[]) new TableEntry[capacity];
		size = 0;
	}

	private boolean isAvailable(int index) {
		return table[index] == null || table[index].isDeleted();
	}

	@Override
	public V bucketGet(int hash, K key) {
		// Find the index of where the bucket is located
		int index = findBucket(hash, key);
		// No such bucket was found
		if (index < 0)
			return null;
		// Bucket was found and the value is returned
		return table[index].getValue();
	}

	@Override
	public V bucketPut(int hash, K key, V value) {
		// Find an index to place bucket
		int i = findBucket(hash, key);
		// If an index is found with same key place value there,
		// and return the the old value
		if (i >= 0) {
			V valueReplaced = table[i].getValue();
			table[i].setValue(value);
			return valueReplaced;
		}
		// If no index is found place a new bucket of where it should go
		table[-(i + 1)] = new TableEntry<K, V>(key, value);
		// Increment size
		size++;
		// Return null since no bucket was there
		return null;
	}

	private int findBucket(int index, K key) {
		int available = -1;
		int i = index;
		do {
			if (isAvailable(i)) {
				if (available == -1)
					available = i;
				if (table[i] == null)
					break;
			} else if (table[i].getKey().equals(key)) {
				return i;
			}
			i = (i + 1) % table.length;
		} while (i != index);
		return -(available + 1);
	}

	@Override
	public V bucketRemove(int hash, K key) {
		// Remember to set the table bucket as DELETED using setDeleted(true)
		int i = findBucket(hash, key);
		// Nothing to remove
		if (i < 0)
			return null;
		// 'Remove' the value, just set it isDeleted to true
		V valueRemoved = table[i].getValue();
		// Set deleted to true
		table[i].setDeleted(true);
		// Decrement size
		size--;
		// Return the value that was 'removed'
		return valueRemoved;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	protected int capacity() {
		return table.length;
	}

	private static class TableEntry<K, V> extends MapEntry<K, V> {

		private boolean isDeleted;

		public TableEntry(K key, V value) {
			super(key, value);
			setDeleted(false);
		}

		public boolean isDeleted() {
			return isDeleted;
		}

		public void setDeleted(boolean deleted) {
			isDeleted = deleted;
		}
	}
}
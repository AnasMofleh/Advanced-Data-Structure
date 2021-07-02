package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	Entry<K, V>[] table;
	public int capacity = 16;
	public double loadFactor = 0.75;
	public int size = 0;

	public SimpleHashMap() {
		table = (Entry<K, V>[]) new Entry[16];
		size = 0;
	}

	public SimpleHashMap(int capacity) {
		this.capacity = capacity;
		table = (Entry<K, V>[]) new Entry[capacity];
		size = 0;
	}

	private static class Entry<K, V> implements Map.Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
			this.next = null;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			V oldVal = this.value;
			this.value = value;
			return oldVal;
		}

		@Override
		public String toString() {
			return key + " = " + value;
		}
	}

	public String Show() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < table.length; i++) {
			while (table[i] != null) {
				s.append(i + "   " + table[i].toString() + "\n");
				table[i] = table[i].next;

			}
		}
		return s.toString();
	}

	@Override
	public V get(Object object) {
		K key = (K) object;
		Entry<K, V> e = find(index(key), key);
		if (e == null)
			return null;
		else {
			return e.value;
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V put(K key, V value) {
		int index = index(key);// 1 calculate index
		Entry<K, V> e = find(index, key); // 2 try if we can find the key at
											// index whitch is länkedlist
		if (e != null) {
			return e.setValue(value);
		} // har inte hittat i hela tablen
		e = new Entry<K, V>(key, value);
		if (table[index] != null) {
			e.next = table[index];
		}
		table[index] = e;
		size++;
		if ((double) size / table.length > 0.75) {
			rehash();
		}

		return null;
	}

	public void rehash() {
		Entry<K, V>[] old = table;// kopiera tabellen
		capacity *= 2;
		table = (Entry<K, V>[]) new Entry[capacity];// skapa ny tabell
		size = 0; // size på den nya tabellen är 0
		for (int i = 0; i < old.length; i++) {
			Entry<K, V> e = old[i];
			while (e != null) {
				put(e.getKey(), e.getValue());
				e = e.next;
			}
		}
	}

	@Override
	public V remove(Object n) {
		K key = (K) n;
		int index = index(key);
		Entry<K, V> e = find(index, key);

		if (table[index] == null) {
			return null;
		} else if (e == null) {
			return null;
		} else {
			if (e.key.equals(table[index].key)) {
				Entry<K, V> temp = e;
				table[index] = e.next;
				size--;
				return e.getValue();
			} else {
				Entry<K, V> pos = table[index];
				while (e != pos.next) {
					pos = pos.next;
				}

				Entry<K, V> temp = e;
				pos.next = e.next;
				size--;
				return e.getValue();
			}
		}

	}

	@Override
	public int size() {
		return size;
	}

	private int index(K key) {
		return Math.abs(key.hashCode() % capacity);
	}

	private Entry<K, V> find(int index, K key) {
		Entry<K, V> e = table[index];
		if (e == null) {
			return e;
		} else {
			while (e != null) {
				if (e.getKey().equals(key)) {
					return e;
				} else {
					e = e.next;
				}
			}

		}
		return null;
	}

	public static void main(String[] args) {
		// SimpleHashMap<String, Integer> shm = new SimpleHashMap<>(4);
		SimpleHashMap<Integer, Integer> shm = new SimpleHashMap<>();
		/*
		 * shm.put("Jamil", 123); shm.put("anas", 123); shm.put("bruno", 123);
		 * shm.put("mahmoud", 123); shm.put("walid", 1); shm.put("tobaz", 23);
		 */

		shm.put(3, 3);
		shm.put(3, 10);
		shm.put(16 + 3, 16 + 3);
		System.out.println(shm.remove(3).toString());

		System.out.print(shm.Show());
		// System.out.println("-----");
		// shm.remove("Jamil");
		// shm.rehash();
		System.out.print(shm.Show());
	}

}

package de.hs_mannheim_ib.tpe.chr_luk.uebung_03;



public interface AssociativeArray<K,V> {
	
	

	void clear();
	boolean containsValue(V v);
	boolean containsKey(K k);
	V get(K k);
	boolean isEmpty();
	void put(K k , V v);
	void putAll(AssociativeArray b);
	V remove(K k);
	int size();
	void update(K k, V v);
	void forEach(BiConsumer<K,V> biConsumer);	
	AssociativeArray map(AssociativeArray a);
	void extractAll(AssociativeArray b);
	
}

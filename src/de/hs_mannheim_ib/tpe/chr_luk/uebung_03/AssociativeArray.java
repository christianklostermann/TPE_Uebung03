package de.hs_mannheim_ib.tpe.chr_luk.uebung_03;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;



public interface AssociativeArray<K,V> {
	
	

	void clear();
	boolean containsValue(V v);
	boolean containsKey(K k);
	V get(K k);
	boolean isEmpty();
	void put(K k , V v);
	void putAll(AssociativeArray<K, V>associativeArray);
	V remove(K k);
	int size();
	void update(K k, V v);
	void forEach(BiConsumer<K,V> biConsumer);	
	AssociativeArray<K, V> map(BiFunction<K,V,K>biFunction);
	void extractAll(AssociativeArray<K, V>associativeArray);
	AssociativeArray<K, V> map(AssociativeArray<K, V> b);
	
}

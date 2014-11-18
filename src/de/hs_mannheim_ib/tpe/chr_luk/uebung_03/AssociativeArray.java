package de.hs_mannheim_ib.tpe.chr_luk.uebung_03;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;


<<<<<<< HEAD
=======

>>>>>>> origin/Lukas
public interface AssociativeArray<K,V> {
	
	

	void clear();
	boolean containsValue(V v);
	boolean containsKey(K k);
	V get(K k);
	boolean isEmpty();
	void put(K k , V v);
<<<<<<< HEAD
	void putAll(AssociativeArray b);
=======
	void putAll(AssociativeArray<K, V>associativeArray);
>>>>>>> origin/Lukas
	V remove(K k);
	int size();
	void update(K k, V v);
	void forEach(BiConsumer<K,V> biConsumer);	
<<<<<<< HEAD
	AssociativeArray map(AssociativeArray a);
	void extractAll(AssociativeArray b);
=======
	AssociativeArray<K, V> map(BiFunction<K,V,K>biFunction);
	void extractAll(AssociativeArray<K, V>associativeArray);
>>>>>>> origin/Lukas
	
}

package de.hs_mannheim_ib.tpe.chr_luk.uebung_03;

import java.util.HashMap;
import java.util.Map;

public interface AssociativeArray<K,V,B> {
	
	

	void clear();
	boolean containsValue(V v);
	boolean containsKey(K k);
	V get(K k);
	boolean isEmpty();
	void put(K k , V v);
	void putAll(Map m);
	V remove(K k);
	long size();
	void update(K k, V v);
	void forEach(B b);
	void extractAll(Map m);
	Map map(B b);
	
}

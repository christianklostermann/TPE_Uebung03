package de.hs_mannheim_ib.tpe.chr_luk.uebung_03;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;


public interface AssociativeArray2<K extends Comparable<K>, V> {
	public void clear();
	public boolean containsValue(V value);
	public boolean containskey(K key);
	public V get(K key);
	public boolean isEmpty();
	public void put(K key, V value);
	public void putAll(AssociativeArray2<K, V> array);
	public V remove(K key);
	public int size();
	public void update(K key, V value);
	public void forEach(BiConsumer<K, V> consumer);
	public void extractAll(AssociativeArray2<K, V> array);
	public AssociativeArray2<K, V> map(BiFunction<K, V, AssociativeArray2<K, V>> function);
}

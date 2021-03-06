package de.hs_mannheim_ib.tpe.chr_luk.uebung_03;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * represents a interface for an associative array
 * 
 * @author
 *
 * @param is
 *            key
 * @param is
 *            value
 */

public interface AssociativeArray<K, V> {

	/**
	 * delete all entries the associative array
	 */
	void clear();

	/**
	 * Proves if given value is part of the associative array
	 * 
	 * @param value
	 *            of entry
	 */
	boolean containsValue(V v);

	/**
	 * Proves if given key is part of the associative array
	 * 
	 * @param k
	 *            is key
	 * @return true if key is part of associative array
	 */
	boolean containsKey(K k);

	/**
	 * get the given key if key is part of the array
	 * 
	 * @param k
	 *            is key
	 * @return the value wich connected with the key
	 */
	V get(K k);

	/**
	 * Proves if the associative array has entries
	 * 
	 * @return true if entries > 0
	 */

	boolean isEmpty();

	/**
	 * insert a new pair of a key-value
	 * 
	 * @param k
	 *            is key
	 * @param v
	 *            is value
	 * @return
	 * @return
	 */
	void put(K k, V v);

	/**
	 * insert a other associative array into the associative array
	 * 
	 * @param associativeArray
	 *            the one you want to insert
	 */

	void putAll(Tree<? extends K, ? extends V> b);

	/**
	 * removes the pair of key-values by given key
	 * 
	 * @param k
	 *            is key
	 * @return the value
	 */
	V remove(K k);

	/**
	 * Counts the number of key-value pairs
	 * 
	 * @return the number of entries as integer
	 */

	int size();

	/**
	 * override an existing key-value pair
	 * 
	 * @param k
	 *            is key
	 * @param v
	 *            is value
	 */
	void update(K k, V v);

	/**
	 * Each value of an key-value pair gets manipulated by the given lambda
	 * expression.
	 * 
	 * @param biConsumer
	 *            the lambda expression which define the manipulation
	 */

	void forEach(BiConsumer<K, V> biConsumer);

	/**
	 * 
	 * @param associativeArray
	 */

	void extractAll(AssociativeArray<K, V> associativeArray);

	/**
	 * gets a biFunction (lambda expression) which consumes
	 * a value and key, manipulates the value and put 
	 * key-value pairs into a new tree of the same type 
	 * as the class itself
	 * 
	 * 
	 * @param biFunction a lambda expression which manipulates only the value
	 * @return a new sub-class of AssociativeArray, like tree or dictionary
	 */

	<T> T map(BiFunction<K, V, V> biFunction);

}

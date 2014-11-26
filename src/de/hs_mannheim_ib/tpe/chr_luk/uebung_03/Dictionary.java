package de.hs_mannheim_ib.tpe.chr_luk.uebung_03;

import java.util.ArrayList;
import java.util.function.BiConsumer;

/**
 * its a sub-class of {@link Tree}.
 * 
 * change is that the K (key) and V (value) are
 * limited to type String
 * 
 * @author 
 *
 * @param <K> as String
 * @param <V> as String
 */

public class Dictionary<K extends String, V extends String> extends Tree<K, V> {

	/**
	 * saves all keys of the {@link AssociativeArray} in an array
	 * uses a arrayList to save each key by using a lambda expression
	 * to add them internally to the array list
	 * 
	 * @return array with all keys of the array
	 * 
	 */

	public String[] keys() {

		ArrayList<String> list = new ArrayList<>();
		
		BiConsumer<K,V> bicon = (x, y) -> {			
			list.add(x);
		};
		this.forEach(bicon);
		String[] array = new String[list.size()];
		return list.toArray(array);
	}
	/**
	 * saves all keys of the {@link AssociativeArray} in an array
	 * uses a arrayList to save each values by using a lambda expression
	 * to add them internally to the array list
	 * 
	 * @return array with all values of the array
	 * 
	 */

	public String[] values() {
		ArrayList<String> list = new ArrayList<>();
		
		BiConsumer<K,V> bicon = (x, y) -> {			
			list.add(y);
		};
		this.forEach(bicon);
		String[] array = new String[list.size()];
		return list.toArray(array);
	}

}

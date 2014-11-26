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

	

	public String[] keys() {

		ArrayList<String> list = new ArrayList<>();
		
		BiConsumer<K,V> bicon = (x, y) -> {			
			list.add(x);
		};
		this.forEach(bicon);
		String[] array = new String[list.size()];
		return list.toArray(array);
	}


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

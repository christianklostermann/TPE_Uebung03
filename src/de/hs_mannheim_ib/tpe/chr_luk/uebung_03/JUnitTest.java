package de.hs_mannheim_ib.tpe.chr_luk.uebung_03;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import org.junit.Test;


public class JUnitTest<K, V> {
	
	
	


	@Test
	public void test() {
		AssociativeArray<Number, String> tree = new Tree<>();
		AssociativeArray<Number, String> tree2 = new Tree<>();

		String o = new String();
		o = "1";
		String o2 = new String();
		o2 = "2";
		String o3 = new String();
		o3 = "3";
		String o4 = new String();
		o4 = "4";

		tree.put(o.hashCode(), o);
		tree.put(o2.hashCode(), o2);
		tree2.put(o3.hashCode(), o3);
		tree2.put(o4.hashCode(), o4);

		System.out.println(tree.containsKey(o.hashCode()));
		System.out.println(tree.containsKey(564654));

		System.out.println(tree.size());
		System.out.println(tree.containsValue(o4));
		System.out.println(tree.toString());

		tree.extractAll(tree2);
		
		

		tree.putAll(tree2);
		System.out.println(tree.toString());
		System.out.println(tree2.toString());
		
		
		BiConsumer biConsumer = (x,y)-> {};
		
		tree.forEach(biConsumer);
		

	
		BiFunction biFunction = (x,y) -> y+"AAA";
		
		AssociativeArray<Number, String> tree3 = tree2.map(biFunction);
		System.out.println(tree3.isEmpty());
		
		System.out.println("After BiFunktion\n"+tree3.toString());
	    System.out.println(tree3.containsKey(50));

	}

}

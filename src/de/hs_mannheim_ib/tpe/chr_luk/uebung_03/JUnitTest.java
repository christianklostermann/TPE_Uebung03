package de.hs_mannheim_ib.tpe.chr_luk.uebung_03;

import org.junit.Test;


public class JUnitTest<K, V> {

	@Test
	public void test() {
		Tree<Object, Object> tree = new Tree<>();
		Tree<Object, Object> tree2 = new Tree<>();

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
		System.out.println(tree.containsKey("HALLO"));

		System.out.println(tree.size());
		System.out.println(tree.containsValue(o4));
		tree.printTree();

		tree.extractAll(tree2);
		
		

		tree.putAll(tree2);
		tree.printTree();

		tree2.printTree();
	}

}

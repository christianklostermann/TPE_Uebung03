package de.hs_mannheim_ib.tpe.chr_luk.uebung_03;

import org.junit.Test;
import org.junit.Assert.*;

public class JUnitTest {

	@Test
	public void test() {
		Tree tree = new Tree();
		Object o = new Object();
		Object o2 = new Object();
		Object o3 = new Object();
		Object o4 = new Object();
		
		tree.insert(o.hashCode(), o);
		tree.insert(o2.hashCode(), o2);
		tree.insert(o3.hashCode(), o3);
		tree.insert(o4.hashCode(), o4);
		
	
		System.out.println(tree.containsKey(o.hashCode()));
		System.out.println(tree.containsKey("HALLO"));
		
		

		tree.printTree();
	}

}

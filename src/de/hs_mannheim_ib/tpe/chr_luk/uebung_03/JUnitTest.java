package de.hs_mannheim_ib.tpe.chr_luk.uebung_03;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import org.junit.Test;

public class JUnitTest<K, V> {

	@Test
	public void testAssociativeArray() {
		AssociativeArray<Number, Object> tree = new Tree<>();
		Object object1 = new Object();
		BiConsumer<Number, Object> bicon = (x, y) -> {
			System.out.println(x + " " + y);
		};
		BiFunction<Number, Object, Object> bifunk = (x, y) -> {
			y = new String();
			return y;
		};

		tree.put(object1.hashCode(), object1);
		assertEquals(tree.size(), 1);
		assertEquals(tree.containsKey(object1.hashCode()), true);
		assertEquals(tree.containsValue(object1), true);

		tree.forEach(bicon);
		assertEquals(tree.get(object1.hashCode()), object1);

		tree.clear();
		assertEquals(tree.size(), 0);
		assertEquals(tree.isEmpty(), true);
		tree.put(object1.hashCode(), object1);
		assertEquals(tree.equals(tree), true);
		assertEquals(tree.isEmpty(), false);
		assertEquals(tree.map(bifunk).equals(tree), false);

		tree = tree.map(bifunk);
		assertEquals(tree.containsKey(object1.hashCode()), true);
		assertEquals(tree.containsValue(object1), false);
		assertEquals(tree.get(object1.hashCode()), new String());

		tree.update(object1.hashCode(), object1);
		assertEquals(tree.containsValue(object1), true);
	

	}
	@Test
	public void testDictionary(){
		
		
		Dictionary<String, String> dic= new Dictionary<>();
		String word = new String();
		word = "aboretum";
		
		dic.put("Baumschule", word);
		dic.put("Baumreihe", word);
		
		for(int i = 0;i < 2;i++){
		
			dic.put(""+ SessionIdentifierGenerator.nextSessionId(),"test"+i);
		}
		
		assertEquals(dic.get("Baumschule"),"aboretum");
		
		String[] keys   = dic.keys();
		String[] values = dic.values();
		
		assertEquals(keys.length,4);
		assertEquals(values.length,4);
		
		dic.containsKey("0.001");
		
	
		System.out.println(dic);
		dic.remove("Baumreihe");
		dic.remove("Baum");
		System.out.println(dic);
		
		

		
		
		
	}
	
	public final static class SessionIdentifierGenerator {
		  private static SecureRandom random = new SecureRandom();

		  public static String nextSessionId() {
		    return new BigInteger(130, random).toString(32);
		  }
		}
	
	
	@Test
	public void testStringAssoArray(){
		StringAssociativeArray<Integer, String> stringAssA = new StringAssociativeArray<>();
		String word = new String();
		word = "nihil";
		
		stringAssA.put(word.hashCode(), word);
		
		assertEquals(stringAssA.get(word.hashCode()), "nihil");
		
	}
}

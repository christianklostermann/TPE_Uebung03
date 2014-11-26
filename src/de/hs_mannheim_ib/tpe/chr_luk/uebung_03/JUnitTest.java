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

		AssociativeArray<?, ?> tree3 = tree.map(bifunk);
		
		assertEquals(tree.containsKey(object1.hashCode()), true);
		assertEquals(tree.containsValue(object1), true);
	

		tree.update(object1.hashCode(), object1);
		assertEquals(tree.containsValue(object1), true);

	}

	@Test
	public void testDictionary() {

		Dictionary<String, String> dic = new Dictionary<>();
		String word = new String();
		String word2 = new String();
		word = "aboretum";
		word2 = "invictus";

		for (int i = 0; i < 20; i++) {

			dic.put("" + SessionIdentifierGenerator.nextSessionId(), "test" + i);
		}
		dic.put("Baumschule", word);
		dic.put("unbesiegbar", word2);

		assertEquals(dic.get("Baumschule"), "aboretum");

		String[] keys   = dic.keys();
		String[] values = dic.values();

		assertEquals(keys.length, 22);
	    assertEquals(values.length, 22);
	    assertEquals(dic.containsKey("0.001"), false);
		

		System.out.println(dic);
		

		BiFunction<String, String, String> bifunk = (x, y) -> {
			y = new String();
			return y;
		};

		assertEquals(dic.remove("Baumschule"), word);
		assertEquals(dic.remove("unbesiegbar"), word2);
		assertEquals(dic.get("Baumschule"), null);
		assertEquals(dic.get("unbesiegbar"), null);
		assertEquals(dic.map(bifunk).getClass(), Dictionary.class);
		
		 dic.putAll(new Dictionary<>());
		
		 Dictionary<String, String> tmp = (Dictionary<String, String>) dic.map(bifunk);
		

		System.out.println(dic);

	}

	@Test
	public void testStringAssoArray() {
		StringAssociativeArray<Integer, String> stringAssA = new StringAssociativeArray<>();
		String word = new String();
		word = "nihil";

		stringAssA.put(word.hashCode(), word);

		assertEquals(stringAssA.get(word.hashCode()), "nihil");

	}

	public final static class SessionIdentifierGenerator {
		private static SecureRandom random = new SecureRandom();

		public static String nextSessionId() {
			return new BigInteger(130, random).toString(24);
		}
	}
}

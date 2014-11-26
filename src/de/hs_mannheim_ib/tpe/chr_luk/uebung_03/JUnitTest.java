package de.hs_mannheim_ib.tpe.chr_luk.uebung_03;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import org.junit.Test;

public class JUnitTest<K, V> {

	// / just for the test-cases
	BiConsumer<Number, Object> bicon = (x, y) -> {
		System.out.println(x + " " + y);
	};

	BiFunction<Number, Object, Object> biFunk = (x, y) -> {
		y = new String();
		return y;
	};
	BiFunction<Integer, String, String> biFunkInt = (x, y) -> {
		y = new String();
		return y;
	};

	BiFunction<String, String, String> biFunkString = (x, y) -> {
		y = new String();
		return y;
	};

	public final static class SecureRand {
		private static SecureRandom random = new SecureRandom();

		public static String nextRand(int c) {
			return new BigInteger(130, random).toString(c);
		}
	}

	@Test
	public void testAssociativeArray() {
		AssociativeArray<Number, Object> tree = new Tree<>();
		Object object1 = new Object();

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
		assertEquals(tree.map(biFunk).equals(tree), false);

		AssociativeArray<?, ?> tree3 = tree.map(biFunk);

		assertEquals(tree3.size(), tree3.size());
		assertEquals(tree3.getClass(), tree.getClass());

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

			dic.put("" + SecureRand.nextRand(24), "VALUE" + i);
		}

		dic.put("Baumschule", word);
		dic.put("unbesiegbar", word2);

		assertEquals(dic.get("Baumschule"), "aboretum");

		String[] keys = dic.keys();
		String[] values = dic.values();

		assertEquals(keys.length, dic.size());
		assertEquals(values.length, dic.size());

		assertEquals(values[0].toString(), dic.get(keys[0]));

		assertEquals(keys.length, 22);
		assertEquals(values.length, 22);
		assertEquals(dic.containsKey("0.001"), false);

		assertEquals(dic.remove("Baumschule"), word);
		assertEquals(dic.remove("unbesiegbar"), word2);
		assertEquals(dic.get("Baumschule"), null);
		assertEquals(dic.get("unbesiegbar"), null);
		assertEquals(dic.map(biFunkString).getClass(), Dictionary.class);

		int size = dic.size();
		dic.putAll(dic);
		assertEquals(dic.size(), size);

		Dictionary<String, String> mapDic = dic.map(biFunkString);

		assertEquals(mapDic.getClass(), dic.getClass());
		assertEquals(mapDic.size(), dic.size());

		System.out.println(dic);
		
	

	}

	@Test
	public void testStringAssociativeArray() {
		StringAssociativeArray<Integer, String> stringAssA = new StringAssociativeArray<>();
		String word = new String();
		word = "nihil";
		String word2 = "quasar";

		stringAssA.put(word.hashCode(), word);

		assertEquals(stringAssA.get(word.hashCode()), "nihil");

		for (int i = 0; i < 20; i++) {

			stringAssA.put((int) Math.random() * 1000, "VALUE" + i);
		}

		stringAssA.put(123456789, word);
		stringAssA.put(word2.hashCode(), word2);

		assertEquals(stringAssA.get(word.hashCode()), word);
		assertEquals(stringAssA.containsKey(-1), false);
		assertEquals(stringAssA.remove(-1), null);
		assertEquals(stringAssA.remove(word2.hashCode()), word2);
		assertEquals(stringAssA.get(word2.hashCode()), null);
		assertEquals(stringAssA.map(biFunkInt).getClass(),
		        StringAssociativeArray.class);

		int size = stringAssA.size();
		stringAssA.putAll(stringAssA);
		assertEquals(stringAssA.size(), size);

		StringAssociativeArray<Integer, String> mapIn = stringAssA
		        .map(biFunkInt);

		assertEquals(mapIn.getClass(), stringAssA.getClass());
		assertEquals(mapIn.size(), stringAssA.size());

		System.out.println(stringAssA);

	}

	@Test
	public void testIntegerAssociativeArray() {
		IntegerAssociaticeArray<Integer, Integer> integerAssA = new IntegerAssociaticeArray<>();
		

		Integer word = new Integer(667384);
		

		

	}

}

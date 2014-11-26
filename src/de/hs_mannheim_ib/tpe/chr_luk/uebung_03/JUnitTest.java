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
	BiConsumer<String, String> biconString = (x, y) -> {
		x = "ALLKEYSAREQUAL";
	};

	BiFunction<Number, Object, Object> biFunk = (x, y) -> {
		y = new String();
		return y;
	};
	BiFunction<Integer, String, String> biFunkInt = (x, y) -> {
		y = new String();
		return y;
	};
	BiFunction<Integer, Integer, Integer> biFunkIntInt = (x, y) -> {
		y = new Integer(557548);
		return y;
	};

	BiFunction<String, String, String> biFunkString = (x, y) -> {
		y = new String();
		return y;
	};
	BiFunction<String, Object, Object> biFunkObject = (x, y) -> {
		y = new String();
		return y;
	};

	public final static class SecureRand {
		private static SecureRandom random = new SecureRandom();

		public static String nextRand() {
			return new BigInteger(130, random).toString();
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

			dic.put("" + SecureRand.nextRand(), "VALUE" + i);
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
		dic.forEach(biconString);
		

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
		StringAssociativeArray<String, Object > stringAssA = new StringAssociativeArray<>();
		String word = new String();
		word = "nihil";
		String word2 = "quasar";

		stringAssA.put(word, word);

		assertEquals(stringAssA.get(word), "nihil");

		for (int i = 0; i < 20; i++) {

			stringAssA.put(""+i, "VALUE" + i);
		}

		stringAssA.put("exNihiloNihilFit", word);
		stringAssA.put(word2, word2);

		assertEquals(stringAssA.get(word), word);
		assertEquals(stringAssA.containsKey("A"), false);
		assertEquals(stringAssA.remove("A"), null);
		assertEquals(stringAssA.remove(word2), word2);
		assertEquals(stringAssA.get(word2), null);
		assertEquals(stringAssA.map(biFunkObject).getClass(),
		        StringAssociativeArray.class);

		int size = stringAssA.size();
		stringAssA.putAll(stringAssA);
		assertEquals(stringAssA.size(), size);

		StringAssociativeArray<String, String> mapIn = stringAssA
		        .map(biFunkObject);

		assertEquals(mapIn.getClass(), stringAssA.getClass());
		assertEquals(mapIn.size(), stringAssA.size());

		System.out.println(stringAssA);

	}

	@Test
	public void testIntegerAssociativeArray() {
		IntegerAssociaticeArray<Integer, Integer> integerAssA = new IntegerAssociaticeArray<>();
		

		Integer word = new Integer(667384);
		
		 int word2 = 4568778;

		 integerAssA.put(word.hashCode(), word);

		assertEquals(integerAssA.get(word.hashCode()),new Integer(667384));

		for (int i = 0; i < 20; i++) {

			integerAssA.put((int) Math.random() * 1000,(int) Math.random() * 1000);
		}

		integerAssA.put(123456789, word);
		integerAssA.put(word2, word2);

		assertEquals(integerAssA.get(word.hashCode()), word);
		assertEquals(integerAssA.containsKey(-1), false);
		assertEquals(integerAssA.remove(-1), null);
		assertEquals(integerAssA.remove(word) ,word);
		assertEquals(integerAssA.get(word2), new Integer(4568778));
		assertEquals(integerAssA.map(biFunkIntInt).getClass(),
		        IntegerAssociaticeArray.class);

		int size =integerAssA.size();
		integerAssA.putAll(integerAssA);
		assertEquals(integerAssA.size(), size);

		IntegerAssociaticeArray<Integer, Integer> mapIn = integerAssA
		        .map(biFunkIntInt);

		assertEquals(mapIn.getClass(), integerAssA.getClass());
		assertEquals(mapIn.size(), integerAssA.size());

		System.out.println(integerAssA);

	}

}

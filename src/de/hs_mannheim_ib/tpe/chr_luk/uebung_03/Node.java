package de.hs_mannheim_ib.tpe.chr_luk.uebung_03;

public class Node<K, V> {

	public Node<K, V> left;
	public Node<K, V> right;
	final K k;
	final V v;

	public Node(K k,V v, Node<K, V> left, Node<K, V> right) {
		this.k = k;
		this.v = v;
		this.left = left;
		this.right = right;

	}


	/**
	 * @return the k
	 */
	public K getK() {
		return k;
	}

	/**
	 * @return the v
	 */
	public V getV() {
		return v;
	}

	public int compareTo(K k) {
		if (this.k instanceof String) {
			String tmp = (String) k;
	
			System.out.println("test");

		}
		if (k instanceof Number) {

		}
		if (k instanceof Boolean) {

		}
		if (k instanceof Character) {

		}
		return 0;
	}

}

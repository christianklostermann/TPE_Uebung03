package de.hs_mannheim_ib.tpe.chr_luk.uebung_03;

public class Node<K, V> {

	private Node<K, V> left;
	private Node<K, V> right;

	private K k;

	private V v;

	public Node(K k, V v, Node<K, V> left, Node<K, V> right) {
		this.k = k;
		this.v = v;
		this.setLeft(left);
		this.setRight(right);

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
		if (this.k instanceof String && k instanceof String) {
			String tmp1 = (String) k;
			String tmp2 = (String) this.k;

			return tmp1.compareToIgnoreCase(tmp2);
		} else if (this.k instanceof Number && k instanceof Number) {
			Number tmp1 = (Number) k;
			Number tmp2 = (Number) this.k;

			if (tmp1.equals(tmp2)) {
				return 0;
			} else if (tmp1.longValue() > tmp2.longValue()) {

				return -1;
			} else {
				return 1;
			}

		} else if (this.k instanceof Character && k instanceof Character) {
			Character tmp1 = (Character) k;
			Character tmp2 = (Character) this.k;

			return tmp1.compareTo(tmp2);
		}
		return 0;
	}

	public Node<K, V> getLeft() {
		return left;
	}

	public void setLeft(Node<K, V> left) {
		this.left = left;
	}

	public Node<K, V> getRight() {
		return right;
	}

	public void setRight(Node<K, V> right) {
		this.right = right;
	}

	/**
	 * @param k
	 *            the k to set
	 */
	public void setK(K k) {
		this.k = k;
	}

	/**
	 * @param v
	 *            the v to set
	 */
	public void setV(V v) {
		this.v = v;
	}
}

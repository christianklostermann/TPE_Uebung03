package de.hs_mannheim_ib.tpe.chr_luk.uebung_03;

import java.util.LinkedList;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

class Tree<K, V> implements AssociativeArray<K, V> {

	private Node<K, V> root;

	public Tree() {
		this.root = null;

	}

	public Tree(K k, V v) {
		this.root = new Node<K, V>(k, v, null, null, null);

	}

	@SuppressWarnings("hiding")
	class Node<K, V> {

		private Node<K, V> left;
		private Node<K, V> right;
		private Node<K, V> parent;

		private K k;

		private V v;

		public Node(K k, V v, Node<K, V> left, Node<K, V> right,
		        Node<K, V> parent) {
			this.k = k;
			this.v = v;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}

		/**
		 * @return the k
		 */
		public K getK() {
			return this.k;
		}

		/**
		 * @return the v
		 */
		public V getV() {
			return this.v;
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
			left.parent = this;
			this.left = left;
		}

		public Node<K, V> getRight() {
			return right;
		}

		public void setRight(Node<K, V> right) {
			right.parent = this;
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

		public Node<K, V> getParent() {
			return parent;
		}

		public void setParent(Node<K, V> parent) {
			this.parent = parent;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "{KEY=" + this.k + ", VALUE=" + this.v + "}";
		}
	}

	@Override
	public boolean isEmpty() {

		return (this.root == null);
	}

	@Override
	public void put(K k, V v) {
		Node<K, V> node = new Node<K, V>(k, v, null, null, null);
		this.put(node);
	}

	private void put(Node<K, V> node) {

		if (node != null) {
			Node<K, V> parent = null; // parent of child
			Node<K, V> child = this.root;
			if (child == null) { // empty tree
				this.root = node;

				return;
			}

			while (child != null) { // descend tree
				parent = child; // parent, then child
				if (child.compareTo(node.k) == 0) {
					return; // element in tree
				} else if (child.compareTo(node.k) < 0) {
					child = child.getLeft(); // left sub tree
				} else if (child.compareTo(node.k) > 0) {
					// i.compareTo (child.value) > 0
					child = child.getRight();// right sub tree
				}
			}

			// parent node found
			if (parent.compareTo(node.k) < 0) {
				// insert left from parent
				node.setParent(parent);
				parent.setLeft(node);
			} else {
				// insert right from parent
				node.setParent(parent);
				parent.setRight(node);
			}
		}
	}

	@Override
	public V remove(K k) {
		Node<K, V> node = searchKey(k);

		V value = null;
		if (node != null) {
			value = node.v;

			if (node.equals(this.root)) {
				Node<K, V> tmp = this.root.right;
				this.root = this.root.left;
				this.put(tmp);
			} else {
				Node<K, V> left = node.left;
				Node<K, V> right = node.right;

				if (node.parent.left != null && node.parent.left.equals(node)) {
					node.parent.left = null;
				} else {
					node.parent.right = null;
				}

				this.put(left);
				this.put(right);

			}

		}

		return value;
	}

	@Override
	public boolean containsKey(K k) {

		Node<K, V> tmp = root;

		if (tmp.getK().equals(k)) {

			while (tmp != null) {

				if (tmp.compareTo(k) == 0) {

					return true;
				}
				if (tmp.compareTo(k) < 0) {
					tmp = tmp.getRight();
				} else {
					tmp = tmp.getLeft();
				}

			}
		}
		return false;
	}

	private Node<K, V> searchValue(V v) {

		return this.BFS(v);
	}

	private Node<K, V> BFS(V v) {
		if (v != null) {
			LinkedList<Node<K, V>> level = new LinkedList<>();

			level.add(this.root);

			while (!level.isEmpty()) {
				Tree<K, V>.Node<K, V> node = level.poll();

				if (node.getV().equals(v)) {

					return node;
				}

				if (node.left != null)
					level.add(node.left);
				if (node.right != null)
					level.add(node.right);
			}
		}
		return null;
	}

	private Node<K, V> searchKey(K k) {

		return this.searchKey(this.root, k);
	}

	private Node<K, V> searchKey(Node<K, V> node, K k) {

		if (k != null && node != null) {
			if (node.compareTo(k) == 0) {

				return node;
			} else if (node.compareTo(k) > 0) {
				return this.searchKey(node.right, k);

			} else {
				return this.searchKey(node.left, k);
			}

		}
		return null;
	}

	public String toString() {
		String erg = "";

		erg += "\tPrint Tree\n";
		erg += "---------------------------------------\n";
		erg += "" + this.print(this.root, 0);
		erg += "_______________________________________\n";

		return erg;
	}

	private String print(Node<K, V> node, int level) {
		String distance = "";
		String erg = "";
		for (int i = 0; i < level; i++) {
			distance += " -";
		}
		if (node != null) {
			erg += this.print(node.getRight(), level + 1);
			erg += distance + "[" + level + "]" + node.toString() + "\n";
			erg += this.print(node.getLeft(), level + 1);
		}

		return erg + "";
	}

	@Override
	public void clear() {
		this.root = null;

	}

	@Override
	public V get(K k) {
		Node<K, V> node = this.searchKey(k);
		if (node != null) {
			return node.v;
		} else {
			return null;
		}
	}

	@Override
	public int size() {
		return (size(this.root));
	}

	private int size(Node<K, V> node) {
		if (node == null) {
			return 0;
		} else {
			return (size(node.getLeft()) + 1 + size(node.getRight()));
		}
	}

	@Override
	public void update(K k, V v) {
		this.searchKey(k).setV(v);
	}

	@Override
	public void forEach(BiConsumer<K, V> biConsumer) {

		if (this.root != null) {
			this.forEach(this.root, biConsumer);
		}
	}

	/**
	 * helper method which recursively manipulate the value of a pair of
	 * key-values by using a given biConsumer
	 * 
	 * @param node
	 *            (pair of key-values)
	 * @param biConsumer
	 *            (lambda expression)
	 */

	private void forEach(Node<K, V> node, BiConsumer<K, V> biConsumer) {
		if (node != null) {
			biConsumer.accept(node.getK(), node.getV());
			forEach(node.getLeft(), biConsumer);
			forEach(node.getRight(), biConsumer);
		}
	}

	@Override
	public void putAll(AssociativeArray<K, V> b) {
		if (b instanceof Tree) {
			Tree<K, V> tree = (Tree<K, V>) b;
			this.putAll(tree.root);
		}
	}

	/**
	 * helper method which recursively insert all nodes into the given
	 * associative array
	 * 
	 * @param node
	 *            from giben
	 */

	private void putAll(Node<K, V> node) {
		if (node != null) {
			this.put(node.getK(), node.getV());
			putAll(node.getLeft());
			putAll(node.getRight());
		}
	}

	@Override
	public void extractAll(AssociativeArray<K, V> b) {
		if (b instanceof Tree) {
			((Tree<K, V>) b).putAll(this.root);
		}
	}

	@Override
	public AssociativeArray<K, V> map(BiFunction<K, V, V> biFunction) {
		AssociativeArray<K, V> newTree = new Tree<K, V>();
		return this.map(this.root, biFunction, newTree);

	}

	/**
	 * helper method which recursively manipulate the value of a pair of
	 * key-values by using a given biFunktion
	 * 
	 * @param node
	 *            (pair of key-values)
	 * @param biFunction
	 *            (lambda expression)
	 * @param newTree
	 *            associative array
	 * @return a new associative array
	 */
	private AssociativeArray<K, V> map(Node<K, V> node,
	        BiFunction<K, V, V> biFunction, AssociativeArray<K, V> newTree) {

		if (node != null) {

			newTree.put(node.getK(), biFunction.apply(node.getK(), node.getV()));

			this.map(node.left, biFunction, newTree);
			this.map(node.right, biFunction, newTree);
		}

		return newTree;

	}

	@Override
	public boolean containsValue(V v) {
		if (searchValue(v) != null) {
			return true;
		} else {
			return false;
		}

	}

}
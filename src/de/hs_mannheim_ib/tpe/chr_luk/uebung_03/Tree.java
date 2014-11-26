package de.hs_mannheim_ib.tpe.chr_luk.uebung_03;

import java.util.LinkedList;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * 
 * it is a Binary-Tree. with implemented interface {@link AssociativeArray} with
 * generic parameter K (Key) and V (Value) could handle object or sub-classes
 * 
 * the inner class node represents the key-value pairs. *
 * 
 * @author
 *
 * @param <K>
 *            is Key of AssociativeArray
 * @param <V>
 *            is Value of AssociativeArray
 */
class Tree<K, V> implements AssociativeArray<K, V> {

	private Node<K, V> root;

	public Tree() {
		this.root = null;

	}

	/**
	 * 
	 * @param k
	 *            key
	 * @param v
	 *            value
	 */

	public Tree(K k, V v) {
		this.root = new Node<K, V>(k, v, null, null, null);

	}

	/**
	 * Inner-class of Tree represents the nodes of the binary-tree
	 * 
	 * @author
	 *
	 * @param <K>
	 * @param <V>
	 */

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
		 * @return the key
		 */
		public K getK() {
			return this.k;
		}

		/**
		 * @return the value
		 */
		public V getV() {
			return this.v;
		}

		/**
		 * compares the given key with the key of this node
		 * 
		 * @param given
		 *            key
		 * @return if equal 0, if greater 1 and if smaller -1
		 */

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

		/**
		 * 
		 * @return Node left child of this node
		 */

		public Node<K, V> getLeft() {
			return left;
		}

		/**
		 * 
		 * @param Node
		 *            the new left child
		 */

		public void setLeft(Node<K, V> left) {
			left.parent = this;
			this.left = left;
		}

		/**
		 * 
		 * @return Node the right child of this node
		 */

		public Node<K, V> getRight() {
			return right;
		}

		/**
		 * 
		 * @param Node
		 *            the new right child of this node
		 */

		public void setRight(Node<K, V> right) {
			right.parent = this;
			this.right = right;
		}

		/**
		 * @param key
		 *            the key to set
		 */
		public void setK(K k) {
			this.k = k;
		}

		/**
		 * @param value
		 *            the value to set
		 */
		public void setV(V v) {
			this.v = v;
		}

		/**
		 * 
		 * @return parent node of this node
		 */

		public Node<K, V> getParent() {
			return parent;
		}

		/**
		 * 
		 * @param parent
		 *            node of this node
		 */

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

	/**
	 * internal use only helper method which recursively adds the given node to
	 * the {@link AssociativeArray} as {@link Tree}
	 * 
	 * @param node
	 *            which one will be add to the tree!
	 */
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

	/**
	 * internal use only uses bread-first-search to traverse the tree helper
	 * method which search a node by a given value
	 * 
	 * @param value
	 *            which should be found into the tree
	 * @return node which was searched for or null if not found
	 */

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

	/**
	 * internal use only uses depth-first-search to traverse the tree helper
	 * method which search a node by a given key
	 * 
	 * @param value
	 *            which should be found into the tree
	 * @return node which was searched for
	 */
	private Node<K, V> searchKey(K k) {

		return this.searchKey(this.root, k);
	}

	/**
	 * internal use only contains to searchKey(K k) it recursively traverse the
	 * tree
	 * 
	 * @param node
	 *            which is passed due travesty
	 * @param key
	 *            which is compared with the nodes of the tree due travesty
	 * @return node if found or null if not found
	 */

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

	@Override
	public String toString() {
		String erg = "";

		erg += "\tPrint Tree\n";
		erg += "---------------------------------------\n";
		erg += "" + this.print(this.root, 0);
		erg += "_______________________________________\n";

		return erg;
	}

	/**
	 * internal use only format the tree nodes for output by traverse the tree
	 * recusivly.
	 * 
	 * @param node
	 *            which to start (mainly root-node)
	 * @param level
	 *            of the current depth of the tree
	 * @return String which contains the formated tree for output
	 */

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
	public void putAll(Tree<? extends K, ? extends V> b) {

		this.putAll(b.root);

	}

	/**
	 * helper method which recursively insert all nodes into the given
	 * associative array
	 * 
	 * @param node
	 *            from given
	 */

	private void putAll(
	        Tree<? extends K, ? extends V>.Node<? extends K, ? extends V> node) {
		if (node != null) {
			this.put(node.getK(), node.getV());
			putAll(node.getLeft());
			putAll(node.getRight());
		}
	}

	@Override
	public void extractAll(AssociativeArray<K, V> b) {

		this.putAll(this.root);

	}

	@Override
	public <T> T map(BiFunction<K, V, V> biFunction) {

		return (T) this.map(this.root, biFunction,
		        this.newInstance(this.getClass()));

	}

	/**
	 * helper method produce a new instance of the class itself the type of
	 * return value is a generic one.
	 * 
	 * @param type
	 *            is the type of the given class
	 * @return is a instance of the class itself
	 */

	public <T> T newInstance(Class<T> type) {

		try {
			return type.newInstance();
		} catch (InstantiationException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		}

	}

	/**
	 * helper method which recursively manipulate the value of a pair of
	 * key-values by using a given biFunktion
	 * 
	 * @param node
	 *            (pair of key-values)
	 * @param biFunction
	 *            (lambda expression)
	 * @param tree
	 * @param newTree
	 *            associative array
	 * @return a new associative array
	 */
	private <T> T map(Node<K, V> node, BiFunction<K, V, V> biFunction,
	        Tree<K, V> tree) {

		if (node != null && tree != null) {

			tree.put(node.getK(), biFunction.apply(node.getK(), node.getV()));

			this.map(node.left, biFunction, tree);
			this.map(node.right, biFunction, tree);
		}

		return (T) tree;

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
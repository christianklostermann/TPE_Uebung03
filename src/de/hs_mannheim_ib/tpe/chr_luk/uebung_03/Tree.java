package de.hs_mannheim_ib.tpe.chr_luk.uebung_03;



import java.util.LinkedList;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class Tree<K, V> implements AssociativeArray<K, V> {

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
			left.setParent(this);
			this.left = left;
		}

		public Node<K, V> getRight() {
			return right;
		}

		public void setRight(Node<K, V> right) {
			right.setParent(this);
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
	}

	@Override
	public boolean isEmpty() {

		return (this.root == null);
	}
	@Override
	public void put(K k, V v) {

		if (k != null && v != null) {
			Node<K, V> parent = null; // parent of child
			Node<K, V> child = this.root;
			if (child == null) { // empty tree
				this.root = new Node<K, V>(k, v, null, null, null);

				return;
			}

			while (child != null) { // descend tree
				parent = child; // parent, then child
				if (child.compareTo(k) == 0) {
					return; // element in tree
				} else if (child.compareTo(k) < 0) {
					child = child.getLeft(); // left sub tree
				} else if (child.compareTo(k) > 0) {
					// i.compareTo (child.value) > 0
					child = child.getRight();// right sub tree
				}
			}

			// parent node found
			if (parent.compareTo(k) < 0) {
				// insert left from parent
				parent.setLeft(new Node<K, V>(k, v, null, null, parent));
			} else {
				// insert right from parent
				parent.setRight(new Node<K, V>(k, v, null, null, parent));
			}
		}
	}

	@Override
	public V remove(K k) {
		Node<K, V> parent = null; // parent of child
		Node<K, V> child = this.root;
		V value = null;

		if (this.root != null && child != null && k != null) {

			// search game element, save parent and child nodes
			child = this.search(k);	
			parent = child.getParent();
			
			// no empty tree
			if (child != null) {
				if (this.root.compareTo(k) == 0) {

					if (this.root.getLeft() == null) {
						this.root = this.root.getRight();

					} else if (this.root.getRight() == null) {
						this.root = this.root.getLeft();
					} else if (this.root.getRight() != null
					        && this.root.getLeft() != null) {
						Node<K, V> big = getBiggestElemLeftSubT(this.root);
						big.setLeft(this.root.getLeft());
						big.setRight(this.root.getRight());
						root = big;

					}

					// is leaf
				} else if (child.getRight() == null && child.getLeft() == null) {

					if (parent.getLeft() != null
					        && parent.getLeft().equals(child)) {
						value = parent.getLeft().getV();
						parent.left = null;

					}
					if (parent.getRight() != null
					        && parent.getRight().equals(child)) {
						value = parent.getRight().getV();
						parent.setRight(null);
					}
					// game is inner Node
				} else if (child.getRight() != null || child.getLeft() != null) {

					if (child.getLeft() == null) {

						if (parent.getLeft() != null
						        && parent.getLeft().equals(child)) {
							value.equals(parent.getLeft().getV());
							parent.setLeft(child.getRight());
						}
						if (parent.getRight() != null
						        && parent.getRight().equals(child)) {
							value = parent.getRight().getV();
							parent.setRight(child.getRight());
						}
					} else if (child.getRight() == null && parent != null) {

						if (parent.getLeft() != null
						        && parent.getLeft().equals(child)) {
							value = parent.getLeft().getV();
							parent.setLeft(child.getLeft());
						}
						if (parent.getRight() != null
						        && parent.getRight().equals(child)) {
							value = parent.getRight().getV();
							parent.setRight(child.getLeft());
						}
					} else if (child != null && parent != null) {

						if (parent.getLeft() != null
						        && parent.getLeft().equals(child)) {
							value = parent.getLeft().getV();
							Node<K, V> big = getBiggestElemLeftSubT(child);
							parent.setLeft(big);
							big.setLeft(child.getLeft());
							big.setRight(child.getRight());
						}
						if (parent.getRight() != null
						        && parent.getRight().equals(child)) {
							value = parent.getRight().getV();
							Node<K, V> big = getBiggestElemLeftSubT(child);
							parent.setRight(big);

							big.setLeft(child.getLeft());
							big.setRight(child.getRight());
						}

					}

				}

			}
		}
		return value;
	}

	private Node<K, V> getBiggestElemLeftSubT(Node<K, V> child) {

		// go ones left and then only right
		Node<K, V> tmpParent = child;
		Node<K, V> tmp = child.getLeft();

		while (tmp.getRight() != null) {

			tmpParent = tmp;
			tmp = tmp.getRight();
		}

		// proves if child has left node
		// add it to the right side of par.
		if (tmp.getLeft() != null) {
			tmpParent.setRight(tmp.getLeft());
		} else {

			tmpParent.setRight(null);
		}

		Node<K, V> newTmp = new Node<K, V>(tmp.getK(), tmp.getV(),
		        tmp.getLeft(), tmp.getRight(), tmpParent);
		tmp = null;

		return newTmp;
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


	/*
		public boolean containsValue(V v) {
	if (this.search(this.root, v) != null) {
			return true;
		} else {
			return false;
		}

	}*/

	/*
	 * private Node<K, V> search(Node<K, V> node, V value) { if (node == null) {
	 * return null; } if (node.getV().equals(value)) { return node; } if (node
	 * != null) { search(node.getLeft(), value); search(node.getRight(), value);
	 * } return null; }
	 */
	private Node<K,V> search(K k) {

	
		return this.BFS(k);
	}

	private Node<K,V> BFS(K k) {
		if (k != null) {
			LinkedList<Node<K,V>> level = new LinkedList<>();

			level.add(this.root);

			while (!level.isEmpty()) {
				Tree<K, V>.Node<K, V> node = level.poll();

				if (node.getK().equals(k)) {
			
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
	

	public String toString() {
		String erg = "";

		erg += "Print Tree\n";
		erg += "-------------";
		erg += "" + printhelper(this.root, 0);
		erg += "_____________\n";

		return erg;
	}

	private String printhelper(Node<K, V> node, int level) {
		String distance = "";
		String erg = "\n";
		for (int i = 0; i < level; i++) {
			distance += " ";
		}
		if (node != null) {
			erg += printhelper(node.getRight(), level + 1);
			erg += distance + "[" + level + "][#" + node.getK() + "~"
			        + node.getV().toString() + "]";
			erg += printhelper(node.getLeft(), level + 1);
		}

		return erg + "";
	}



	@Override
	public void clear() {
		this.root = null;

	}

	@Override
	public V get(K k) {
		return this.search(k).getV();
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
		this.search(k).setV(v);
	}



	@Override
	public void forEach(BiConsumer<K, V> biConsumer) {

		if (this.root != null) {
			this.forEach(this.root, biConsumer);
		}
	}

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
			this.putAll(tree.getRoot());
		}
	}

	/**
	 * @return the root
	 */
	private Node<K, V> getRoot() {
		return this.root;
	}

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
	    // TODO Auto-generated method stub
	    return false;
    }

}
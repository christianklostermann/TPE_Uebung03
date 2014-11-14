package de.hs_mannheim_ib.tpe.chr_luk.uebung_03;

import java.util.Map;

public class Tree<K, V, B, T> implements AssociativeArray<K, V, B> {

	private Node<K, V> root;

	public Tree() {
		root = null;

	}

	public Tree(K k, V v) {
		root = new Node(k, v, null, null);

	}

	public boolean isEmpty() {

		return (root == null);
	}

	private Tree getLeft() {

		if (root != null) {
			Tree t = new Tree();
			t.root = root.left;
			return t;
		} else {
			return null;
		}

	}

	private void setLeft(Tree t) {
		if (this.isEmpty()) {
			this.root = t.root;
		} else {
			this.root.left = t.root.left;
		}
	}

	private Tree getRight() {
		if (root != null) {
			Tree t = new Tree();
			t.root = root.right;
			return t;
		} else {
			return null;
		}

	}

	private void setRight(Tree t) {
		if (this.isEmpty()) {
			this.root = t.root;
		} else {
			this.root.right = t.root.right;
		}
	}

	public void insert(K k, V v) {

		if (k != null && v != null) {
			Node parent = null; // parent of child
			Node child = root;
			if (child == null) { // empty tree
				root = new Node(k, v, null, null);
				return;
			}

			while (child != null) { // descend tree
				parent = child; // parent, then child
				if (child.compareTo(k) == 0) {
					return; // element in tree
				} else if (child.compareTo(k) < 0) {
					child = child.left; // left sub tree
				} else
					// i.compareTo (child.value) > 0
					child = child.right;// right sub tree
			}

			// parent node found
			if (child.compareTo(k) < 0) {
				// insert left from parent
				parent.left = new Node(k, v, null, null);
			} else {
				// insert right from parent
				parent.right = new Node(k, v, null, null);
			}
		}
	}

	@Override
	public V remove(K k) {
		Node parent = null; // parent of child
		Node child = root;
		V value = null;

		if (root != null && child != null && k != null) {

			// search game element, save parent and child nodes

			while (child != null) {

				if (child.compareTo(k) == 0) {

					break;
				}

				parent = child;

				if (child.compareTo(k) < 0) {
					child = child.right;

				} else if (child.compareTo(k) > 0) {
					child = child.left;

				}

			}

			// no empty tree and game is part of tree
			if (child != null) {
				if (root.compareTo(k) == 0) {

					if (root.left == null) {
						root = root.right;
					} else if (root.right == null) {
						root = root.left;
					} else if (root.right != null && root.left != null) {
						Node big = getBiggestElemLeftSubT(root);
						big.left = root.left;
						big.right = root.right;
						root = big;

					}

					// is leaf
				} else if (child.right == null && child.left == null) {

					if (parent.left != null && parent.left.equals(child)) {
						value = (V) parent.left.getV();
						parent.left = null;

					}
					if (parent.right != null && parent.right.equals(child)) {
						value = (V) parent.right.getV();
						parent.right = null;
					}
					// game is inner Node
				} else if (child.right != null || child.left != null) {

					if (child.left == null) {

						if (parent.left != null && parent.left.equals(child)) {
							value = (V) parent.left.getV();
							parent.left = child.right;
						}
						if (parent.right != null && parent.right.equals(child)) {
							value = (V) parent.right.getV();
							parent.right = child.right;
						}
					} else if (child.right == null && parent != null) {

						if (parent.left != null && parent.left.equals(child)) {
							value = (V) parent.left.getV();
							parent.left = child.left;
						}
						if (parent.right != null && parent.right.equals(child)) {
							value = (V) parent.right.getV();
							parent.right = child.left;
						}
					} else if (child != null && parent != null) {

						if (parent.left != null && parent.left.equals(child)) {
							value = (V) parent.left.getV();
							Node big = getBiggestElemLeftSubT(child);
							parent.left = big;
							big.left = child.left;
							big.right = child.right;
						}
						if (parent.right != null && parent.right.equals(child)) {
							value = (V) parent.right.getV();
							Node big = getBiggestElemLeftSubT(child);
							parent.right = big;

							big.left = child.left;
							big.right = child.right;
						}

					}

				}

			}
		}
		return value;
	}

	private Node getBiggestElemLeftSubT(Node child) {

		// go ones left and then only right
		Node tmpParent = child;
		Node tmp = child.left;

		while (tmp.right != null) {

			tmpParent = tmp;
			tmp = tmp.right;
		}

		// proves if child has left node
		// add it to the right side of par.
		if (tmp.left != null) {
			tmpParent.right = tmp.left;
		} else {

			tmpParent.right = null;
		}

		Node newTmp = new Node(tmp.getK(),tmp.getV(), tmp.left, tmp.right);
		tmp = null;

		return newTmp;
	}

	public boolean containsKey(K k) {

		Node tmp = root;
		

		while (tmp != null) {

			if (tmp.compareTo(k) == 0) {
				return true;
			}
			if (tmp.compareTo(k) < 0) {
				tmp = tmp.right;
			} else {
				tmp = tmp.left;
			}

		}

		return false;
	}

	public void printTree() {
		System.out.println();
		System.out.println("Print Tree");
		System.out.println("-------------");
		printhelper(root, 0);
		System.out.println("_____________");
		System.out.println();
	}

	private void printhelper(Node root, int level) {
		String distance = "";
		for (int i = 0; i < level; i++) {
			distance += " ";
		}
		if (root != null) {
			printhelper(root.right, level + 1);
			System.out
			        .println(distance + "[" + level + "]" + root.hashCode());
			printhelper(root.left, level + 1);
		}
	}

	public Node depthFirstSearch(K k) {

	
		return DFS2(root, k);
	}

	private Node DFS2(Node node, K k) {

		Node left = null;
		Node right = null;

		if (node != null) {

		
			if (node.equals(k)) {

				return node;
			}

			left = DFS2(node.left, k);

			if (left == null) {
				right = DFS2(node.right, k);
			}

		}

		if (left != null) {
			return left;
		} else {
			return right;
		}

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean containsValue() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsKey() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(K k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(K k, V v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void putAll(Map m) {
		// TODO Auto-generated method stub

	}

	@Override
	public long size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(K k, V v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void forEach(B b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void extractAll(Map m) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map map(B b) {
		// TODO Auto-generated method stub
		return null;
	}

}

package de.hs_mannheim_ib.tpe.chr_luk.uebung_03;

import java.util.Map;

public class Tree<K, V, B, T> implements AssociativeArray<K, V, B> {

	private Node<K, V> root;
	private int nodeCount;

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
			t.root = root.getLeft();
			return t;
		} else {
			return null;
		}

	}

	private void setLeft(Tree t) {
		if (this.isEmpty()) {
			this.root = t.root;
		} else {
			this.root.setLeft(t.root.getLeft());
		}
	}

	private Tree getRight() {
		if (root != null) {
			Tree t = new Tree();
			t.root.setRight(root.getRight());
			return t;
		} else {
			return null;
		}

	}

	private void setRight(Tree t) {
		if (this.isEmpty()) {
			this.root = t.root;
		} else {
			this.root.setRight(t.root.getRight());
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
					child = child.getLeft(); // left sub tree
				} else if (child.compareTo(k) > 0) {
					// i.compareTo (child.value) > 0
					child = child.getRight();// right sub tree
				}
			}

			// parent node found
			if (parent.compareTo(k) < 0) {
				// insert left from parent
				parent.setLeft(new Node(k, v, null, null));
			} else {
				// insert right from parent
				parent.setRight(new Node(k, v, null, null));
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
					child = child.getRight();

				} else if (child.compareTo(k) > 0) {
					child = child.getLeft();

				}

			}

			// no empty tree and game is part of tree
			if (child != null) {
				if (root.compareTo(k) == 0) {

					if (root.getLeft() == null) {
						root = root.getRight();
					} else if (root.getRight() == null) {
						root = root.getLeft();
					} else if (root.getRight() != null
					        && root.getLeft() != null) {
						Node big = getBiggestElemLeftSubT(root);
						big.setLeft(root.getLeft());
						big.setRight(root.getRight());
						root = big;

					}

					// is leaf
				} else if (child.getRight() == null && child.getLeft() == null) {

					if (parent.getLeft() != null
					        && parent.getLeft().equals(child)) {
						value = (V) parent.getLeft().getV();
						parent.setLeft(null);

					}
					if (parent.getRight() != null
					        && parent.getRight().equals(child)) {
						value = (V) parent.getRight().getV();
						parent.setRight(null);
					}
					// game is inner Node
				} else if (child.getRight() != null || child.getLeft() != null) {

					if (child.getLeft() == null) {

						if (parent.getLeft() != null
						        && parent.getLeft().equals(child)) {
							value = (V) parent.getLeft().getV();
							parent.setLeft(child.getRight());
						}
						if (parent.getRight() != null
						        && parent.getRight().equals(child)) {
							value = (V) parent.getRight().getV();
							parent.setRight(child.getRight());
						}
					} else if (child.getRight() == null && parent != null) {

						if (parent.getLeft() != null
						        && parent.getLeft().equals(child)) {
							value = (V) parent.getLeft().getV();
							parent.setLeft(child.getLeft());
						}
						if (parent.getRight() != null
						        && parent.getRight().equals(child)) {
							value = (V) parent.getRight().getV();
							parent.setRight(child.getLeft());
						}
					} else if (child != null && parent != null) {

						if (parent.getLeft() != null
						        && parent.getLeft().equals(child)) {
							value = (V) parent.getLeft().getV();
							Node big = getBiggestElemLeftSubT(child);
							parent.setLeft(big);
							big.setLeft(child.getLeft());
							big.setRight(child.getRight());
						}
						if (parent.getRight() != null
						        && parent.getRight().equals(child)) {
							value = (V) parent.getRight().getV();
							Node big = getBiggestElemLeftSubT(child);
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

	private Node getBiggestElemLeftSubT(Node child) {

		// go ones left and then only right
		Node tmpParent = child;
		Node tmp = child.getLeft();

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

		Node newTmp = new Node(tmp.getK(), tmp.getV(), tmp.getLeft(),
		        tmp.getRight());
		tmp = null;

		return newTmp;
	}

	@Override
	public boolean containsKey(K k) {

		Node tmp = root;

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

	@Override
	public boolean containsValue(V v) {

		Node tmp = root;

		if (tmp.getV().equals(v)) {

			while (tmp != null) {

				if (tmp.compareTo(v) == 0) {

					return true;
				}
				if (tmp.compareTo(v) < 0) {
					tmp = tmp.getRight();
				} else {
					tmp = tmp.getLeft();
				}

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
			printhelper(root.getRight(), level + 1);
			System.out.println(distance + "[" + level + "]" + root.hashCode());
			printhelper(root.getLeft(), level + 1);
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

			left = DFS2(node.getLeft(), k);

			if (left == null) {
				right = DFS2(node.getRight(), k);
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
		this.root = null;

	}

	@Override
	public V get(K k) {
		Node tmp = root;

		if (tmp.getK().equals(k)) {

			while (tmp != null) {

				if (tmp.compareTo(k) == 0) {

					return (V) tmp.getV();
				}
				if (tmp.compareTo(k) < 0) {
					tmp = tmp.getRight();
				} else {
					tmp = tmp.getLeft();
				}

			}
		}

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
	public int size() {
		return (size(this.root));
	}

	private int size(Node node) {
		if (node == null)
			return (0);
		else {
			return (size(node.getLeft()) + 1 + size(node.getRight()));
		}
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

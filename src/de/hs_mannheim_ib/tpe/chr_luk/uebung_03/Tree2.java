package de.hs_mannheim_ib.tpe.chr_luk.uebung_03;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;


public class Tree2<K extends Comparable<K>, V> implements AssociativeArray2<K, V> {
	
	private class Node {
		public Node left;
		public Node right;
		
		public K key;
		public V value;
		
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
	
	public List<Node> getNodeList(Node node) {
		List<Node> list = new LinkedList();
		if(node.left != null) list.addAll(getNodeList(node.left));
		if(node.right != null) list.addAll(getNodeList(node.right));
		return list;
	}
	
	public Map<K, V> getNodeMap(Node node) {
		Map<K, V> map = new HashMap();
		if(node.left != null) map.putAll(getNodeMap(node.left));
		if(node.right != null) map.putAll(getNodeMap(node.right));
		return map;
	}
	
	private Node root;
	
	private Node searchKey(K key) {
		if(root == null) {
			return null;
		} else {
			return searchKeyRecursive(root, key);
		}
	}
	
	private Node searchKeyRecursive(Node node, K key) {
		if(node.key.compareTo(key) == 0) {
			return node;
		} else if(key.compareTo(node.key) > 0 && node.left != null) {
			return searchKeyRecursive(node.left, key);
		}  else if(key.compareTo(node.key) < 0 && node.right != null) {
			return searchKeyRecursive(node.right, key);
		}
		return null;
	}
	
	private boolean searchValueRecursive(Node node, V value) {
		if(node.value.equals(value)) {
			return true;
		} else if(searchValueRecursive(node.left, value)) {
			return true;
		} else if(searchValueRecursive(node.right, value)) {
			return true;
		}
		return false;
	}
	
//	private void insert(K key, V value) {
//		if(root == null) {
//			root = new Node(key, value);
//		} else {
//			insertRecursive(root, key, value);
//		}
//	}
	
	private void insertRecursive(Node node, K key, V value) {
		if(node.key.compareTo(key) == 0) {
			node.key = key;
			node.value = value;
		} else if(node.key.compareTo(key) > 1 && node.left != null) {
			insertRecursive(node.left, key, value);
		} else if(node.key.compareTo(key) < 1 && node.right != null) {
			insertRecursive(node.right, key, value);
		} else if(node.key.compareTo(key) > 1) {
			node.left = new Node(key, value);
		} else if(node.key.compareTo(key) < 1) {
			node.right = new Node(key, value);
		}
	}
	
	private void forEachNode(BiConsumer<K, V> consumer, Node node) {
//		consumer.accept(node.key, node.value);
		if(node.left != null) {
			consumer.accept(node.left.key, node.left.value);
			forEachNode(consumer, node.left);
		}
		if(node.right != null) {
			consumer.accept(node.right.key, node.right.value);
			forEachNode(consumer, node.right);
		}
	}

	public void clear() {
		root = null;
	}

	public boolean containsValue(V value) {
		if(root == null) {
			return false;
		} else {
			return searchValueRecursive(root, value);
		}
	}

	public boolean containskey(K key) {
		if(searchKey(key) != null) {
			return true;
		}
		return false;
	}

	public V get(K key) {
		return searchKey(key).value;
	}

	public boolean isEmpty() {
		if(root != null) {
			return true;
		} else {
			return false;
		}
	}

	public void put(K key, V value) {
//		insert(key, value);
		if(root == null) {
			root = new Node(key, value);
		} else {
			insertRecursive(root, key, value);
		}
	}

	public void putAll(AssociativeArray2<K, V> array) {
		array.forEach((key, value) -> this.put(key, value)); 
	}

	private Node removeRecursive(Node node, K key) {
		if(node.left != null) {
			if(node.left.key.equals(key)) {
				Node temp = node.left;
				node.left = null;
				return temp;
			}
		} else if(node.right != null) {
			if(node.right.key.equals(key)) {
				Node temp = node.right;
				node.right = null;
				return temp;
			}
		}
		if(node.left != null) {
			return removeRecursive(node.left, key);
		}
		if(node.right != null) {
			return removeRecursive(node.right, key);
		}
		return null;
	}
	
	@Override
	public V remove(K key) {
		if(root != null && root.key.equals(key)) {
			Node node = root;
			root = null;
			return node.value;
		} else {
			Node node = removeRecursive(root, key);
			if(node != null) {
				forEachNode((K t, V u) -> put(t, u), node);
				return node.value;
			}
		}
		return null;
	}

	@Override
	public int size() {
		return getNodeList(root).size();
	}

	@Override
	public void update(K key, V value) {
		searchKey(key).value = value;
	}

	@Override
	public void forEach(BiConsumer<K, V> consumer) {
		if(root != null) {
			consumer.accept(root.key, root.value);
			forEachNode(consumer, root);
		}
	}


	public void extractAll(AssociativeArray2<K, V> array) {
		this.forEach((key, value) -> array.put(key, value));
		
//		this.forEach(new BiConsumer<K, V>() {
//			@Override
//			public void accept(K k, V v) {
//				// TODO Auto-generated method stub
//				array.put(k, v);
//			}
//		});
	}

	public AssociativeArray2<K, V> map(BiFunction<K, V, AssociativeArray2<K, V>> function) {
		// TODO Auto-generated method stub
		return null;
	}

}

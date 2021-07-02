package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
    int size;
    
	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		root = null;
		size =0;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if(x == null){return false;}
		else if(root == null){
			root = new BinaryNode<>(x);
			size++;
			return true;
		} else{
			return add(root, x);
		}
	}

	private boolean add(BinaryNode<E> n, E x){
		boolean b = true;
		if(n.element.compareTo(x) == 0){
			b = false;
		} else if(n.element.compareTo(x) > 0){
			if(n.left == null){
			n.left = new BinaryNode<>(x);
			size++;
			}else add(n.left,x);
		}else if(n.element.compareTo(x) < 0){
			if(n.right == null) {
				n.right = new BinaryNode<>(x);
				size++;
			} else add(n.right,x);
		}
		return b;
	}

	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}

		private int height(BinaryNode<E> n){
			if (n == null ) {
				return 0;
			} else {
				int leftHeight = height(n.left);
				int rightHeight = height(n.right);
				return 1 + Math.max(leftHeight, rightHeight);
			}
		}



	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);
	}

	private void printTree(BinaryNode<E> n) {
		if (n != null) {
			printTree(n.left);
			System.out.println(n.element);
			printTree(n.right);
		}
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		E[] a = (E[]) new Comparable[size];
		toArray(root,a,0);
		root = buildTree(a,0,a.length - 1);
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index].
	 * Returns the index of the last inserted element + 1 (the first empty
	 * position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		if(n != null){
			index = toArray(n.left,a,index);
			a[index++] = n.element;
			index = toArray(n.right,a,index);
		} return index;
	}
	/*
	 * Builds a complete tree from the elements a[first]..a[last].
	 * Elements in the array a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		if(first > last) return null;
		int mid = (first + last) / 2;
		BinaryNode<E> root = new BinaryNode<>(a[mid]);
		root.left = buildTree(a, first, mid - 1);
		root.right = buildTree(a, mid + 1, last);
		return root;
	}
	


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}

	public static void main(String[] args){
		BinarySearchTree tree = new BinarySearchTree<Integer>();
		for(int i =1; i < 20; i++){
			tree.add(i);
		}
		tree.rebuild();
		BSTVisualizer scene = new BSTVisualizer("BinaryTree",400,400);
		scene.drawTree(tree);


	}

}


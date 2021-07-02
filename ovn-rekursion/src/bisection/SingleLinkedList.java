package bisection;

public class SingleLinkedList<E> {
	private ListNode<E> first;
	public SingleLinkedList() {
		first = null;
	}

	private static class ListNode<E> {
		private E element;
		private ListNode<E> next;
		private ListNode(E e) {
			element = e;
			next = null;
		}
	}

	public void add(E x) {
		if (first == null) {
			first = new ListNode<E>(x);
		} else {
			add(first, x);
		}
	}


	private void add(ListNode<E> node, E x) {
		if (node.next == null) {
			node.next = new ListNode<E>(x);
		} else {
			add(node.next, x);
		}
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		ListNode<E> node = first;
		while(node != null){
			sb.append(node.element.toString());
			node = node.next;
		}  return sb.toString();
	}

	public static void main(String[] args) {
		SingleLinkedList<Integer> list = new SingleLinkedList();
		System.out.println(list.first);
		list.add(1);
		list.add(2);
		list.add(3);;
		list.add(4);
		System.out.println(list);

	}

}

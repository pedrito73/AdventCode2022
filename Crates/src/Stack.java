import java.util.Random;

public class Stack<T> {

	int c = 0;
	Node<T> top;

	public Stack() {
		super();
	}

	public Stack(T t) {
		c++;
		top = new Node<T>(t);

	}

	public void push(T t) {
		c++;
		Node<T> n = new Node<>(t);
		if (c>1) {n.setNext(top);}
		top = n;
	}

	public T pop() {
		Node<T> n = top;
		if (--c <= 0) {
			top = null;
		} else {
			top = n.getNext();
		}
		return n.getT();
	}

	public T peak() {
		if (c == 0) {
			return null;
		} else {
			return top.getT();
		}
	}

	public int size() {
		return c;
	}

	public boolean isEmpty() {
		return c==0;
	}

	public static void main(String[] args) {
		Random r=new Random();
		Stack<Integer> s=new Stack<>();
		for(int i=0;i<100;i++) {
			int d=r.nextInt(1000);
			s.push(d);
			System.out.println("pushed at "+i+"with value "+d);
		}
		for(int i=0;i<10;i++) {
			int d=s.peak();
			System.out.println("peaked:"+i+" value:"+d);
		}
		for(int i=0;i<100;i++) {
			int d=s.pop();
			System.out.println("popped:"+d+" at pos:"+i);
		}

	}

	@SuppressWarnings("hiding")
	private class Node<T> {
		private T t;
		private Node<T> next;



		public Node(T t) {
			super();
			this.t = t;
			next = null;
		}

		public T getT() {
			return t;
		}



		public Node<T> getNext() {
			return next;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}

	}
}

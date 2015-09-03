package puzzlesolver;

import java.util.ArrayList;
import java.util.List;

public class Queue<T> {
	private List<T> list = new ArrayList<>();
	
	public void push(T t) {
		list.add(t);
	}
	
	public T pop() {
		T svaret = list.get(0);
		list.remove(0);
		return svaret;
	}
	
	public boolean isEmpty() {
		return list.size() == 0;
	}
	
	public String toString() {
		return list.toString();
	}
	
}

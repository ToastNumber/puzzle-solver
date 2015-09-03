package puzzlesolver;

import java.util.ArrayList;
import java.util.List;

public class Queue<T> {
	private List<T> list = new ArrayList<>();
	
	public void push(T t) {
		list.add(t);
	}
	
	public T pop() {
		int index = list.size() - 1;
		T svaret = list.get(index);
		list.remove(index);
		
		return svaret;
	}
	
	public boolean isEmpty() {
		return list.size() == 0;
	}
	
}

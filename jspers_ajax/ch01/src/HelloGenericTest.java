

import java.util.Collection;
import java.util.Vector;

class HelloGeneric<T, V extends Collection<T>> {
	private T[] vals;
	private V val;

	public void set(T[] o) {
		this.vals = o;
	}
	public void set(V o) {
		this.val = o;
	}
	public int length() {
		int len = 0;
		if(null != vals) len += vals.length;
		if(null != val) len += val.size();
		return len;
	}
}

public class HelloGenericTest {
	public static void main(String[] args) {
		HelloGeneric<Integer, Vector<Integer>> generic =
			new HelloGeneric<Integer, Vector<Integer>>();
		Integer[] arr = new Integer[10];
		Vector<Integer> vec = new Vector<Integer>(10);
		for(int i=arr.length-1; i>-1; --i) {
			arr[i] = i;
			vec.add(500-i);
		}
		System.out.println(generic.length());
		generic.set(arr);
		System.out.println(generic.length());
		generic.set(vec);
		System.out.println(generic.length());		
	}
}

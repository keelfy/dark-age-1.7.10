package keelfy.api.java;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author keelfy
 */
public class ArrayHelper {
	public static <T>Iterator<T> getArrayIterator(final T[] array) {
		return new Iterator<T>() {
			private int count = array.length;
			private int index = 0;
			
			@Override
			public boolean hasNext() {
				return index < count;
			}
	 
			@Override
			public T next() {
				if (index < count) {
					return array[index++];
				} else {
					throw new NoSuchElementException("No such element.");
				}
			}
	 
			@Override
			public void remove() {
				throw new UnsupportedOperationException("Cannot remove item from array.");
			}
		};
	}
	
	public static boolean arrayContains(String[] array, String need) {
		String r = "";
		Iterator it = getArrayIterator(array);
		
		while(it.hasNext()) {
			if(it.next().equals(need)) {
				return true;
			}
		}
		
		return false;
	}

}

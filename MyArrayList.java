//I have adhered to the Honor Code in this assignment.

/**
 * Implement my own ArrayList
 * @author Michael Clifford
 * @date 2/17/14
 * @param <AnyType>
 */

import java.util.AbstractList;

public class MyArrayList<AnyType> extends AbstractList<AnyType> {

    private int size;
    private AnyType[] data;
    
    /**
     * constructer
     * @param startsize
     */
    public MyArrayList(int startsize) {
	int i = 1;
	while (i < startsize) {
	     i = i *2;
	}
	data = (AnyType [])new Object[i];  
    }
    
    public MyArrayList() {
	this(2);
    }
 
    @SuppressWarnings("unchecked")
    private void resize() {
	AnyType[] newlist = (AnyType [])new Object[2*data.length];
	for (int i=0; i < data.length; i++) {
	    newlist[i] = data[i];
	}
	data = newlist;
    }
 
    public int size() {
	return size;
    }
	
    public void add(int index, AnyType element) { 
	if (size == data.length) {
	    this.resize();
	}
	if (index > size || index < 0) {
	    throw new IndexOutOfBoundsException("You're index must be between 0 and " + (this.size));
	}
	for (int i = size; i > index; i--) {
	    data[i] = data[i-1];
	}
	data[index] = element;
	size++;
    }
    
    
    public boolean add(AnyType element) {
	this.add((size), element);
	return true;
    }
    
    public AnyType get(int index) {
	if (index >= size || index < 0) {
	    throw new IndexOutOfBoundsException("You're index must be between 0 and " + (size-1));
	}
	return data[index];
    }
    
    public AnyType set(int index, AnyType element) {
	if (index >= size || index < 0) {
	    throw new IndexOutOfBoundsException("You're index must be between 0 and " + (size-1));
	}
	AnyType temp = data[index];
	data[index] = element;
	return temp;
    }
    
    public AnyType remove(int index) {
	if (index >= size || index < 0) {
	    throw new IndexOutOfBoundsException("You're index must be between 0 and " + (size-1));
	}
	    AnyType temp = data[index];
	for (int i = index; i < size; i++) {
	    data[i] = data[i+1];
	}
	size--;
	return temp;
    }
    
    public boolean isEmpty() {
	if (size == 0) {
	    return true;
	}
	return false;
    }
    
    public void clear() {
	for (int i = 0; i < size; i++) {
	    data[i] = null;
	}
	size = 0;
    }
}


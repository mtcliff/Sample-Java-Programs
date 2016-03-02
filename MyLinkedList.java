/**
 * @author Michael Clifford
 * @date 3/3/14
 * Implement a doubly linked list.
 */

import java.util.*;

public class MyLinkedList<T> extends AbstractList<T>  {
    
    protected class Node {
        T data;
        Node next;
        Node prev;
    }
    
    private Node head;
    private Node tail;
    private int size;
    
    public MyLinkedList() { 
	head = new Node();
	tail = new Node();
	head.next = tail;
	tail.prev = head;
	size = 0;
    }
    
    private Node getNth(int index) {
	Node node = head.next;
	for (int i = 0; i < index; i++) {
	    node = node.next;
	}
	return node;
    }
    
    public boolean add(T data) {
	Node node = new Node();
	node.data = data;
	node.next = tail;
	node.prev = tail.prev;
	tail.prev.next = node;
	tail.prev = node;
	size++;
	return true;
    }
    
    public void add(int index, T data) {
	if (index < 0 || index >size) {
	    throw new IndexOutOfBoundsException("The index you gave is out of the list's bounds");
	}
	    Node nnode = new Node();
	nnode.data = data;
	if (index == 0) {
	    nnode.prev = head;
	    nnode.next = head.next;
	    head.next.prev = nnode;
	    head.next = nnode;
	} else {
    		Node n = head.next;
        	for (int i = 1; i < index; i++) {
        	    n = n.next;
        	}
        	nnode.prev = n;
        	nnode.next = n.next;
        	n.next = nnode;
        	nnode.next.prev = nnode;
	}
	size++;
    }
	
    public T get(int index) {
	if (index < 0) {
	    throw new IndexOutOfBoundsException("The index you gave is out of the list's bounds <0");
	}
	if (index >=size) {
	    throw new IndexOutOfBoundsException("The index you gave is out of the list's bounds >=size");
	}
	return getNth(index).data;
    }
    
    public T set(int index, T data) {
	if (data == null) {
	    throw new NullPointerException("Give me some data");
	}
	if (index < 0 || index >=size) {
	    throw new IndexOutOfBoundsException("The index you gave is out of the list's bounds");
	}
	Node node = getNth(index);
	node.data = data;
	return data;
    }
    
    public T remove(int index) {
	if (index < 0 || index >=size) {
	    throw new IndexOutOfBoundsException("The index you gave is out of the list's bounds");
	}
	Node node = getNth(index);
	node.prev.next = node.next;
	node.next.prev = node.prev;
	node.prev = null;
	node.next = null;
	size--;
	return node.data;
    }
    
    public void clear() {
	head.next = tail;
	tail.prev = head;
	size = 0;
    }
    
    public boolean isEmpty() {
	if (size == 0) {
	    return true;
	}
	return false;
    }
    
    public int size() {
	return size;
    }
    

    protected class MyListIterator implements ListIterator<T> {

        private Node cursor;
        private int whatcalled;
        private int index;
        
        MyListIterator() {
            cursor = new Node();
            cursor.prev = MyLinkedList.this.head;
            cursor.next = MyLinkedList.this.head.next;
            whatcalled = 0;
            index = 0;
        }
        
        public boolean hasNext() {
            if (cursor.next != tail) {
        	return true;
            }
            return false;
        }
        
        public T next() {
            if (cursor.next.data == null) {
        	throw new NoSuchElementException("There is nothing left.");
            }
            T temp = cursor.next.data;
            cursor.prev = cursor.next;
            cursor.next = cursor.next.next;
            whatcalled = 1;
            index++;
            return temp;
        }
        
        public boolean hasPrevious() {
            if (cursor.prev.data != null) {
        	return true;
            }
            return false;
        }
        
        public T previous() {
            if (cursor.prev.data == null) {
        	throw new NoSuchElementException("There is nothing left.");
            }            
            T temp = cursor.prev.data;
            cursor.prev = cursor.prev.prev;
            cursor.next = cursor.prev;
            whatcalled = -1;
            index--;
            return temp;
        }
        
        public int nextIndex() {
            if (cursor.next == tail) {
        	return size;
            } else {
        	return index;
            }
        }
        
        public int previousIndex() {
            if (cursor.prev == head) {
        	return -1;
            } else {
        	return index-1;
            }
        }

        public void set(T x) {
            if (whatcalled == 0) {
        	throw new IllegalStateException("You haven't called next or previous");
            } else if (whatcalled == 2 || whatcalled == 3) {
        	throw new IllegalStateException("Call next or previous first");
            }
            else if (whatcalled == 1) {
        	MyLinkedList.this.set(index - 1, x);
            } else {
        	MyLinkedList.this.set(index, x);
            }
        }
        
        public void remove() {
            if (whatcalled == 0) {
        	throw new IllegalStateException("You haven't called next or previous");
            } else if (whatcalled == 2) {
        	throw new IllegalStateException("Call next or previous first");
            }
            else if (whatcalled == 1) {
        	MyLinkedList.this.remove(index - 1); 
        	index--;
            } else {
        	MyLinkedList.this.remove(index); 
            }
            whatcalled = 3;
        }

        public void add(T x) {
            MyLinkedList.this.add(index, x);
            whatcalled = 2;
            index++;
        }
       
    }
    
     public ListIterator<T> listIterator() {
	 MyListIterator iterator = new MyListIterator(); 
	 return iterator; 
	
     }
     
     public Iterator<T> iterator() {
	 MyListIterator iterator = new MyListIterator(); 
	 return iterator; 
     }
}
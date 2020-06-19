package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException();
		}
		
		LLNode<E> curr = new LLNode<E>(element);
		
		curr.next = tail;
		curr.prev = tail.prev;
		
		tail.prev.next = curr;
		tail.prev = curr;
		
		size += 1;
		
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if ((index < 0) | (index >= size)) {
			throw new IndexOutOfBoundsException();
		}
		else {
			int temp_ind = 0;
			LLNode<E> tempnode = head.next;
			while(temp_ind < index) {
				tempnode = tempnode.next;
				temp_ind += 1;
			}
			return tempnode.data;
		}
		
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException();	
		}
		if ((index<0) | (index>size)) {
			throw new IndexOutOfBoundsException();
		}
			
			int temp_ind = 0;
			LLNode<E> tempnode = head;
			while(temp_ind < index) {
				tempnode = tempnode.next;
				temp_ind += 1;
			}
			
			LLNode<E> curr = new LLNode<E>(element);
			curr.prev = tempnode;
			curr.next = tempnode.next;
		
			tempnode.next.prev = curr;
			tempnode.next = curr;
			size += 1;

		
		
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if((index<0)|(index>=size)) {
			throw new IndexOutOfBoundsException();
		}
		
		int temp_ind = 0;
		LLNode<E> curr = head;
		
		while (temp_ind<index) {
			curr = curr.next;
			temp_ind += 1;
		}
		
		E ret = curr.next.data;
		
		curr.next = curr.next.next;
		curr.next.prev = curr;
		
		size -= 1;
		
		return ret;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if((index<0)|(index>=size)) {
			throw new IndexOutOfBoundsException();
		}
		if (element == null) {
			throw new NullPointerException();	
		}
		
		int temp_ind = 0;
		LLNode<E> curr = head;
		
		while (temp_ind < index) {
			curr = curr.next;
			temp_ind += 1;
		}
		
		E ret = curr.next.data;
		curr.next.data = element;
		return ret;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}

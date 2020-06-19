/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		assertEquals("Remove: check size is correct ", (Integer)21, list1.head.next.data);
		assertEquals("Remove: check size is correct ", list1.head, list1.head.next.prev);
		
		// TODO: Add more tests here
		try {
			emptyList.remove(0);
			fail("check empty list");
			
		}
		catch (IndexOutOfBoundsException e) {}
		try {
			shortList.remove(2);
			fail("check last index");
			
		}
		catch (IndexOutOfBoundsException e) {}		
		try {
			shortList.remove(-1);
			fail("check last index");
			
		}
		catch (IndexOutOfBoundsException e) {}			
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		
		//test adding null, get should throw an exception
		try {
			Integer i1 = null;
			emptyList.add(i1);
			fail("Check out of bounds");
		}
		catch (NullPointerException e) {
			
		}
		
		emptyList.add(1);
		assertEquals("Add: test empty list size", 1, emptyList.size);
		assertEquals("Add: next of head", (Integer)1, emptyList.head.next.data);
		assertEquals("Add: prev of tail", (Integer)1, emptyList.tail.prev.data);	
		assertEquals("Add: next of head", emptyList.head, emptyList.head.next.prev);
		assertEquals("Add: prev of tail", emptyList.tail, emptyList.tail.prev.next);	
		
		shortList.add("C");
		assertEquals("Add: test short list size", 3, shortList.size);
		assertEquals("Add: test short list added new", "C", shortList.tail.prev.data);
		assertEquals("Add: test short list new prev", "B", shortList.tail.prev.prev.data);
		assertEquals("Add: test short list tail prev prev next", "C", shortList.tail.prev.prev.next.data);
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("Add: test empty list size", 0, emptyList.size);
		assertEquals("Add: test empty list size", 2, shortList.size);
		
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		//test adding null, get should throw an exception
		try {
			Integer i1 = null;
			emptyList.add(0,i1);
			fail("Check out of bounds");
		}
		catch (NullPointerException e) {
			
		}
		
		
		try {
			Integer i1 = 1;
			emptyList.add(1,i1);
			fail("Check index");
		}
		catch (IndexOutOfBoundsException e) {
			
		}		
		
		try {
			String s1 = "C";
			shortList.add(3,s1);
			fail("Check index");
		}
		catch (IndexOutOfBoundsException e) {
			
		}	
		try {
			String s1 = "C";
			shortList.add(-1,s1);
			fail("Check index");
		}
		catch (IndexOutOfBoundsException e) {
			
		}			
		
		emptyList.add(0,(Integer)1);
		assertEquals("Add: test empty list size", 1, emptyList.size);
		assertEquals("Add: next of head", (Integer)1, emptyList.head.next.data);
		assertEquals("Add: prev of tail", (Integer)1, emptyList.tail.prev.data);	
		assertEquals("Add: next of head", emptyList.head, emptyList.head.next.prev);
		assertEquals("Add: prev of tail", emptyList.tail, emptyList.tail.prev.next);	
		
		shortList.add(2, "C");
		assertEquals("Add: test short list size", 3, shortList.size);
		assertEquals("Add: test short list added new", "C", shortList.tail.prev.data);
		assertEquals("Add: test short list new prev", "B", shortList.tail.prev.prev.data);
		assertEquals("Add: test short list tail prev prev next", "C", shortList.tail.prev.prev.next.data);
		
		shortList.add(1, "D");
		assertEquals("Add: test short list size", 4, shortList.size);
		assertEquals("Add: test short list added new", "D", shortList.tail.prev.prev.prev.data);
		assertEquals("Add: test short list new prev", "A", shortList.tail.prev.prev.prev.prev.data);
		assertEquals("Add: test short list new prev", "D", shortList.head.next.next.data);
		assertEquals("Add: test short list tail prev prev next", "B", shortList.head.next.next.next.data);
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		try {
			emptyList.set(0,1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		try {
			shortList.set(2,"a");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}	    
		try {
			String s = null;
			shortList.set(1,s);
			fail("Check null pointer");
		}
		catch (NullPointerException e) {
			
		}	  
		
		String ret = shortList.set(0,"a");
		assertEquals("Set", "A", ret);
		assertEquals("Set", 2, shortList.size);
		assertEquals("Set", "a", shortList.get(0));
	}
	
	
	// TODO: Optionally add more test methods.
	
}

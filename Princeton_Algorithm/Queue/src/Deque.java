import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int count;

    private class Node<Item>{
        public Item data;
        public Node<Item> next;
        public Node<Item> prev;
    }

    // construct an empty deque
    public Deque(){
        Node<Item> initFirst = new Node<>();
        first = initFirst;
        Node<Item> initLast = new Node<>();
        last = initLast;
        first.next = last;
        last.prev = first;
        count = 0;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return first.next == last;
    }

    // return the number of items on the deque
    public int size(){
        return count;
    }

    // add the item to the front
    public void addFirst(Item item){
        if (item == null){
            throw new IllegalArgumentException();
        }
        Node<Item> oldFirst = first;
        first = new Node<Item>();
        oldFirst.data = item;
        first.next = oldFirst;
        oldFirst.prev = first;
        count += 1;
    }

    // add the item to the back
    public void addLast(Item item){
        if (item == null){
            throw new IllegalArgumentException();
        }
        Node<Item> oldLast = last;
        oldLast.data = item;
        last = new Node<Item>();
        oldLast.next = last;
        last.prev = oldLast;
        count += 1;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }

        Object ret = first.next.data;
        first.next = first.next.next;
        first.next.prev = first;
        count -= 1;
        return (Item) ret;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        Object ret = last.prev.data;
        last.prev = last.prev.prev;
        last.prev.next = last;
        count -= 1;
        return (Item) ret;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){return new DequeIterator();}

    private class DequeIterator implements Iterator<Item>{
        private Node<Item> curr = first;
        public boolean hasNext(){return curr.next != last;}

        @Override
        public Item next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            Item ret = (Item) curr.next.data;
            curr = curr.next;
            return ret;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        // test init
        Deque<Integer> d = new Deque<Integer>();
        System.out.println("size: " + d.size());
        // add first and last
        d.addFirst(1);
        System.out.println("size: " + d.size());
        d.addLast(100);
        System.out.println("size: " + d.size());

        // iterator
        for (Integer i: d){
            System.out.println(i);
        }

        // remove
        System.out.println(d.removeFirst());
        System.out.println(d.removeLast());


    }

}
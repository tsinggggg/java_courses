import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] list;
    private int n;
    // construct an empty randomized queue
    public RandomizedQueue(){
        list = (Item[]) new Object[0];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return n;
    }

    private void make_new_array(int len){
        Item[] new_array = (Item[]) new Object[len];
        for (int i=0;i<n;i++){
            new_array[i] = list[i];
        }
        list = new_array;

    }

    // add the item
    public void enqueue(Item item){
        if (item == null){
            throw new IllegalArgumentException();
        }
        if (n == list.length){
            make_new_array(2 * list.length + 1);
        }
        list[n++] = item;
    }
    private int randint(int max, int min){
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }
    // remove and return a random item
    public Item dequeue(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        int i = randint(n-1,0);
        Item temp = list[i];
        list[i] = list[--n];

        if (n == list.length / 4){
            make_new_array(2 * n);
        }
        return temp;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        int i = randint(n-1,0);
        return list[i];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item>{
        private int curr_n = n;
        private Item[] iter;

        public RandomIterator(){
            iter = (Item[]) new Object[curr_n];

            for (int i=0;i<curr_n;i++){
                iter[i] = list[i];
            }
        }

        public boolean hasNext() {
            return curr_n != 0;
        }

        @Override
        public Item next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            int i = randint(curr_n-1,0);
            Item ret = iter[i];
            iter[i] = iter[--curr_n];
            return ret;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<Integer> l = new RandomizedQueue<>();
        for(int i=0;i<10;i++){
            l.enqueue(i);
            System.out.println(l.size());
            System.out.println(l.isEmpty());
            System.out.println(l.sample());
        }
        System.out.println("test iter");
        for(int i=0;i<2;i++){
            for (Integer j : l){
                System.out.println(j);
            }
            System.out.println("****************");
        }
        System.out.println("test dequeue");
        for(int i=0;i<10;i++){
            System.out.println(l.dequeue());

        }

    }

}
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args){
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> q = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            q.enqueue(item);
        }
        int c = 0;
        for(String temp: q){
            if (c < k){
                System.out.println(temp);
                c += 1;
            }
        }
    }
}
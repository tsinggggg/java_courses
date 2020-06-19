import edu.duke.*;
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {

    public int len(String w){
        int count = w.length();
        char first = w.charAt(0);
        char last = w.charAt(w.length()-1);
        if ( Character.isLetter(first)==false){
            count = count - 1;
        }
        if (w.length() > 1){
        if (Character.isLetter(last)==false){
            count = count - 1;
        }
        }
        return count;
    }
    
    public void countWordLengths(FileResource resource, int[] counts){
        for (String w: resource.words()){
            int temp = len(w);
            temp = Math.min(temp, counts.length - 1);
            counts[temp] += 1; 
        }
    }
    
    
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        for (int i=0;i<counts.length;i++){
            System.out.println(i + "   " + counts[i]);
        }
        System.out.println(indexOfMax(counts));
    }
    
    public int indexOfMax (int[] values){
        int ret = 0;
        int temp = values[0];
        for (int i=0;i<values.length;i++){
            if (values[i]>temp){
                ret = i;
                temp = values[i];
            }
        }
        return ret;
    }
}

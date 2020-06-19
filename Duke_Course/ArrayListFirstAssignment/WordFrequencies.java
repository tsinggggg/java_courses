 

import java.util.*;
import edu.duke.*;
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequencies {
    
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        
        for (String word: fr.words()){
            int ind = myWords.indexOf(word.toLowerCase());
            if (ind == -1){
                myWords.add(word.toLowerCase());
                myFreqs.add(1);
            }
            else{
                int count = myFreqs.get(ind);
                myFreqs.set(ind, count+1);
            }
        }
        System.out.println("unique: " + myFreqs.size());
    }
    
    public int findIndexOfMax(ArrayList<Integer> a){
        
        int ind = -1;
        if (a.size() == 0){return -1;}
        int temp = a.get(0);
        
        for (int i=0;i<a.size();i++){
            if (a.get(i)>temp){
                temp = a.get(i);
                ind = i;
            }
        }
        
        return ind;
    }
    
    public void tester(){
        findUnique();
        for (int i=0;i<myWords.size();i++){
            System.out.println(myWords.get(i)+"  "+myFreqs.get(i));
        }
        int maxind = findIndexOfMax(myFreqs);
        System.out.println(myFreqs.get(maxind) + myWords.get(maxind));
    }
    
    
}


/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            //System.out.println(key);
            //System.out.println(follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        //for (int i=0;i<myText.length-1;i++){
        //    if (myText[i].equals(key)){
        //        follows.add(myText[i+1]);
        //    }
        //}
        int ind = indexOf(myText, key, 0);
        while(ind!=-1){
            if(ind<=myText.length - 2){
                follows.add(myText[ind+1]);
                ind = indexOf(myText, key, ind + 1);
            }
           
        }
        return follows;
    }
    
    private int indexOf(String[] words, String target, int start){
        for (int i=start;i<words.length;i++){
            if (words[i].equals(target)){
                return i;
            }
            
        }
        return -1;
    }
    
    public void testIndexOf(){
    String[] a = "this is just a test yes this is a simple test".split("\\s+");
    System.out.println(indexOf(a,"this",0));
    System.out.println(indexOf(a,"this",3));
    System.out.println(indexOf(a,"frog",0));
    System.out.println(indexOf(a,"frog",5));
    System.out.println(indexOf(a,"simple",2));
    System.out.println(indexOf(a,"test",5));
    }
}

import java.util.*;
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order){
        myOrder = order;
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
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key.toString());
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
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
            key = key.shiftAdd(next) ;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        //for (int i=0;i<myText.length-1;i++){
        //    if (myText[i].equals(key)){
        //        follows.add(myText[i+1]);
        //    }
        //}
        int ind = indexOf(myText, kGram, 0);
        while(ind!=-1){
            if(ind<myText.length - kGram.length()){
                follows.add(myText[ind+kGram.length()]);
                ind = indexOf(myText, kGram, ind + 1);
            }
           
        }
        return follows;
    }
    
    private int indexOf(String[] words, WordGram target, int start){
        for (int i=start;i< (words.length - target.length()) ;i++){
            int same = 1;
            for(int j=0;j<target.length();j++){
                if(!target.wordAt(j).equals(words[i+j])){
                    same = 0;
                    break;
                }
            }
            if (same == 1){
                return i;
            }
        }
        return -1;
    }
}

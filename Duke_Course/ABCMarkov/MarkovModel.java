import java.util.*;
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovModel extends AbstractMarkovModel {

    private int n;
    
    public MarkovModel(int i) {
        myRandom = new Random();
        n = i;
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        int f_ind = myRandom.nextInt(myText.length()-n);
        StringBuilder sb = new StringBuilder();
        sb.append(myText.substring(f_ind,f_ind+n));
        for(int k=0; k < numChars-n; k++){
            ArrayList<String> l = getFollows(sb.substring(k,k+n));
            int index = myRandom.nextInt(l.size());
            sb.append(l.get(index));
        }
        
        return sb.toString();
    }
    
    public String toString(){
    System.out.println(n);
    return Integer.toString(n);
    }
}

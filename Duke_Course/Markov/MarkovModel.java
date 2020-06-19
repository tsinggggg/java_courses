import java.util.*;
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int n;
    
    public MarkovModel(int i) {
        myRandom = new Random();
        n = i;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
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
    
    public ArrayList<String> getFollows(String key){
        ArrayList<String> ret = new ArrayList<String>();
        int pos = 0;
        while (true) {
            int temp = myText.indexOf(key, pos);
            pos = temp + key.length();
            if (temp == -1 || (pos == myText.length())){
                break;
            }
            else{
                ret.add(myText.substring(pos, pos+1));
            }
        }
        return ret;
    }
}

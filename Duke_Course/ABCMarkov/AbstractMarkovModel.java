
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    abstract public String getRandomText(int numChars);
    abstract public String toString();

    protected ArrayList<String> getFollows(String key){
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

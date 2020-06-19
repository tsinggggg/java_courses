import java.util.*;
/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovFour extends AbstractMarkovModel{

    public MarkovFour() {
        myRandom = new Random();
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        int f_ind = myRandom.nextInt(myText.length()-4);
        StringBuilder sb = new StringBuilder();
        sb.append(myText.substring(f_ind,f_ind+4));
        for(int k=0; k < numChars-4; k++){
            ArrayList<String> l = getFollows(sb.substring(k,k+4));
            int index = myRandom.nextInt(l.size());
            sb.append(l.get(index));
        }
        
        return sb.toString();
    }
    public String toString(){
         System.out.println(4);
    return "4";
    }
}

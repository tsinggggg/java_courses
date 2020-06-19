import java.util.*;
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EfficientMarkovWord implements IMarkovModel  {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram,ArrayList<String>> map;
    
    public EfficientMarkovWord(int order){
        myOrder = order;
        myRandom = new Random();
        map = new HashMap<WordGram,ArrayList<String>>();
    }
    
    public void buildMap(){
        for (int i=0;i<myText.length-myOrder;i++){
            WordGram temp = new WordGram(myText,i,myOrder);

            String tempword = myText[i+myOrder];

            if(map.containsKey(temp)){
                ArrayList<String> al = map.get(temp);
                al.add(tempword);
                map.put(temp,al);
            }
            else{
                ArrayList<String> l = new ArrayList<String>();
                l.add(tempword);
                map.put(temp,l);
            }
        }
    
    }
    public void printHashMapInfo(){
        System.out.println("n keys " + map.size());
        ArrayList<WordGram> wgl = new ArrayList<WordGram>();
        //WordGram wg = new WordGram(myText,0,myOrder);
        int c = 0;
        
        if (map.size()<=25000000){
            for(WordGram k:map.keySet()){
                System.out.println(k);
                System.out.println(map.get(k));
                int len = map.get(k).size();
                if(len>c){
                    c = len;
                    wgl = new ArrayList<WordGram>();
                    wgl.add(k);
                    //wg = k;
                }
                if(len==c && !wgl.contains(k)){
                    wgl.add(k);
                }
               
            }
        }
        
        System.out.println(wgl);
        System.out.println(c);
    
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
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
        if (map.keySet().contains(kGram)){
            return map.get(kGram);
        }
        else{
            return new ArrayList<String>();
        }
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

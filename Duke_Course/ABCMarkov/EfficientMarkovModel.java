import java.util.*;
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EfficientMarkovModel extends AbstractMarkovModel{
    private int n;
    private HashMap<String,ArrayList<String>> map;
    
    public EfficientMarkovModel(int i) {
        myRandom = new Random();
        n = i;
        map = new HashMap<String,ArrayList<String>>();
    }
    
    public void setTraining(String s){
        myText = s.trim();
        buildMap();
        printHashMapInfo();
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
            if (l.size()==0){break;}
            int index = myRandom.nextInt(l.size());
            sb.append(l.get(index));
        }
        
        return sb.toString();
    }
    public void buildMap(){
        for (int i=0;i<myText.length()-n;i++){
            String key = myText.substring(i,i+n);
            String nextItem = myText.substring(i+n,i+n+1);
            if (map.containsKey(key)){
                map.get(key).add(nextItem);
            }
            else{
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(nextItem);
                map.put(key,temp);
            }
            
        }
    
    }

    protected ArrayList<String> getFollows(String key){
        ArrayList<String> ret = new ArrayList<String>();
        if (map.containsKey(key)){
            return map.get(key);
        }
        else{
             return new ArrayList<String>();
        }
    }    
    public String toString(){
    System.out.println("eff" + n);
    return "eff" + Integer.toString(n);
    }
    
    public void printHashMapInfo(){
        System.out.println(map.size());
        if (map.size()<=250000){
            int c=0;
            ArrayList<String> k= new ArrayList<String>();
            for(String key:map.keySet()){
                ArrayList<String> a = map.get(key);
                //System.out.println(key);
                //System.out.println(a.size());
                //System.out.println(a);
                if(a.size()>c){
                    c = a.size();
                    k= new ArrayList<String>();
                    k.add(key);
                }
                if(a.size()==c){
                    if (!k.contains(key)){
                    k.add(key);}
                }              

            }
            System.out.println(k);    
            System.out.println(c);   
        }
    }
}

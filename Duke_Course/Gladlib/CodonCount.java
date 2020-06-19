import edu.duke.*;
import java.util.*;
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CodonCount {
    private HashMap<String,Integer> map;
    
    public CodonCount(){
        map = new HashMap<String,Integer>();
    }
    
    public void buildCodonMap(int start, String dna){
        map = new HashMap<String,Integer>();
        for (int ind = start;ind+3<=dna.length();ind = ind+3){
            String temp = dna.substring(ind,ind+3);
            if (map.containsKey(temp)){
                map.put(temp, map.get(temp)+1);
            }
            else{
                map.put(temp,1);
            }
        }
    }
    
    public String getMostCommonCodon(){
        String temp = "";
        int count = -1;
        for (String key:map.keySet()){
            int c = map.get(key);
            if (c>count){
                temp = key;
                count = c;
            }
        }
        return temp;
    }
    
    public void printCodonCounts(int start, int end){
        for (String key:map.keySet()){
            int c = map.get(key);
            if (c>=start && c<=end){
                System.out.println(key+"\t"+c);
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase().trim();
        for (int i=0;i<=2;i++){
            buildCodonMap(i, dna);
            
            int count = 0;
            for(String key:map.keySet()){
                count = count+1;
            }
            System.out.println(count);
            
            String common = getMostCommonCodon();
            int c = map.get(common);
            System.out.println(common + "\t" + c);
            
            printCodonCounts(7, 7);
        }
    }
}

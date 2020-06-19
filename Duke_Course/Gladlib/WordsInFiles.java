import edu.duke.*;
import java.util.*;
import java.io.*;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {

    private HashMap<String,ArrayList<String>> filenames;
    private HashMap<String,Integer> wc;
    
    public WordsInFiles(){
        filenames = new HashMap<String,ArrayList<String>>();
        wc = new HashMap<String,Integer>();
    }
    
    private void addWordsFromFile(File f){
        String fname = f.getName();
        FileResource fr = new FileResource(f);
        for (String word:fr.words()){
            if (filenames.containsKey(word)==false){
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(fname);
                filenames.put(word, temp);
                wc.put(word, 1);
            }
            else{
                ArrayList<String> temp = filenames.get(word);
                if (temp.indexOf(fname)==-1){
                    temp.add(fname);
                }
                wc.put(word, wc.get(word)+1);
            }
        
        }
    }
    
    public void buildWordFileMap(){
        filenames = new HashMap<String,ArrayList<String>>();
        DirectoryResource dr = new DirectoryResource();
        for (File f:dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        int count = 0;
        for(String word:filenames.keySet()){
            int temp = filenames.get(word).size();
            if (temp>count){
                count = temp;
            }
        }
        return count;
    }
    
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> temp = new ArrayList<String>();
        for (String word:filenames.keySet()){
            if(filenames.get(word).size() == number){
                temp.add(word);
            }
        }
        return temp;
    }
    
    public void printFilesIn(String word){
        ArrayList<String> temp = filenames.get(word);
        for (String name:temp){
            System.out.println(name);
        }
    }
    
    public void tester(){
        buildWordFileMap();
        //int maxnum = maxNumber();
        //System.out.println("max num of files word is in "+maxnum);
        ArrayList<String> temp = wordsInNumFiles(4);
        System.out.println(temp.size());
        //for(String word:temp){
            //System.out.println(word);
            //System.out.println(filenames.get(word));
        //}
    }
    
    public void q4(){
        buildWordFileMap();
        ArrayList<String> temp = wordsInNumFiles(5);
        System.out.println(temp.size());

    }
    
    public void q5(){
        buildWordFileMap();
        ArrayList<String> temp = wordsInNumFiles(4);
        System.out.println(temp.size());

    }    
   
    
    public void quiz_q14(){
        buildWordFileMap();
        printFilesIn("laid");
    }  
    
    public void quiz_q15(){
        buildWordFileMap();
        printFilesIn("tree");
    }  
}

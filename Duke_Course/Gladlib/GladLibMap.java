import edu.duke.*;
import java.util.*;

public class GladLibMap {

    private ArrayList<String> used;
    private ArrayList<String> used_category;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    private HashMap<String,ArrayList<String>> myMap;
    
    public GladLibMap(){
        myMap = new HashMap<String,ArrayList<String>>();
        used = new ArrayList<String>();
        used_category = new ArrayList<String>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String,ArrayList<String>>();
        used = new ArrayList<String>();
        used_category = new ArrayList<String>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        
        String[] items = {"adjective","noun","color","country",
                          "name","animal","timeframe","verb",
                          "fruit"};
        for (String s:items){
            myMap.put(s, readIt(source+"/" + s + ".txt"));
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        if (source.size()==0){return "**RAN out of words**";}
        int index = myRandom.nextInt(source.size());
        String temp = source.get(index);
        used.add(temp);
        source.remove(index);
        return temp;
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if (myMap.containsKey(label)){
            ArrayList<String> temp = myMap.get(label);
            used_category.add(label);
            return randomFrom(temp);
        }
        else{
        return "**UNKNOWN**";
        }
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
        System.out.println(used.size());
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        System.out.println("remaining words " + totalWordsInMap());
        printOut(story, 60);
    }
    
    public int totalWordsInMap(){
        int count = 0;
        for(String key:myMap.keySet()){
            count = count + myMap.get(key).size();
        }
        return count;
    }
    
    public int totalWordsConsidered(){
        int count = 0;
        for (String c:used_category){
            count = count + myMap.get(c).size();
        }
        return count;
    }

}

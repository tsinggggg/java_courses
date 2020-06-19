import java.util.*;
import edu.duke.*;
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    private ArrayList<String> name;
    private ArrayList<Integer> count;
    
    public CharactersInPlay(){
        name = new ArrayList<String>();
        count = new ArrayList<Integer>();
    }
    
    public void update (String person){
        int ind = name.indexOf(person);
        if (ind == -1){
            name.add(person);
            count.add(1);
        }
        else{
            int c = count.get(ind);
            count.set(ind, c + 1);
        }
    }
    
    public void findAllCharacters(){
        FileResource fr = new FileResource();
        for (String line:fr.lines()){
           int ind = line.indexOf(".");
           if(ind!=-1){
               String temp_name = line.substring(0,ind);
               update(temp_name);
           }
        }
    }
    public void charactersWithNumParts(int num1, int num2){
        for (int i=0;i<name.size();i++){
            int c = count.get(i);
            if (c>=num1 && c<=num2){
                System.out.println(name.get(i) + ":"+c);
            }
        }
    }
    public void tester (){
        findAllCharacters();
        for (int i=0;i<name.size();i++){
            int c = count.get(i);
            if (c>1){
                System.out.println(name.get(i) + ":"+c);
            }
        }
    }
    
    public void quiz_q8 (){
        findAllCharacters();
        charactersWithNumParts(10,15);
    }
}

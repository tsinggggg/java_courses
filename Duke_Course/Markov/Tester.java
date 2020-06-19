
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class Tester {

    public void testGetFollows(){
        MarkovOne m = new MarkovOne();
        m.setTraining("this is a test yes this is a test.");
        ArrayList<String> l = m.getFollows("t.");
        System.out.println(l.size());
        for (String s:l){
            System.out.println(s);
        }
    }
    
    public void testGetFollowsWithFile(){
        MarkovOne m = new MarkovOne();
        FileResource fr = new FileResource();
        m.setTraining(fr.asString());
        ArrayList<String> l = m.getFollows("he");
        System.out.println(l.size());
        //for (String s:l){
           // System.out.println(s);
       // }
    }    
}

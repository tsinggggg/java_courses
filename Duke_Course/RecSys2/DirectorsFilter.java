import java.util.*;
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {
    private ArrayList<String> dir;
    
    public DirectorsFilter(String input){
        dir = new ArrayList<String>(Arrays.asList(input.split(",")));
    }
    @Override
    public boolean satisfies(String id){
        String temp = MovieDatabase.getDirector(id);
        for(String d:dir){
            if (temp.contains(d)){
                return true;
            }
        }
        return false;
    }

}

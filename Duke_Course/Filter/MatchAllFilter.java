import java.util.*;
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MatchAllFilter implements Filter{
    
    private ArrayList<Filter> f = new ArrayList<Filter>();
    
    public void addFilter(Filter temp){
        f.add(temp);
    }
    
    public boolean satisfies(QuakeEntry qe) { 
        for(Filter tempf:f){
            if(!tempf.satisfies(qe)){return false;}
        }
        return true;
    
    }
    
    public String getName(){
        String ret = "";
        
        for(Filter tempf:f){
            ret = ret + tempf.getName()+" ";
        }
        return ret;
    }

}

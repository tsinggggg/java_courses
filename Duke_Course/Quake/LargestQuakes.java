import java.util.*;
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LargestQuakes {
    
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
        System.out.println(getLargest(list, 50));
        
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        int ind = -1;
        double m = 0.0f;
        for(int i=0;i<data.size();i++){
            QuakeEntry qe = data.get(i);
            double tempm = qe.getMagnitude();
            if (tempm > m){
                ind = i;
                m = tempm;
            }
        }
        return ind;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
     ArrayList<QuakeEntry> ret  = new ArrayList<QuakeEntry>();
     ArrayList<QuakeEntry> copy  = new ArrayList<QuakeEntry>(quakeData);
     
     for(int i=1;i<=howMany;i++){
         int ind = indexOfLargest(copy);
         ret.add(copy.get(ind));
         copy.remove(ind);
        }
     return ret;
    
    }
}

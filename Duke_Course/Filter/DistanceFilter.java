
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{
    private Location l;
    private double maxD;
    
    public DistanceFilter(Location location, double max) { 
        l = location;
        maxD = max;
    } 
    
    public boolean satisfies(QuakeEntry qe) { 
        float temp = qe.getLocation().distanceTo(l);
        return temp <= maxD; 
    } 
    
    public String getName(){
        return "Distance";
    }    
}

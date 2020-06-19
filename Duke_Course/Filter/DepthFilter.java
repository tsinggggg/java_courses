
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter {
    private double maxD; 
    private double minD; 
    
    public DepthFilter(double min, double max) { 
        maxD = max;
        minD = min;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getDepth() >= minD && qe.getDepth() <= maxD; 
    } 
    
    public String getName(){
        return "Depth";
    }
}

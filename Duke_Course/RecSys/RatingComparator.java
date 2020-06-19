import java.util.*;
/**
 * Write a description of RatingComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RatingComparator implements Comparator<Rating> {
    public int compare(Rating a,Rating b){
        return Double.compare(a.getValue(),b.getValue());
    }

}

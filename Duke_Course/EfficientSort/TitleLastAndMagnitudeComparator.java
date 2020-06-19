
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>  {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String s1 = qe1.getInfo();
        String s2 = qe2.getInfo();
        String w1 = s1.substring(s1.lastIndexOf(" ")+1);
        String w2 = s2.substring(s2.lastIndexOf(" ")+1);
        
        int ret1 = w1.compareTo(w2);
        if (ret1 != 0){return ret1;}
        else{
            return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
        }
        
    }
}

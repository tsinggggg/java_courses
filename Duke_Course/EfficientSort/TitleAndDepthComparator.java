
/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2){
        String s1 = qe1.getInfo();
        String s2 = qe2.getInfo();
        int ret1 = s1.compareTo(s2);
        if (ret1 != 0){return ret1;}
        else{
            return Double.compare(qe1.getDepth(), qe2.getDepth());
        }
    }
}

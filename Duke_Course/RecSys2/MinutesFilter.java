
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter{
    private int max;
    private int min;
    
    public MinutesFilter(int a, int b){
        max = a;
        min = b;
    }
    @Override
    public boolean satisfies(String id){
        return (MovieDatabase.getMinutes(id)>=min) && (MovieDatabase.getMinutes(id)<=max);
    }

}

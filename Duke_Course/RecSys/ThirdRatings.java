import java.util.*;
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ThirdRatings {

    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile){
        
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
    
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters){
        int c = 0;
        double sum = 0.0;
        for(Rater r:myRaters){
            double temp = r.getRating(id);
            if(temp != -1){
                sum += temp;
                c += 1;
            }
        }
        if (c >= minimalRaters){
            return sum/(double) c;
        }
        else{
            return 0.0;
        }
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> ret = new ArrayList<Rating>();
        for (String m:movies){
            double temp = getAverageByID(m,minimalRaters);
            if (temp > 0){
                ret.add(new Rating(m, temp));
            }
        }
        return ret;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> ret = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String m:movies){
            double temp = getAverageByID(m,minimalRaters);
            if (temp > 0){
                ret.add(new Rating(m, temp));
            }
        }
        return ret;
    }
}

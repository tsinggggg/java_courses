
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile){
        
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    
    }
    
    public int getMovieSize(){
        return myMovies.size();
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
        ArrayList<Rating> ret = new ArrayList<Rating>();
        for (Movie m:myMovies){
            String id = m.getID();
            double temp = getAverageByID(id,minimalRaters);
            if (temp > 0){
                ret.add(new Rating(id, temp));
            }
        }
        return ret;
    }
    
    public String getTitle(String id){
        for(Movie m:myMovies){
            if (m.getID().equals(id)){
                return m.getTitle();
            }
        }
        return "NOT FOUND";
    }
    
    public String getID(String title){
        for(Movie m:myMovies){
            if (m.getTitle().equals(title)){
                return m.getID();
            }
        }
        return "NOT FOUND";
    }
}
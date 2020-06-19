import java.util.*;

/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerAverage {
    public void printAverageRatings(){
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        System.out.println(sr.getMovieSize());
        System.out.println(sr.getRaterSize());
    
        ArrayList<Rating> l = sr.getAverageRatings(12);
        System.out.println(l.size());
        Comparator compareRate = new RatingComparator(); 
        Collections.sort(l,compareRate);
        for(Rating r: l){
            System.out.println(Double.toString(r.getValue()) + sr.getTitle(r.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie(){
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        ArrayList<Rating> l = sr.getAverageRatings(1);
        for (Rating r : l){
            if(sr.getTitle(r.getItem()).equals("Vacation")){
                System.out.println(r.getValue());
            }
        }
        
    }
}

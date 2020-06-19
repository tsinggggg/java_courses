import java.util.*;
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println(RaterDatabase.getRaters().size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.out.println(movies.size());
    
        ArrayList<Rating> l = fr.getAverageRatings(35);
        System.out.println(l.size());
        
        Comparator compareRate = new RatingComparator(); 
        Collections.sort(l,compareRate);
        for(Rating r: l){
            System.out.println(Double.toString(r.getValue()) + MovieDatabase.getTitle(r.getItem()));
        }
    }
    public void printAverageRatingsByYearAfterAndGenre(){
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println(RaterDatabase.getRaters().size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.out.println(movies.size());
        
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(1990));
        f.addFilter(new GenreFilter("Drama"));

        ArrayList<Rating> l = fr.getAverageRatingsByFilter(8,f);
        System.out.println(l.size());
        
        Comparator compareRate = new RatingComparator(); 
        Collections.sort(l,compareRate);
        for(Rating r: l){
            System.out.println(Double.toString(r.getValue()) + MovieDatabase.getTitle(r.getItem()) + MovieDatabase.getDirector(r.getItem()) + Integer.toString(MovieDatabase.getMinutes(r.getItem())));
        }        
    }    
    
    public void printSimilarRatings(){
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println(RaterDatabase.getRaters().size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.out.println(movies.size());
        
        ArrayList<Rating> ret = fr.getSimilarRatings("71",20,5);
        for(Rating r:ret){
            System.out.println(MovieDatabase.getTitle(r.getItem()) + Double.toString(r.getValue()));
        }
        
    }
    
    
    public void printSimilarRatingsByGenre(){
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println(RaterDatabase.getRaters().size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.out.println(movies.size());
        
        ArrayList<Rating> ret = fr.getSimilarRatingsByFilter("964",20,5, new GenreFilter("Mystery"));
        for(Rating r:ret){
            System.out.println(MovieDatabase.getTitle(r.getItem()) + Double.toString(r.getValue()));
        }
    }
    
    public void printSimilarRatingsByDirector(){
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println(RaterDatabase.getRaters().size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.out.println(movies.size());
        
        ArrayList<Rating> ret = fr.getSimilarRatingsByFilter("120",10,2, new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
        for(Rating r:ret){
            System.out.println(MovieDatabase.getTitle(r.getItem()) + Double.toString(r.getValue()));
        }
    }   
    
    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println(RaterDatabase.getRaters().size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.out.println(movies.size());
        
        AllFilters f = new AllFilters();
        f.addFilter(new GenreFilter("Drama"));
        f.addFilter(new MinutesFilter(160,80));
        
        ArrayList<Rating> ret = fr.getSimilarRatingsByFilter("168", 10, 3, f);
        for(Rating r:ret){
            System.out.println(MovieDatabase.getTitle(r.getItem()) + Double.toString(r.getValue()));
        }
    }      
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println(RaterDatabase.getRaters().size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.out.println(movies.size());
        
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(1975));
        f.addFilter(new MinutesFilter(200,70));
        
        ArrayList<Rating> ret = fr.getSimilarRatingsByFilter("314", 10, 5, f);
        for(Rating r:ret){
            System.out.println(MovieDatabase.getTitle(r.getItem()) + Double.toString(r.getValue()));
        }
    }          
}

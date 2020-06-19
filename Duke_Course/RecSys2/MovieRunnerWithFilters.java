import java.util.*;
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        System.out.println(tr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.out.println(movies.size());
    
        ArrayList<Rating> l = tr.getAverageRatings(35);
        System.out.println(l.size());
        
        Comparator compareRate = new RatingComparator(); 
        Collections.sort(l,compareRate);
        for(Rating r: l){
            System.out.println(Double.toString(r.getValue()) + MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYear(){
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        System.out.println(tr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.out.println(movies.size());
    
        ArrayList<Rating> l = tr.getAverageRatingsByFilter(20,new YearAfterFilter(2000));
        System.out.println(l.size());
        
        Comparator compareRate = new RatingComparator(); 
        Collections.sort(l,compareRate);
        for(Rating r: l){
            System.out.println(Double.toString(r.getValue()) + MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre(){
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        System.out.println(tr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.out.println(movies.size());
    
        ArrayList<Rating> l = tr.getAverageRatingsByFilter(20,new GenreFilter("Comedy"));
        System.out.println(l.size());
        
        Comparator compareRate = new RatingComparator(); 
        Collections.sort(l,compareRate);
        for(Rating r: l){
            System.out.println(Double.toString(r.getValue()) + MovieDatabase.getTitle(r.getItem()) + MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes(){
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        System.out.println(tr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.out.println(movies.size());
    
        ArrayList<Rating> l = tr.getAverageRatingsByFilter(5,new MinutesFilter(135,105));
        System.out.println(l.size());
        
        Comparator compareRate = new RatingComparator(); 
        Collections.sort(l,compareRate);
        for(Rating r: l){
            System.out.println(Double.toString(r.getValue()) + MovieDatabase.getTitle(r.getItem()) + MovieDatabase.getGenres(r.getItem()));
        }
    }  

    public void printAverageRatingsByDirectors(){
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        System.out.println(tr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.out.println(movies.size());
    
        ArrayList<Rating> l = tr.getAverageRatingsByFilter(4,new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
        System.out.println(l.size());
        
        Comparator compareRate = new RatingComparator(); 
        Collections.sort(l,compareRate);
        for(Rating r: l){
            System.out.println(Double.toString(r.getValue()) + MovieDatabase.getTitle(r.getItem()) + MovieDatabase.getDirector(r.getItem()));
        }
    }    
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        System.out.println(tr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.out.println(movies.size());
        
        AllFilters f = new AllFilters();
        f.addFilter(new MinutesFilter(180,90));
        f.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));

        ArrayList<Rating> l = tr.getAverageRatingsByFilter(3,f);
        System.out.println(l.size());
        
        Comparator compareRate = new RatingComparator(); 
        Collections.sort(l,compareRate);
        for(Rating r: l){
            System.out.println(Double.toString(r.getValue()) + MovieDatabase.getTitle(r.getItem()) + MovieDatabase.getDirector(r.getItem()) + Integer.toString(MovieDatabase.getMinutes(r.getItem())));
        }        
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        System.out.println(tr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.out.println(movies.size());
        
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(1990));
        f.addFilter(new GenreFilter("Drama"));

        ArrayList<Rating> l = tr.getAverageRatingsByFilter(8,f);
        System.out.println(l.size());
        
        Comparator compareRate = new RatingComparator(); 
        Collections.sort(l,compareRate);
        for(Rating r: l){
            System.out.println(Double.toString(r.getValue()) + MovieDatabase.getTitle(r.getItem()) + MovieDatabase.getDirector(r.getItem()) + Integer.toString(MovieDatabase.getMinutes(r.getItem())));
        }        
    }    
}

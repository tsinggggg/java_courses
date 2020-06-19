import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> ret = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record:parser){
            ret.add(new Movie(record.get("id"), record.get("title"), record.get("year"), record.get("genre"),record.get("director"),
                              record.get("country"), record.get("poster"), Integer.parseInt(record.get("minutes"))));
        }
        return ret;
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater> ret = new ArrayList<Rater>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        HashMap<String, Rater> raterset = new HashMap<String, Rater>();
        for (CSVRecord record:parser){
            String id = record.get("rater_id");
            if(!raterset.containsKey(id)){
                Rater temp = new EfficientRater(id);
                temp.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                ret.add(temp);
                raterset.put(id,temp);
            }
            else{
                raterset.get(id).addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
            }
        }
        return ret;
    } 
    
    public void testLoadRaters(){
        ArrayList<Rater> l = loadRaters("data/ratings.csv");
        System.out.println(l.size());
        //for(Rater r:l){
        //    System.out.println(r.getID() + Integer.toString(r.numRatings()));
        //    for (String item:r.getItemsRated()){
        //         System.out.println(item + Double.toString(r.getRating(item)) );
        //    }
        //}
        for(Rater r:l){
            if (r.getID().equals("193")){
                System.out.println(r.getItemsRated().size());
            }
        }
        
        int c = 0;
        ArrayList<String> id = new ArrayList<String>();
        for(Rater r:l){
            int temp = r.getItemsRated().size();
            if (temp > c){
                c = temp;
                id = new ArrayList<String>();
                id.add(r.getID());
            }
            if (temp == c && !id.contains(r.getID())){
                id.add(r.getID());
            }
        }
        System.out.println(c);
        System.out.println(id.size());
        System.out.println(id);
        
        int rate_c = 0;
        for (Rater r:l){
            if(r.getRating("1798709") != -1){
                rate_c += 1;
            }
        }
        System.out.println(rate_c);
        
        HashSet<String> s = new HashSet<String>();
        for (Rater r:l){
            for(String temp:r.getItemsRated()){
                s.add(temp);
            }
        }
        System.out.println(s.size());
    }
    
    public void testLoadMovies(){
        ArrayList<Movie> l = loadMovies("data/ratedmoviesfull.csv");
        System.out.println(l.size());
        int comedy = 0;
        int c150 = 0;
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        for(Movie m:l){
            if(m.getGenres().contains("Comedy")){
                comedy += 1;
            }
            if(m.getMinutes()>150){
                c150 += 1;
            }
            String d = m.getDirector();
            for(String tempD:d.split(",")){
                if(!map.containsKey(tempD)){
                    map.put(tempD,1);
                }
                else{
                    map.put(tempD,map.get(tempD)+1);
                }
            }
            //System.out.println(m);
        }
        System.out.println(comedy);
        System.out.println(c150);
        int maxd = 0;
        ArrayList<String> dl = new ArrayList<String>();
        for(String tempD:map.keySet()){
            int temp = map.get(tempD);
            if(temp>maxd){
                maxd = temp;
                dl = new ArrayList<String>();
                dl.add(tempD);
            }
            if(temp==maxd && !dl.contains(tempD)){
                dl.add(tempD);
            }
        }
        System.out.println(maxd);
        System.out.println(dl);
    }
}

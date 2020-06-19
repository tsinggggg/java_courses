import java.util.*;
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RecommendationRunner implements Recommender {

    public ArrayList<String> getItemsToRate (){
        ArrayList<String> ret = new ArrayList<String>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        int s = movies.size();
        Random rand = new Random();
        
        HashSet<Integer> set = new HashSet<Integer>();
        while (ret.size()<15){
            int i = rand.nextInt(s);
            if(!set.contains(i)){
                ret.add(movies.get(i));
                set.add(i);
            }
            
        }
        //System.out.println(ret);
        return ret;
    }
    
    
    
    public void printRecommendationsFor (String webRaterID){
        FourthRatings fr = new FourthRatings();
        //RaterDatabase.initialize("ratings.csv");
        //System.out.println(RaterDatabase.getRaters().size());
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        //System.out.println(movies.size());
        ArrayList<Rating> ret = fr.getSimilarRatings(webRaterID, 10, 3);
        int c = 0;
        if (ret.size()>0){
        for (Rating m:ret){
            if (c<15){
            System.out.println(MovieDatabase.getTitle(m.getItem())+"<br>");
            c += 1;
            }
        }
        }
        else{
        for (String m:movies){
            if (c<15){
            System.out.println(MovieDatabase.getTitle(m)+"<br>");
            c += 1;
            }
        }
        }
    }

}

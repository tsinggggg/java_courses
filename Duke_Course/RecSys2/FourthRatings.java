import java.util.*;
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FourthRatings {
    private double getAverageByID(String id, int minimalRaters){
        int c = 0;
        double sum = 0.0;
        RaterDatabase.initialize("data/ratings_short.csv");
        for(Rater r:RaterDatabase.getRaters()){
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
    
    private double dotProduct(Rater me, Rater r){
        ArrayList<String> a = me.getItemsRated();
        ArrayList<String> b = r.getItemsRated();
        double sum = 0;
        for (String m:a){
            if(b.contains(m)){
                sum += (me.getRating(m)-5) * (r.getRating(m)-5);
            }
        }
        return sum;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> ret = new ArrayList<Rating>();
        ArrayList<Rater> rl = RaterDatabase.getRaters();
        Rater me = RaterDatabase.getRater(id);
        for (Rater r:rl){
            if(!r.getID().equals(id)){
                double temp = dotProduct(me, r);
                if(temp>0){
                    ret.add(new Rating(r.getID(), temp));
                }
            }
        }
        Collections.sort(ret, Collections.reverseOrder());
        return ret;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, 
    int numSimilarRaters, int minimalRaters){
        // ArrayList<Rating> ret = new ArrayList<Rating>();
        // ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        // ArrayList<Rating> sim_score = getSimilarities(id);
        // System.out.println(sim_score.size());
        // ArrayList<Rater> all_raters = RaterDatabase.getRaters();
        
        // for (String m:movies){
            // double score = 0.0;
            // double weight = 0.0;
            // int count = 0;
            
            // for(int i=0;i<numSimilarRaters;i++){
                // if (i<sim_score.size()){
                    // Rater temp_rater = RaterDatabase.getRater(sim_score.get(i).getItem());
                    // if(temp_rater.hasRating(m)){
                        // double temp_score = temp_rater.getRating(m);
                        // weight += sim_score.get(i).getValue();
                        // score += sim_score.get(i).getValue() * temp_score;
                        // count += 1;
                    // }
                // }
            // }
            // if (count >= minimalRaters){
                // ret.add(new Rating(m,score/count));
            // }
            
        // }
        
        // Collections.sort(ret,Collections.reverseOrder());
        return getSimilarRatingsByFilter(id, 
               numSimilarRaters, minimalRaters, new TrueFilter());
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, 
    int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> ret = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> sim_score = getSimilarities(id);
        ArrayList<Rater> all_raters = RaterDatabase.getRaters();
        
        for (String m:movies){
            double score = 0.0;
            double weight = 0.0;
            int count = 0;
            
            for(int i=0;i<numSimilarRaters;i++){
                if (i<sim_score.size()){
                    Rater temp_rater = RaterDatabase.getRater(sim_score.get(i).getItem());
                    if(temp_rater.hasRating(m)){
                        double temp_score = temp_rater.getRating(m);
                        weight += sim_score.get(i).getValue();
                        score += sim_score.get(i).getValue() * temp_score;
                        count += 1;
                    }
                }
            }
            if (count >= minimalRaters){
                ret.add(new Rating(m,score/count));
            }
            
        }
        
        Collections.sort(ret,Collections.reverseOrder());
        return ret;
    }
    
}


/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from){
        int ind = from;
        double temp = quakes.get(from).getDepth();
        for(int i=from;i<quakes.size();i++){
            double d = quakes.get(i).getDepth();
            if (d>temp){
                ind = i;
                temp = d;
            }
        }
        return ind;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
        //for (int i =0;i<in.size();i++){
        for (int i =0;i<70;i++){    
            int maxind = getLargestDepth(in, i);
            QuakeEntry q1 = in.get(i);
            QuakeEntry q2 = in.get(maxind);
            in.set(i,q2);
            in.set(maxind,q1);
            
        }
    
    }
    

    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        for (int i=0;i<quakeData.size()-numSorted-1;i++){
            if (quakeData.get(i).getMagnitude() > quakeData.get(i+1).getMagnitude()){
                QuakeEntry q1 = quakeData.get(i);
                QuakeEntry q2 = quakeData.get(i+1);
                quakeData.set(i,q2);
                quakeData.set(i+1,q1);
            }
        
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size()-1; i++) {
            for (QuakeEntry qe: in) { 
                System.out.println(qe);
            } 
            onePassBubbleSort(in,i);
        }
        
     }
     
    public boolean checkInSortedOrder( ArrayList<QuakeEntry> in){
        for (int i=0;i<in.size()-1;i++){
            if (in.get(i).getMagnitude() > in.get(i+1).getMagnitude()){
                return false;
            }
        
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
       int i;
       for (i=0; i< in.size()-1; i++) {
            if (checkInSortedOrder(in)){
                break;}
            onePassBubbleSort(in,i);
        }
        System.out.println(i);
     }

     
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int i;
       for (i=0; i< in.size(); i++) {
            if (checkInSortedOrder(in)){
                break;}
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        System.out.println(i);
    }
}


/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for (String line:fr.lines()){
            LogEntry temp = WebLogParser.parseEntry(line);
            records.add(temp);
            
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> keeper = new ArrayList<String>();
         for (LogEntry record: records){
            String temp = record.getIpAddress();
            if (!keeper.contains(temp)){
                keeper.add(temp);
            }
            }
            
         return keeper.size();
     }
     
     public void printAllHigherThanNum(int num){
     for (LogEntry record:records){
        int temp = record.getStatusCode();
        if (temp>num){
            System.out.println(record);
        }
        }
    }   
        
    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
    //public int uniqueIPVisitsOnDay(String someday){    
        ArrayList<String> ip = new ArrayList<String>();
        //HashMap<String,Integer> ip = new HashMap<String,Integer>();
        String month = someday.substring(0,3);
        String day = someday.substring(4,6);
        for(LogEntry record:records){
            String tempdate = record.getAccessTime().toString();
            String temp_month = tempdate.substring(4,7);
            String temp_day = tempdate.substring(8,10);
            if(temp_month.equals(month)&&temp_day.equals(day)){
                String temp_ip = record.getIpAddress();
            //if (!ip.keySet().contains(temp_ip)){
            if (!ip.contains(temp_ip)){    
                ip.add(temp_ip);
                //ip.put(temp_ip,1);
            }
            }
        }
        return ip; 
        }
        
    public int counUniqueIPsInRange(int low, int high){
        ArrayList<String> ip = new ArrayList<String>();

        for (LogEntry record:records){
            String tempip = record.getIpAddress();
            int tempcode = record.getStatusCode();
            if (tempcode>=low && tempcode<=high){
                if (!ip.contains(tempip)){
                ip.add(tempip);}
            }
      
        }
        return ip.size();
    }    
    
    public HashMap<String,Integer> countVisitsPerIP(){
        HashMap<String,Integer> ip = new HashMap<String,Integer>();

        for(LogEntry record:records){

            String temp_ip = record.getIpAddress();
            if (!ip.keySet().contains(temp_ip)){  
                ip.put(temp_ip,1);
            }
            else{
            ip.put(temp_ip,ip.get(temp_ip) + 1);
            }
            }
        return ip;     
        }
    
    public int mostNumberVisitsByIP(HashMap<String,Integer> map){
    int count = 0;
    for (String k:map.keySet()){
    int temp = map.get(k);
    if(temp>count){count = temp;}
    }
    return count;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> map){
    ArrayList<String> ip = new ArrayList<String>();
    int count = 0;
    for (String k:map.keySet()){
        int temp = map.get(k);
        if(temp > count){count = temp;}
    }
    for (String k:map.keySet()){
        int temp = map.get(k);
        if(temp == count){ip.add(k);}
    }
    return ip;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> ret = new HashMap<String, ArrayList<String>>();
        for(LogEntry record:records){
            String tempdate = record.getAccessTime().toString();
            String temp_month = tempdate.substring(4,7);
            String temp_day = tempdate.substring(8,10);
            String temp = temp_month +" "+ temp_day;
            String ip = record.getIpAddress();
            if (!ret.containsKey(temp)){
                ArrayList<String> al = new ArrayList<String>();
                al.add(ip);
                ret.put(temp,al);
            }
            else{
                ret.get(temp).add(ip);
            }
            
        }
        return ret;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map){
    
        int count = 0;
        String d = "";
        for(String k:map.keySet()){
            int temp = map.get(k).size();
            if(temp > count){
                count = temp;
                d = k;
            }
            
        }
        return d;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String d){
    
    ArrayList<String> todayIP = map.get(d);
    HashMap<String, Integer> countMap = new HashMap<String, Integer>();
    for (String ip:todayIP){
        if (!countMap.containsKey(ip)){
            countMap.put(ip,1);
        }
        else{
            countMap.put(ip,countMap.get(ip)+1);
        }
    }
    return iPsMostVisits(countMap);
    }
}    
    

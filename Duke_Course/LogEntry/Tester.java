
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP(){
        LogAnalyzer l = new LogAnalyzer();
        l.readFile("weblog2_log");
        System.out.println(l.countUniqueIPs());
    }
    
    public void testPrintAllHigherThanNum(){
        LogAnalyzer l = new LogAnalyzer();
        l.readFile("weblog1_log");
        l.printAllHigherThanNum(400);
    }    
    
    public void testUniqueIPOnDay(){
        LogAnalyzer l = new LogAnalyzer();
        l.readFile("weblog-short_log");
        System.out.println(l.uniqueIPVisitsOnDay("Sep 14"));
        System.out.println(l.uniqueIPVisitsOnDay("Sep 30"));
    }        
    
    public void testUniqueIPInRange(){
        LogAnalyzer l = new LogAnalyzer();
        l.readFile("weblog1_log");
        System.out.println(l.counUniqueIPsInRange(200,299));
        //System.out.println(l.counUniqueIPsInRange(300,399));
    }      
    
    public void testIPsForDays(){
    LogAnalyzer l = new LogAnalyzer();
    l.readFile("weblog3-short_log");
    System.out.println(l.iPsForDays());
    }
    
    public void testIPsWithMostVisitsOnDay(){
    LogAnalyzer l = new LogAnalyzer();
    l.readFile("weblog3-short_log");
    HashMap<String, ArrayList<String>> temp = l.iPsForDays();
    //System.out.println(temp);
    System.out.println(l.iPsWithMostVisitsOnDay(temp,"Sep 30"));
    }
    
    public void q1(){
    LogAnalyzer l = new LogAnalyzer();
    l.readFile("weblog1_log");
    HashMap<String, Integer> temp = l.countVisitsPerIP();
    //System.out.println(temp);
    System.out.println(l.mostNumberVisitsByIP(temp));
    }    
    
    public void q2(){
    LogAnalyzer l = new LogAnalyzer();
    l.readFile("weblog1_log");
    HashMap<String, Integer> temp = l.countVisitsPerIP();
    //System.out.println(temp);
    System.out.println(l.iPsMostVisits(temp));
    }        
    
    public void q3(){
    LogAnalyzer l = new LogAnalyzer();
    l.readFile("weblog1_log");
    HashMap<String, ArrayList<String>> temp = l.iPsForDays();
    //System.out.println(temp);
    System.out.println(l.dayWithMostIPVisits(temp));
    }        

    public void q4(){
    LogAnalyzer l = new LogAnalyzer();
    l.readFile("weblog1_log");
    HashMap<String, ArrayList<String>> temp = l.iPsForDays();
    //System.out.println(temp);
    System.out.println(l.iPsWithMostVisitsOnDay(temp, "Mar 17"));
    }    
    
    public void quiz_q5(){
        LogAnalyzer l = new LogAnalyzer();
        l.readFile("weblog2_log");
        System.out.println(l.uniqueIPVisitsOnDay("Sep 24").size());
    }        
    
    public void quiz_q6(){
        LogAnalyzer l = new LogAnalyzer();
        l.readFile("weblog2_log");
        System.out.println(l.counUniqueIPsInRange(400,499) );
    }      
    
    public void quiz_q7(){
    LogAnalyzer l = new LogAnalyzer();
    l.readFile("weblog2_log");
    HashMap<String, Integer> temp = l.countVisitsPerIP();
    //System.out.println(temp);
    System.out.println(l.mostNumberVisitsByIP(temp));
    }    
    
    public void quiz_q8(){
    LogAnalyzer l = new LogAnalyzer();
    l.readFile("weblog2_log");
    HashMap<String, Integer> temp = l.countVisitsPerIP();
    //System.out.println(temp);
    System.out.println(l.iPsMostVisits(temp));
    } 
    
    public void quiz_q9(){
    LogAnalyzer l = new LogAnalyzer();
    l.readFile("weblog2_log");
    HashMap<String, ArrayList<String>> temp = l.iPsForDays();
    //System.out.println(temp);
    System.out.println(l.dayWithMostIPVisits(temp));
    }     
    
    public void quiz_q10(){
    LogAnalyzer l = new LogAnalyzer();
    l.readFile("weblog2_log");
    HashMap<String, ArrayList<String>> temp = l.iPsForDays();
    //System.out.println(temp);
    System.out.println(l.iPsWithMostVisitsOnDay(temp, "Sep 29"));
    }        
}

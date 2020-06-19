import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {

    
    public void totalBirth(){
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser(false);
    int total = 0;
    int totalF = 0;
    int totalM = 0;
    for (CSVRecord rec: parser){
        int temp = 1;//Integer.parseInt(rec.get(2));
        total = total + temp;
    if (rec.get(1).equals("M")){
    totalM = totalM + temp;
    }
    else{
    totalF = totalF + temp;}
    }
    System.out.println(total + "  " + totalM + "  " + totalF);
    }
    
    public int getRank(int year, String name, String gender){
    int r = -1;
    int row = 0;
    int numF = 0;
    String fName = "us_babynames_by_year/yob" + Integer.toString(year) + ".csv";
    FileResource fr = new FileResource(fName);
    CSVParser parser = fr.getCSVParser(false);
    
    for (CSVRecord rec: parser){
        
        row = row + 1;
        if (rec.get(1).equals("F")){
        numF = numF+1;}
        
        if (rec.get(0).equals(name)){
            if (gender.equals("F")){
            r = row;
            break;
            }
            else{
            r = row - numF;
            }
        }
    
    }
    return r;
    }
    
    public String getName(int year, int rank, String gender){
    String fName = "us_babynames_by_year/yob" + Integer.toString(year) + ".csv";
    FileResource fr = new FileResource(fName);
    CSVParser parser = fr.getCSVParser(false);
    String name = "NO NAME";
    int row = 0;
    int totalF = 0;
    for (CSVRecord rec:parser){
        row = row + 1;
        if (rec.get(1).equals("F")){totalF = totalF + 1;}
        if ((row == rank) && gender.equals("F") && rec.get(1).equals("F")){
            name = rec.get(0);
            break;
        }
        if ((row == (rank + totalF)) && gender.equals("M") && rec.get(1).equals("M")){
            name = rec.get(0);
            break;
        }
    
    }
    return name;
    }
    
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
    
        int oldRank = getRank(year, name, gender);
        System.out.println("old rank " + oldRank);
        String newName = getName(newYear,oldRank, gender);
        System.out.println(newName);
    }
    
    public int yearOfHighestRank(String name, String gender){
    DirectoryResource dr = new DirectoryResource();
    int rank = -1;
    int resultYear = -1;
    for (File f: dr.selectedFiles()){
        String fName = f.getName();
        String sYear = fName.substring(3,7);
        int year = Integer.parseInt(sYear);
        
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser(false);
        int currRank = getRank(year, name, gender);
        
        if (rank == -1){
            rank = currRank;
            resultYear = year;}
        else{
        if ((currRank!=-1) && (currRank < rank)){
        rank = currRank;
        resultYear = year;
        }
        }
    }
    return resultYear;
    }
    
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int sum = 0;
        int count = 0;
        for (File f: dr.selectedFiles()){
        String fName = f.getName();
        String sYear = fName.substring(3,7);
        int year = Integer.parseInt(sYear);
        
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser(false);
        int currRank = getRank(year, name, gender);
        
        if (currRank!=-1){
        sum = sum + currRank;
        count = count + 1;}
       
        }
        if (count == 0){return -1.0;}
        else{
        return (double) sum / (double) count;}
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        
        int count = 0;
        
        int rank = getRank(year, name, gender);
        int row = 0;
        int rowF = 0;
        
        String fName = "us_babynames_by_year/yob" + Integer.toString(year) + ".csv";
        FileResource fr = new FileResource(fName);
        CSVParser parser = fr.getCSVParser(false);
        
        for (CSVRecord rec:parser){
            row = row + 1;
            if (rec.get(1).equals("F")){rowF = rowF + 1;}
            if (gender.equals("F") && rec.get(1).equals("F") && row < rank){
            count = count + Integer.parseInt(rec.get(2));}
            if (gender.equals("M") && rec.get(1).equals("M") && (row - rowF) < rank){
            count = count + Integer.parseInt(rec.get(2));}
        }
        return count;
    
    
    }
}

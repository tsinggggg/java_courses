import org.apache.commons.csv.*;
import edu.duke.*;
import java.io.File;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {

    public CSVRecord coldestHourInFile(CSVParser parser){
    CSVRecord coldest = null;
    for (CSVRecord temp: parser){
    double tempTemp = Double.parseDouble(temp.get("TemperatureF"));
    if ((coldest == null) && (tempTemp != -9999)){
    coldest = temp;
    }
    else{
        double coldestTemp = Double.parseDouble(coldest.get("TemperatureF"));
    if ((tempTemp < coldestTemp) && (tempTemp != -9999)){
    coldest = temp;
    }
    }
    }
    return coldest;
}

    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord temp = coldestHourInFile(parser);
        System.out.println(temp.get("TemperatureF") );
    }
    
    public String fileWithColdestTemperature(){
    String coldest = null;
    Double coldestTemp = null;
    DirectoryResource dr = new DirectoryResource();
    for (File f: dr.selectedFiles()){
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
        CSVRecord temp = coldestHourInFile(parser);
        Double tempTemp = Double.parseDouble(temp.get("TemperatureF"));
        if (coldestTemp == null){
        coldest = f.getName();
        coldestTemp = tempTemp;}
        else{
        if (tempTemp < coldestTemp){
        coldest = f.getName();
        coldestTemp = tempTemp;
        }
    }
    }
    return coldest;
    }
    
    public void testFileWithColdestTemperature(){
        String coldest = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + coldest);
        
        FileResource fr = new FileResource("nc_weather/"+ coldest.substring(8,12) +"/"+coldest);
        CSVParser parser = fr.getCSVParser();
        CSVRecord temp = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was " + temp.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        CSVParser parser2 = fr.getCSVParser();
        for (CSVRecord record: parser2){
            System.out.println(record.get("DateUTC") + ": "+ record.get("TemperatureF"));
        }
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
    CSVRecord lowest = null;
    for (CSVRecord record:parser){
    if ((lowest == null) && (record.get("Humidity").equals("N/A") == false)){
    lowest = record;}
    else{
        double lowestH = Double.parseDouble(lowest.get("Humidity"));
        if (record.get("Humidity").equals("N/A") == false){
        double currentH = Double.parseDouble(record.get("Humidity"));
        if (currentH < lowestH){
        lowest = record;}}
    }
    }
    return lowest;
}

    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println(csv.get("Humidity") + "  " + csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        
        CSVRecord lowest = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
        CSVRecord temp = lowestHumidityInFile(parser);
        double tempLow = Double.parseDouble(temp.get("Humidity"));
        if (lowest == null){
        lowest = temp;}
        else{
        double low = Double.parseDouble(lowest.get("Humidity"));
        if (tempLow < low){
        lowest = temp;}
        }
        }
        return lowest;
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord temp = lowestHumidityInManyFiles();
        System.out.println(temp.get("Humidity") + "    " + temp.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser){
    double sum = 0.0f;
    int count = 0;
    for (CSVRecord record:parser){
    sum = sum + Double.parseDouble(record.get("TemperatureF"));
    count = count + 1;}
    return sum/ (double) count;
}

    public void testAverageTemperatureInFile(){
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    System.out.println(averageTemperatureInFile(parser));
}

    public double averageTemperatureWithHighHumidityInFile (CSVParser parser, int value){
    double sum = 0.0;
    int count = 0;
    for (CSVRecord record:parser){
    if (record.get("Humidity").equals("N/A")==false){
        double tempH = Double.parseDouble(record.get("Humidity"));
        if (tempH >= value){
        sum = sum + Double.parseDouble(record.get("TemperatureF"));
        count = count + 1;
    }}}
    if (count == 0){
    return -9999999;
    }
    else{
    return sum/ (double) count;}
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    double temp = averageTemperatureWithHighHumidityInFile(parser, 80);
    if (temp == -9999999){
    System.out.println("no such thing");}
    else{
    System.out.println(temp);}}
}

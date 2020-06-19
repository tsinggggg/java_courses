import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {

    public void tester(){
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    bigExporters(parser, "$999,999,999,999");}
    
    public String countryinfo(CSVParser parser, String country){
    
        String result = "";
        for (CSVRecord record : parser){
        if (record.get("Country").equals(country)){
        result = country + ":" 
        + record.get("Exports") + ":" 
        + record.get("Value (dollars)");
        }
        }
        return result;
    }
    
    public void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2){
    for (CSVRecord record: parser){
        String item = record.get("Exports");
    if (item.contains(exportItem1) && item.contains(exportItem2)){
    System.out.println(record.get("Country"));
    }
    }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
    int count = 0;
    for (CSVRecord record : parser){
    if (record.get("Exports").contains(exportItem)){
    count = count+1;}}
    return count;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        int len = amount.length();
    for (CSVRecord record : parser){
        if (record.get("Value (dollars)").length() > len){
        System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
    }
    }}
}

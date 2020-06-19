
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public Boolean twoOccurrences(String stringa, String stringb) {
    int first_ind = stringb.indexOf(stringa);
    if (first_ind == -1){
    return false;}
    if (first_ind + stringa.length() == stringb.length()){
    return false;}
    String remaining_b = stringb.substring(first_ind + stringa.length(),
                                           stringb.length());
    int second_ind = remaining_b.indexOf(stringa);
    if (second_ind == -1){
        return false;}
    else{
        return true;
    }
    }
    
    
    public String lastPart(String stringa, String stringb){
    int first_ind = stringb.indexOf(stringa);
    if (first_ind == -1){
    return stringb;}
    if (first_ind + stringa.length() == stringb.length()){
    return "";}
    return stringb.substring(first_ind + stringa.length(),
                                           stringb.length());
    }
    
    public void testing(){
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("a", "banana"));
        System.out.println(twoOccurrences("atg", "ctgtatgta"));
        
        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("zoo", "forest"));
    }
    
    
}

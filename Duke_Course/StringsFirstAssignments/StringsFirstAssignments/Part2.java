
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String s, String startCodon, String endCodon) {
        
        String cap_s = s.toUpperCase();
        String cap_startCodon = startCodon.toUpperCase();
        String cap_endCodon = endCodon.toUpperCase();
        
        int start_ind = cap_s.indexOf(cap_startCodon);
        if (start_ind == -1){
            return "";
        }
        int stop_ind = cap_s.indexOf(cap_endCodon, start_ind+3);
        if (stop_ind == -1){
            return "";
        }
        int length = stop_ind - start_ind;
        if (length % 3 == 0) {
            if (s == cap_s){
                return cap_s.substring(start_ind, stop_ind + 3);
            }
            else{
                return s.substring(start_ind, stop_ind + 3);
            }
        }
        else{
            return "";}
    }
    
    public void testSimpleGene() {
        String s1 = "ATATATATATAT";
        System.out.println(s1);
        System.out.println(findSimpleGene(s1, "ATG", "TAA"));
        String s2 = "ATGATGATG";
        System.out.println(s2);
        System.out.println(findSimpleGene(s2, "ATG", "TAA"));
        String s3 = "fuckoff";
        System.out.println(s3);
        System.out.println(findSimpleGene(s3, "ATG", "TAA"));
        String s4 = "ATGATGTAA";
        System.out.println(s4);
        System.out.println(findSimpleGene(s4, "ATG", "TAA"));
        String s5 = "ATGATGGTAA";
        System.out.println(s5);
        System.out.println(findSimpleGene(s5, "ATG", "TAA"));
    }

}

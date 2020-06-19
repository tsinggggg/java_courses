
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String s) {
        int start_ind = s.indexOf("ATG");
        if (start_ind == -1){
            return "";
        }
        int stop_ind = s.indexOf("TAA", start_ind+3);
        if (stop_ind == -1){
            return "";
        }
        int length = stop_ind - start_ind;
        if (length % 3 == 0) {
            return s.substring(start_ind, stop_ind + 3);
        }
        else{
            return "";}
    }
    
    public void testSimpleGene() {
        String s1 = "ATATATATATAT";
        System.out.println(s1);
        System.out.println(findSimpleGene(s1));
        String s2 = "ATGATGATG";
        System.out.println(s2);
        System.out.println(findSimpleGene(s2));
        String s3 = "fuckoff";
        System.out.println(s3);
        System.out.println(findSimpleGene(s3));
        String s4 = "ATGATGTAA";
        System.out.println(s4);
        System.out.println(findSimpleGene(s4));
        String s5 = "ATGATGGTAA";
        System.out.println(s5);
        System.out.println(findSimpleGene(s5));
    }

}


/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {

    public boolean isVowel (char ch){
        boolean ret = false;
        for (int i = 0; i<"aeiou".length();i++){
            char curr = "aeiou".charAt(i);
            if (ch == curr || ch == Character.toUpperCase(curr)){
                ret = true;
                break;
            }
        }
        return ret;
    }
    
    public void testIsVowel(){
    System.out.println(isVowel('F'));
    System.out.println(isVowel('a'));
    }
    
    public String replaceVowels(String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        for (int i=0; i< phrase.length(); i++){
            char curr = phrase.charAt(i);
            if (isVowel(curr)){
                sb.setCharAt(i, ch);
            }
        }
        return sb.toString();
    }
    
    public String emphasize (String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        for (int i=0; i< phrase.length(); i++){
            char curr = phrase.charAt(i);
            if (curr == Character.toUpperCase(ch) || curr == Character.toLowerCase(ch) ){
                if (i % 2 == 0){sb.setCharAt(i, '*');}
                else{sb.setCharAt(i, '+');}
            }
        }
        return sb.toString();        
    }
    
    public void testEmphasize(){
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
}

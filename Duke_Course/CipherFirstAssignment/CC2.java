
/**
 * Write a description of CC2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CC2 {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int k1;
    private int k2;
    
    public CC2(int key1, int key2){
        k1 = key1;
        k2 = key2;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    }
    
    private String encrypt(String input, String a_search, String a_replace){
        StringBuilder ret = new StringBuilder(input);
        
        for (int i=0;i<input.length();i++){
            int idx = a_search.toUpperCase().indexOf(input.charAt(i));
            if (idx!=-1){ret.setCharAt(i,a_replace.toUpperCase().charAt(idx));}
            idx = a_search.toLowerCase().indexOf(input.charAt(i));
            if (idx!=-1){ret.setCharAt(i,a_replace.toLowerCase().charAt(idx));}
        }
        return ret.toString();
    }
    
    public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        
        for (int i=0;i<input.length();i++){
            String temp = null;
            if(i%2 == 0){
                temp = encrypt(input.substring(i,i+1), alphabet, shiftedAlphabet1);
            }
            else{
                temp = encrypt(input.substring(i,i+1), alphabet, shiftedAlphabet2);
            }
            char toUse = temp.charAt(0);
            sb.setCharAt(i, toUse);
        }
        return sb.toString();
    }
    
    public String decrypt (String input){
        StringBuilder sb = new StringBuilder(input);
        
        for (int i=0;i<input.length();i++){
            String temp = null;
            if(i%2 == 0){
                temp = encrypt(input.substring(i,i+1), shiftedAlphabet1, alphabet);
            }
            else{
                temp = encrypt(input.substring(i,i+1), shiftedAlphabet2, alphabet);
            }
            char toUse = temp.charAt(0);
            sb.setCharAt(i, toUse);
        }
        return sb.toString();
    }
}


/**
 * Write a description of CC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CC {

    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CC(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }
    
    public String encrypt(String input){
        StringBuilder ret = new StringBuilder(input);
        
        for (int i=0;i<input.length();i++){
            int idx = alphabet.toUpperCase().indexOf(input.charAt(i));
            if (idx!=-1){ret.setCharAt(i,shiftedAlphabet.toUpperCase().charAt(idx));}
            idx = alphabet.toLowerCase().indexOf(input.charAt(i));
            if (idx!=-1){ret.setCharAt(i,shiftedAlphabet.toLowerCase().charAt(idx));}
        }
        return ret.toString();
    }    
    
    public String decrypt(String input){
        CC cc = new CC(26-mainKey);
        return cc.encrypt(input);
    }

}



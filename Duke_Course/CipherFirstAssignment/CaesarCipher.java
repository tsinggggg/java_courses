import edu.duke.*;
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {

    public String encrypt(String input, int key){
        StringBuilder ret = new StringBuilder(input);
        String oldA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String newA = oldA.substring(key) + oldA.substring(0,key);
        
        for (int i=0;i<input.length();i++){
            int idx = oldA.toUpperCase().indexOf(input.charAt(i));
            if (idx!=-1){ret.setCharAt(i,newA.toUpperCase().charAt(idx));}
            idx = oldA.toLowerCase().indexOf(input.charAt(i));
            if (idx!=-1){ret.setCharAt(i,newA.toLowerCase().charAt(idx));}
        }
        return ret.toString();
    }
    
    public void testEncrypt(){
    System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
    System.out.println(encrypt("First Legion", 17));
    }
    
    public void testCaesar() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, 1);
        System.out.println("key is " + 1 + "\n" + encrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder sb = new StringBuilder(input);
        
        for (int i=0;i<input.length();i++){
            String temp = null;
            if(i%2 == 0){
                temp = encrypt(input.substring(i,i+1),key1);
            }
            else{
                temp = encrypt(input.substring(i,i+1),key2);
            }
            char toUse = temp.charAt(0);
            sb.setCharAt(i, toUse);
        }
        return sb.toString();
    }
    
    public void testTwoKeys(String s){
    for (int i=1;i<26;i++){
    for (int j=20;j<26;j++){
    System.out.println(i + "  "+ j+ " "+encryptTwoKeys(s,i,j));
    }}}
    
    }

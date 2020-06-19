import edu.duke.*;
/**
 * Write a description of testCC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class testCC {
    
    public int[] countLetters (String s){
        int[] count = new int[26];
        String alph = "abcdefghijklmnopqrstuvwxyz";
        for (int i=0;i<s.length();i++){
            char c = Character.toLowerCase(s.charAt(i));
            int index = alph.indexOf(c);
            if (index !=-1){
                count[index] += 1;
            }
        }
        return count;
    }
    
    public int maxIndex (int[] vals){
        int ret = 0;
        int temp = vals[0];
        for (int i=0;i<vals.length;i++){
            if (vals[i]>temp){
                ret = i;
                temp = vals[i];
            }
        }
        return ret;
    }
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        String f = fr.asString();
        CC cc = new CC(18);
        System.out.println(cc.encrypt(f));
        System.out.println(cc.decrypt(cc.encrypt(f)));
        
        breakCaesarCipher(cc.encrypt(f));
    }
    
    public void breakCaesarCipher(String input){
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 - (4 - maxDex);
        }
        CC cc = new CC(dkey);
        System.out.println(dkey);
        System.out.println(cc.decrypt(input));
    }
    
    public void q1 (){
        CC cc = new CC(15);
        System.out.println(cc.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?"));
    }
}

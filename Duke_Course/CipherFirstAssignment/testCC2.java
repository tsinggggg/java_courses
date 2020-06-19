import edu.duke.*;
/**
 * Write a description of testCC2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class testCC2 {
    public String halfOfString (String message, int start){
        StringBuilder sb = new StringBuilder("");
        if (start >= message.length()){return "";}
        else{
            for (int i=start;i<message.length();i=i+2){
                sb.append(message.charAt(i));
            }
        }
        return sb.toString();
    }
    
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
    
    public int getKey (String s){
        int[] freq =  countLetters(s);
        int ind = maxIndex(freq) + 1;
        int dkey = ind - 5;
        if (ind < 5){
            dkey = 26 - (5 - ind);
        }
        return dkey;
    }
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        String s = fr.asString();
        CC2 cc = new CC2(17, 3);
        String e = cc.encrypt(s);
        System.out.println(e);
        System.out.println(cc.decrypt(e));
        System.out.println(breakCaesarCipher(e));
    }
    
    public String breakCaesarCipher(String input){
        String s1 = halfOfString(input , 0);
        String s2 = halfOfString(input , 1);
        int k1 = getKey(s1);
        int k2 = getKey(s2);
        System.out.println(k1 + " " +k2);
        CC2 cc = new CC2(k1, k2);
        return cc.decrypt(input);
    }
    
    public void q2(){
        CC2 cc = new CC2(21, 8);
        System.out.println(cc.decrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?"));
    }
    
    public void q4(){
        CC2 cc = new CC2(12, 2);
        System.out.println(cc.encrypt("Hfs cpwewloj loks cd Hoto kyg Cyy."));
    }
    
    public void q7(){
        System.out.println(breakCaesarCipher("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!"));
    }
    
    public void q8(){
        FileResource fr = new FileResource();
        String s = fr.asString();
        System.out.println(breakCaesarCipher(s));
    }
}

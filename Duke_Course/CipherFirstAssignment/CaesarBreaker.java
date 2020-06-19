
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarBreaker {
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
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 - (4 - maxDex);
        }
        System.out.println(dkey);
        return cc.encrypt(encrypted, 26- dkey);
    }
    
    public void testDecrypt(String s){
        System.out.println(decrypt(s));
    }
    
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
    
    public int getKey (String s){
        int[] freq =  countLetters(s);
        int ind = maxIndex(freq) + 1;
        System.out.println(ind);
        int dkey = ind - 5;
        if (ind < 5){
            dkey = 26 - (5 - ind);
        }
        return dkey;
    }
    
    public String decryptTwoKeys(String encrypted){
        String s1 = halfOfString(encrypted , 0);
        String s2 = halfOfString(encrypted , 1);
        int k1 = getKey(s1);
        int k2 = getKey(s2);
        System.out.println(k1 + "    " + k2);
        CaesarCipher cc = new CaesarCipher();
        return cc.encryptTwoKeys(encrypted, 26-k1,26-k2);
    }
    
    
}

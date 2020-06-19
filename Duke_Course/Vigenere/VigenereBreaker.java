import java.util.*;
import edu.duke.*;
import java.io.*;
public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i<message.length();i=i+totalSlices){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        for(int i=0;i<klength;i++){
            CaesarCracker temp = new CaesarCracker(mostCommon);
            key[i] = temp.getKey(sliceString(encrypted,i,klength));
        }
        return key;
    }
    
    public void test(){
        FileResource fr= new FileResource();
        String s = fr.asString();
        int[] key = tryKeyLength(s,4,'e');
        for (int k:key){System.out.println(k);}
        
    }
    
    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fr= new FileResource();
        String s = fr.asString();
        
        HashMap<String,HashSet<String>> languages = new HashMap<String,HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource tempfr = new FileResource(f);
            languages.put(f.getName(), readDictionary(tempfr));
        }
 
        breakForAllLangs(s, languages);
    }
    
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> hs = new HashSet<String>();
        for(String line:fr.lines()){
            String l = line.toLowerCase();
            if (!hs.contains(l)){
            hs.add(l);}
        }
        return hs;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        int count = 0;
        String[] words = message.split("\\W+");
        for (String w:words){
            String wl = w.toLowerCase();
            if (dictionary.contains(wl)){
                count = count+1;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
    
        int tempc = 0;
        String ret="";
        int keylength = 0;

        for (int i=1;i<=100;i++){
            int[] temp = tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
            VigenereCipher vc = new VigenereCipher(temp);
            String m = vc.decrypt(encrypted);
            int wc = countWords(m,dictionary);
            if (wc>tempc){
                tempc = wc;
                ret = m;
                keylength = i;
            }
        }
        //System.out.println(keylength);
        //System.out.println(tempc);
        return ret;
    
    }
    
    public void q4(){
        FileResource fr= new FileResource();
        String s = fr.asString();
        int[] key = tryKeyLength(s,38,'e');

        VigenereCipher vc = new VigenereCipher(key);
        String m = vc.decrypt(s);
        
        FileResource fr2 = new FileResource();
        HashSet<String> d = readDictionary(fr2);
        
        int wc = countWords(m,d);
        
        System.out.println(wc);
        
    }
    
    public char mostCommonCharIn (HashSet<String> dictionary){
        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        for (String word:dictionary){
            for (char c:word.toCharArray()){
                if(!count.keySet().contains(c)){
                    count.put(c,1);
                }
                else{
                    count.put(c,count.get(c)+1);
                }
            }
        }
        int temp = 0;
        char ret = 'e';
        for(char c:count.keySet()){
            int tempc = count.get(c);
            if( tempc>temp){
                temp = tempc;
                ret = c;
            }
        }
        return ret;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages){
        String l = "";
        int count = 0;
        String decrypted = "";
        for (String templ:languages.keySet()){
            String d = breakForLanguage(encrypted, languages.get(templ));
            int tempc = countWords(d, languages.get(templ));
            
            if (tempc > count){
                count = tempc;
                l = templ;
                decrypted = d;
            }
        }
        System.out.println(l);
        System.out.println(count);
        System.out.println(decrypted);
    }
}

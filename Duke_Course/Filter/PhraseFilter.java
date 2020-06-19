
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    
    private String where;
    private String phrase;
    
    public PhraseFilter(String w, String p){
        where = w;
        phrase = p;
    }
    
    public boolean satisfies(QuakeEntry qe) { 
        String s = qe.getInfo();
        int ind = s.indexOf(phrase);
        if (ind!=-1){
        if (where.equals("any")){return true;}
        if (where.equals("start") && ind == 0){return true;}
        if (where.equals("end") && (s.substring(s.length()-phrase.length(),s.length())).equals(phrase)){
        return true;}
        return false;
        }
        else{
        return false;
        }
    
    }
    
    public String getName(){
        return "Phrase";
    }
}

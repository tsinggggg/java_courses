import edu.duke.*;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {

public void f(){
    URLResource url_r = 
    new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
    for (String word : url_r.words()){
        int yt_ind = word.toLowerCase().indexOf("youtube.com");
        if ( yt_ind!= -1){
            
            int right_ind = word.substring(yt_ind, word.length()).indexOf("\"");
            int left_ind = word.lastIndexOf("\"", yt_ind);
            
            System.out.println(word.substring(left_ind+1, yt_ind + right_ind));
        }
    }

}
}

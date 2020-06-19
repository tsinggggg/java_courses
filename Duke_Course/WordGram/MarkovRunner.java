
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markovWord = new MarkovWord(5); 
        markovWord.setRandom(844);
        runModel(markovWord, st, 200); 
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 
    
    
    public void testHashMap(){
    EfficientMarkovWord emw = new EfficientMarkovWord(2);
    emw.setRandom(42);
    emw.setTraining("this is a test yes this is really a test yes a test this is wow");
    emw.printHashMapInfo();
    }
    
    public void testHashMap2(){
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        EfficientMarkovWord emw = new EfficientMarkovWord(6);
        emw.setRandom(792);
        emw.setTraining(st);
        emw.printHashMapInfo();
    }
    public void compareMethods(){
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        
        MarkovWord markovWord = new MarkovWord(3); 
        markovWord.setRandom(42);
        long t1 = System.nanoTime();
        runModel(markovWord, st, 100); 
        long t2 = System.nanoTime(); 
        
        EfficientMarkovWord emw = new EfficientMarkovWord(3); 
        emw.setRandom(42);
        long t3 = System.nanoTime();
        runModel(emw, st, 100);      
        long t4 = System.nanoTime();
        System.out.println(t2 - t1);
        System.out.println(t4 - t3);
    
    }

}

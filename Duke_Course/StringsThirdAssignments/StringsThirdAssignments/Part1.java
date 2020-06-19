import edu.duke.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {

    
    public int findStopCodon (String dna, int startIndex, String stopCodon) {
        
        int cur = dna.indexOf(stopCodon, startIndex + 3);
        while (cur != -1){
            if ((cur - startIndex)%3 == 0){
            return cur;}
            else{
            cur = dna.indexOf(stopCodon, cur + 1);}
        }
        return dna.length();
    }
    
    public String findGene(String dna, int start){
        
        if (start >= dna.length()){
        return "";}
        int startIndex = dna.indexOf("ATG", start);
        if (startIndex == -1){
        return "";}
        int taa = findStopCodon(dna, startIndex, "TAA");
        int tag = findStopCodon(dna, startIndex, "TAG");
        int tga = findStopCodon(dna, startIndex, "TGA");
        int stopIndex = 0;
        
        stopIndex = Math.min(taa, Math.min(tag,tga));
        if (stopIndex == dna.length()){
        return "";}
        else
        {return dna.substring(startIndex, stopIndex + 3);}
        
    }
    
    public void printAllGenes(String dna){
        int pos = 0;
        String gene = findGene(dna,pos);
        while (!gene.isEmpty()){
            System.out.println(gene);
            pos = dna.indexOf(gene, pos) + gene.length();
            gene = findGene(dna,pos);
        }
    }
    
    public StorageResource getAllGenes(String dna){
        StorageResource sr = new StorageResource();
        int pos = 0;
        String gene = findGene(dna,pos);
        while (!gene.isEmpty()){
            sr.add(gene);
            pos = dna.indexOf(gene, pos) + gene.length();
            gene = findGene(dna,pos);
        }
        return sr;
    }
    public void testFindAllGene(){
        String s1 = "ATGTAAATGTAA";
        printAllGenes(s1);
         
    }
    public void testGetAllGene(){
        String s1 = "ATGTAAATGTAA";
        StorageResource sr = getAllGenes(s1);
        for (String s:sr.data()){
        System.out.println(s);}
         
    }
    public void testFindGene(){
        String s1 = "sadfasdfATasdfasdfTAAxTAA";
        System.out.println(findGene(s1,0));
        String s2 = "sadfasdfATGGTATAAasd";
        System.out.println(findGene(s2,0));
        String s3 = "sadfasdfATGTAATAAasd";
        System.out.println(findGene(s3,0));  
        String s4 = "sadfasdfATGTATAAasd";
        System.out.println(findGene(s4,0));           
    }
    
    public void testFindStopCodon(){
        //           01234567890123456789012345     
        String s1 = "sadfasdfATGasdfasdfTAAxTAA";
        System.out.println(findStopCodon(s1,11,"TAA"));
    }
    
    public float cgRatio(String dna){
        int count = 0;
        int l = dna.length();
        float result = 0.0f;
        for (char c: dna.toCharArray()){
        if ((c=='C') || (c=='G')){
        count = count + 1;}
        }
        result = (float)count/ (float)l;
        return result;
    }
    
    public void testCgRatio(){
    String s = "ATGCCATAG";
    System.out.println(cgRatio(s));
    }
    
    public void processGenes(StorageResource sr){
    System.out.println("task1");   
    for (String s:sr.data()){
    if (s.length()>-1){
    System.out.println(s);}
    }
    System.out.println("task1");
    
    System.out.println("task2");
    int count = 0;
    for (String s:sr.data()){
    if (s.length()>-1){
    count = count+1;}
    }
    System.out.println(count);
    System.out.println("task2");
    
    System.out.println("task3");
    for (String s:sr.data()){
    if (cgRatio(s)>0.35){
    System.out.println(s);}
    }
    System.out.println("task3");
    
    System.out.println("task4");
    int count2 = 0;
    for (String s:sr.data()){
    if (cgRatio(s)>0.35){
    count2 = count2 + 1;}
    }
    System.out.println(count2);
    System.out.println("task4");
    
    System.out.println("task5");
    int len = 0;
    for (String s:sr.data()){
    if (s.length()>len){
    len = s.length();}
    }
    System.out.println(len);
    System.out.println("task5");    
}

public void testProcessGenes(){
    FileResource fr = new FileResource("GRch38dnapart.fa");
    String dna = fr.asString();
    StorageResource sr = getAllGenes(dna.toUpperCase());
    processGenes(sr);
}
}

import edu.duke.FileResource;

import java.util.HashMap;

public class CodonCount {
    private HashMap<String, Integer> codonCount;
    public CodonCount(){
        codonCount = new HashMap<String, Integer>();
    }
    public void buildCodonMap(int start, String dna){
        codonCount.clear();
        String cutDna = dna.trim().substring(start);
        for(int i = 0; i < cutDna.length() - 3; i += 3){
            String codon = cutDna.substring(i, i + 3);
            if(codonCount.containsKey(codon)){
                int count = codonCount.get(codon);
                codonCount.put(codon, count + 1);
            } else {
                codonCount.put(codon, 1);
            }
        }
    }
    public String getMostCommonCodon(){
        int maxInt = 0;
        String mostCommonCodon = "";
        for(String codon : codonCount.keySet()){
            int count = codonCount.get(codon);
            if(count > maxInt){
                maxInt = count;
                mostCommonCodon = codon;
            }
        }
        return mostCommonCodon;
    }
    public void printCodonCounts(int start, int end){
        for(String codon : codonCount.keySet()){
            int count = codonCount.get(codon);
            if(count >= start && count <= end){
                System.out.println(codon + ": " + count + "\n");
            }
        }
    }
    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString();
        String dnaUpper = dna.toUpperCase();
        buildCodonMap(0, dnaUpper);
        System.out.println("Unique words: " + codonCount.size());
        printCodonCounts(7,7);
        String mostCommon = getMostCommonCodon();
        System.out.println("Most common: " + mostCommon);
        System.out.println("Count = " + codonCount.get(mostCommon));
    }

    public static void main(String[] args) {
        CodonCount inst = new CodonCount();
        inst.tester();
    }
}

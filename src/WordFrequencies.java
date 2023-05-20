import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public WordFrequencies(){
        this.myWords = new ArrayList<String>();
        this.myFreqs = new ArrayList<Integer>();
    }
    public void findUnique(){
        this.myWords.clear();
        this.myFreqs.clear();
        FileResource fr = new FileResource();
        for(String s : fr.words()){
            String lowered = s.toLowerCase();
            if(!myWords.contains(lowered)){
                this.myWords.add(lowered);
                this.myFreqs.add(this.myWords.indexOf(lowered), 0);
            }
            int nextValue = this.myFreqs.get(this.myWords.indexOf(lowered));
            this.myFreqs.set(this.myWords.indexOf(lowered), nextValue + 1);
        }
    }
    public int findIndexOfMax(){
        int maxIdx = 0;
        for(int i = 1; i < this.myFreqs.size(); i++){
            if(this.myFreqs.get(i) > this.myFreqs.get(maxIdx)){
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    public void tester(){
        this.findUnique();
        int maxIdx = findIndexOfMax();
        for(String s : this.myWords){
            int idx = this.myWords.indexOf(s);
            int freq = this.myFreqs.get(idx);
            System.out.println(s + ": " + freq);
        }
        System.out.println("Index of max frequency: " + maxIdx);
        System.out.println("Max frequency word: " + this.myWords.get(maxIdx));
        System.out.println("Unique words: " + this.myFreqs.size());
    }

    public static void main(String[] args) {
        WordFrequencies inst = new WordFrequencies();
        inst.tester();
    }
}

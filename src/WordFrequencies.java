import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public WordFrequencies(ArrayList<String> words, ArrayList<Integer> freqs){
        this.myWords = words;
        this.myFreqs = freqs;
    }
    public void findUnique(){
        this.myWords.clear();
        this.myFreqs.clear();
        FileResource fr = new FileResource();
        for(String s : fr.words()){
            if(!myWords.contains(s)){
                this.myWords.add(s);
            }
            int wordIdx = this.myWords.indexOf(s);
            int nextValue = this.myFreqs.get(wordIdx);
            this.myFreqs.set(wordIdx, nextValue + 1);
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
        findUnique();
        int maxIdx = findIndexOfMax();
        for(String s : this.myWords){
            int idx = this.myFreqs.indexOf(s);
            int freq = this.myFreqs.get(idx);
            System.out.println(s + ": " + freq);
        }
        System.out.println("Index of max frequency: " + maxIdx);
    }

    public static void main(String[] args) {
        WordFrequencies inst = new WordFrequencies();
        inst.tester();
    }
}

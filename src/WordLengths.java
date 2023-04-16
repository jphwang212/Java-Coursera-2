import edu.duke.FileResource;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class WordLengths {
    public int indexOfMax(int[] values){
        int max = 0;
        for(int i = 0; i < values.length; i++){
            if(values[i] > max){
                max = values[i];
            }
        }
        return max;
    }
    public void countWordLengths(FileResource resource, int[] counts){
        for(String word : resource.words()){
            int length = word.length();
            if(!Character.isLetter(word.charAt(0))){
                length--;
            }
            if(!Character.isLetter(word.charAt(length - 1))){
                length--;
            }
            if(length > counts.length){
                length = counts.length;
            }
            counts[length]++;
        }
        for(int i = 1; i < counts.length; i++){
            System.out.println(i + " letter word: " + counts[i]);
        }
    }
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        int max = indexOfMax(counts);
        System.out.println("Largest element at: " + max);
    }

    public static void main(String[] args) {
        WordLengths inst = new WordLengths();
        inst.testCountWordLengths();
    }
}

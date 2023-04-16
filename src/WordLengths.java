import edu.duke.FileResource;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class WordLengths {
//    public int indexOfMax(int[] values){
//
//    }
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
        System.out.println(counts.toString());
    }
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
    }

    public static void main(String[] args) {
        WordLengths inst = new WordLengths();
        inst.testCountWordLengths();
    }
}

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> fileWords;
    public WordsInFiles(){
        fileWords = new HashMap<String, ArrayList<String>>();
    }
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for(String word : fr.words()){
            String fileName = f.getName();
            if(fileWords.containsKey(word)){
                ArrayList<String> fileNameList = fileWords.get(word);
                if(!fileNameList.contains(fileName)){
                    fileNameList.add(fileName);
                    fileWords.replace(word, fileNameList);
                }
            } else {
                ArrayList<String> fileList = new ArrayList<String>();
                fileList.add(fileName);
                fileWords.put(word, fileList);
            }
        }
    }
    public void buildWordFileMap(){
        fileWords.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    public int maxNumber(){
        int maxNum = 0;
        for(String s : fileWords.keySet()){
            int length = fileWords.get(s).size();
            if(length > maxNum){
                maxNum = length;
            }
        }
        return maxNum;
    }
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> output = new ArrayList<String>();
        for(String s : fileWords.keySet()){
            if(fileWords.get(s).size() == number){
                output.add(s);
            }
        }
        return output;
    }
    public void printFilesIn(String word){
        ArrayList<String> fileNames = fileWords.get(word);
        System.out.println("\nFiles in " + word + ": ");
        for(String name : fileNames){
            System.out.print(name + ", ");
        }
    }
    public void tester(){
        buildWordFileMap();
        int maxNum = maxNumber();
        System.out.println("Max number of files = " + maxNum);
        ArrayList<String> wordFiles = wordsInNumFiles(4);
        for(String file : wordFiles){
            printFilesIn(file);
        }
        System.out.println("\nNumber of words = " + wordFiles.size());
        printFilesIn("tree");
    }
    public static void main(String[] args) {
        WordsInFiles inst = new WordsInFiles();
        inst.tester();
    }
}

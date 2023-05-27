import java.io.File;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder slice = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i += totalSlices){
            slice.append(message.charAt(i));
        }
        return slice.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i = 0; i < klength; i++){
            String currSlice = sliceString(encrypted, i, klength);
            int sliceKey = cc.getKey(currSlice);
            key[i] = sliceKey;
        }
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fr = new FileResource();
        String fileString = fr.asString().toLowerCase();
        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> multiDictionary = new HashMap<String, HashSet<String>>();
        for(File f : dr.selectedFiles()){
            FileResource currFile = new FileResource(f);
            HashSet<String> langDict = readDictionary(currFile);
            multiDictionary.put(f.getName(), langDict);
        }

//        int[] key = tryKeyLength(fileString, 4, 'e');
//        for(int i = 0; i < key.length; i++){
//            System.out.print(key[i] + " ");
//        }
//        VigenereCipher vc = new VigenereCipher(key);
//        String decrypted = vc.decrypt(fileString);

        breakForAllLangs(fileString, multiDictionary);
    }

    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dict = new HashSet<String>();
        for(String line : fr.lines()){
            dict.add(line.toLowerCase());
        }
        return dict;
    }

    public int countWords(String message, HashSet<String> dictionary){
        String[] words = message.split("\\W+");
        int count = 0;
        for(String word : words){
            if(dictionary.contains(word)){
                count += 1;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int bestCount = 0;
        int currCount = 0;
        char mostCommon = mostCommonCharIn(dictionary);
//        int[] key = tryKeyLength(encrypted, 5, mostCommon);
//        VigenereCipher vc = new VigenereCipher(key);
//        String bestDecrypted = vc.decrypt(encrypted);
//        bestCount = countWords(bestDecrypted, dictionary);
        String bestDecrypted = "";
        for(int i = 1; i <= 100; i++){
            int[] key = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            currCount = countWords(decrypted, dictionary);
            if(currCount > bestCount){
                bestCount = currCount;
                bestDecrypted = decrypted;
//                System.out.println("Best length = " + i);
            }
        }

        System.out.println(bestCount);
        return bestDecrypted;
    }

    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        int maxFreq = 0;
        int currFreq = 0;
        String usedLanguage = "";
        String output = "";
        for(String language : languages.keySet()){
//            System.out.println(language);
            HashSet<String> languageDict = languages.get(language);
            String languageDecrypted = breakForLanguage(encrypted, languageDict);
            currFreq = countWords(languageDecrypted, languageDict);
            if(currFreq > maxFreq){
                maxFreq = currFreq;
                usedLanguage = language;
                output = languageDecrypted;
            }
        }
        System.out.println("Used language: " + usedLanguage);
        System.out.println(output);
    }

    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character, Integer> letterCounts = new HashMap<Character, Integer>();
        for(String word : dictionary){
            for(char c : word.toCharArray()){
                char lowered = Character.toLowerCase(c);
                if(!letterCounts.containsKey(lowered)){
                    letterCounts.put(lowered, 1);
                } else {
                    letterCounts.put(lowered, letterCounts.get(lowered) + 1);
                }
            }
        }
        int maxFreq = 0;
        int currFreq = 0;
        char mostCommon = '\0';
        for(char ch : letterCounts.keySet()){
            currFreq = letterCounts.get(ch);
            if(currFreq > maxFreq){
                maxFreq = currFreq;
                mostCommon = ch;
            }
        }
        return mostCommon;
    }

    public static void main(String[] args) {
        VigenereBreaker inst = new VigenereBreaker();
        inst.breakVigenere();
    }
    
}

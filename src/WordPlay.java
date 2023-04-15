import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class WordPlay {
    public boolean isVowel(char ch){
        char[] vowelArray = {'A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u'};
        for(int i = 0; i < vowelArray.length; i++){
            if(vowelArray[i] == ch){
                return true;
            }
        }
        return false;
    }
    public String replaceVowels(String phrase, char ch){
        StringBuilder replaced = new StringBuilder(phrase);
        for(int i = 0; i < phrase.length(); i++){
            if(isVowel(replaced.charAt(i))){
                replaced.setCharAt(i, ch);
            }
        }
        return replaced.toString();
    }
    public String emphasize(String phrase, char ch){
        StringBuilder emphasized = new StringBuilder(phrase);
        char upperCh = Character.toUpperCase(ch);
        for(int i = 0; i < phrase.length(); i++){
            char currentChar = Character.toUpperCase(emphasized.charAt(i));
            if(currentChar == upperCh){
                if(i % 2 == 0){
                    emphasized.setCharAt(i, '*');
                } else {
                    emphasized.setCharAt(i, '+');
                }
            }
        }
        return emphasized.toString();
    }

    public static void main(String[] args) {
        WordPlay inst = new WordPlay();
        String testPhrase1 = "Hello World";
        String testPhrase2 = "Mary Bella Abracadabra";
//        char testChar = 'a';
//        boolean vowel = inst.isVowel(testChar);
//        System.out.println("testChar is vowel = " + vowel);
        String replaced = inst.replaceVowels(testPhrase1, '*');
        System.out.println("Replaced = " + replaced);
        String emphasized = inst.emphasize(testPhrase2, 'a');
        System.out.println("Emphasized = " + emphasized);
    }
}

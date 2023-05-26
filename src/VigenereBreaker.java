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
        int[] key = tryKeyLength(fileString, 4, 'e');
        for(int i = 0; i < key.length; i++){
            System.out.print(key[i] + " ");
        }
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(fileString);
        System.out.println(decrypted);
    }

    public static void main(String[] args) {
        VigenereBreaker inst = new VigenereBreaker();
        inst.breakVigenere();
    }
    
}

import edu.duke.FileResource;

public class CaesarBreaker {
    public void testDecrypt(){
        String testString = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
//        String testString = "Top ncmy qkff vi vguv vbg ycpx";
//        FileResource fr = new FileResource();
//        String testString = fr.asString();
//        String halved = halfOfString(testString, 1);
//        System.out.println("Half = " + halved);
        String test = decryptTwoKeys(testString);
        System.out.println("Two keys string = " + test);
    }
    public String halfOfString(String message, int start){
        StringBuilder half = new StringBuilder();
        for(int i = start; i < message.length(); i += 2){
            half.append(message.charAt(i));
        }
        return half.toString();
    }
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxIdx = maxIndex(freqs);
        int diffKey = maxIdx - 4;
        if(maxIdx < 4){
            diffKey = 26 - (4 - maxIdx);
        }
        return cc.encrypt(encrypted, 26 - diffKey);
    }
    public int getKey(String s){
        int[] freqs = countLetters(s);
        int maxIdx = maxIndex(freqs);
        int diffKey = maxIdx - 4;
        if(maxIdx < 4){
            diffKey = 26 - (4 - maxIdx);
        }
        return diffKey;
    }
    public int[] countLetters(String s){
        String inputUpper = s.toUpperCase();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] freqs = new int[26];
        for(int i = 0; i < s.length(); i++){
            char ch = inputUpper.charAt(i);
            int dex = alphabet.indexOf(ch);
            if(dex != -1){
                freqs[dex] += 1;
            }
        }
        return freqs;

    }
    public int maxIndex(int[] freqs){
        int maxIdx = 0;
        for(int i = 0; i < freqs.length; i++){
            if(freqs[i] > freqs[maxIdx]){
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    public String decryptTwoKeys(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        String firstHalf = halfOfString(encrypted, 0);
        String secondHalf = halfOfString(encrypted, 1);
        int firstKey = getKey(firstHalf);
        int secondKey = getKey(secondHalf);
        System.out.println("Key 1 = " + firstKey);
        System.out.println("Key 2 = " + secondKey);
        String decrypted = cc.encryptTwoKeys(encrypted, firstKey, secondKey);
        return decrypted;
    }
    public static void main(String[] args){
        CaesarBreaker inst = new CaesarBreaker();
        inst.testDecrypt();
    }
}

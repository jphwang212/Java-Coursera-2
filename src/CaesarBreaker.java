public class CaesarBreaker {
    public void testDecrypt(){
        String testString = "Qbkm Zgis";
//        String halved = halfOfString(testString, 1);
//        System.out.println("Half = " + halved);
        String test = decryptTwoKeys(testString);
        System.out.println("Two keys = " + test);
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
        return maxIndex(freqs);
    }
    public int[] countLetters(String s){
        String inputUpper = s.toUpperCase();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] freqs = new int[26];
        for(int i = 0; i < s.length(); i++){
            if(Character.isLetter(s.charAt(i))){
                freqs[inputUpper.charAt(i) - 'A']++;
            }
        }
        return freqs;

    }
    public int maxIndex(int[] freqs){
        int max = 0;
        for(int i = 0; i < freqs.length; i++){
            if(freqs[i] > max){
                max = freqs[i];
            }
        }
        return max;
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

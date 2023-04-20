import edu.duke.FileResource;

import java.io.FileReader;

public class CaesarCipher {
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encryptTwo = new StringBuilder(input);
        String evenKeys = encrypt(input, key1);
        String oddKeys = encrypt(input, key2);
        for(int i = 0; i < input.length(); i++){
            if(i%2 == 0){
                encryptTwo.setCharAt(i, evenKeys.charAt(i));
            } else {
                encryptTwo.setCharAt(i, oddKeys.charAt(i));
            }
        }
        return encryptTwo.toString();
    }
    public String encrypt(String input, int key){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder encrypt = new StringBuilder(input);

        for(int i = 0; i < encrypt.length(); i++){
            int alphabetIdx;
            if(Character.isLowerCase(input.charAt(i))){
                alphabetIdx = lowerAlphabet.indexOf(input.charAt(i));
            } else alphabetIdx = alphabet.indexOf(input.charAt(i));
            if(alphabetIdx == -1){
                continue;
            }
            alphabetIdx += key;
            if(alphabetIdx >= 26){
                alphabetIdx = (alphabetIdx % 26);
            }
            if(Character.isLowerCase(input.charAt(i))){
                encrypt.setCharAt(i, lowerAlphabet.charAt(alphabetIdx));
            } else {
                encrypt.setCharAt(i, alphabet.charAt(alphabetIdx));
            }
//            System.out.println(alphabetIdx);
        }
        return encrypt.toString();
    }
    public void testEncrypt(){
//        String testString = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
//        String encrypted = encrypt(testString, 15);
//        System.out.println("Encrypted: " + encrypted);
//        FileResource fr = new FileResource();
//        String message = fr.asString();
//        int key = 0;
//        String encrypted = encrypt(message, key);
//        System.out.println("key is " + key + "\n" + encrypted);
//        String encrypted2 = encryptTwoKeys(testString, 8, 21);
//        System.out.println("Two key encrypt: " + encrypted2);
        String quizString = "Top ncmy qkff vi vguv vbg ycpx";
        String encrypted2 = encryptTwoKeys(quizString, 2, 20);
        System.out.println("Answer: " + encrypted2);
    }
    public static void main(String[] args) {
        CaesarCipher inst = new CaesarCipher();
        inst.testEncrypt();
    }
}

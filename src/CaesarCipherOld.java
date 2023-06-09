public class CaesarCipherOld {
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encryptTwo = new StringBuilder(input);
        String evenKeys = encrypt(input, key1);
        String oddKeys = encrypt(input, key2);
        for(int i = 0; i < input.length(); i++){
            if(encryptTwo.charAt(i) == ' '){
                continue;
            }
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
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        StringBuilder encrypt = new StringBuilder(input);

        for(int i = 0; i < encrypt.length(); i++){
            int alphabetIdx = shiftedAlphabet.indexOf(Character.toUpperCase(input.charAt(i)));
            if(alphabetIdx == -1){
                continue;
            }
            char changed = alphabet.charAt(alphabetIdx);
            if(Character.isLowerCase(input.charAt(i))){
                encrypt.setCharAt(i, Character.toLowerCase(changed));
            } else {
                encrypt.setCharAt(i, changed);
            }
//            System.out.println(changed);
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
//        String quizString = "Top ncmy qkff vi vguv vbg ycpx";
//        String testString = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String testString1 = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        String encrypted2 = encryptTwoKeys(testString1, 14, 24);
//        String encrypted = encrypt(testString, 15);
        System.out.println("Answer: " + encrypted2);
    }
    public static void main(String[] args) {
        CaesarCipherOld inst = new CaesarCipherOld();
        inst.testEncrypt();
    }
}

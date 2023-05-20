import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> CharacterNames;
    private ArrayList<Integer> CharacterCounts;
    public CharactersInPlay(){
        this.CharacterNames = new ArrayList<String>();
        this.CharacterCounts = new ArrayList<Integer>();
    }
    public void update(String person){
        if(this.CharacterNames.contains(person)){
            int idx = this.CharacterNames.indexOf(person);
            int oldCount = this.CharacterCounts.get(idx);
            this.CharacterCounts.set(idx, oldCount + 1);
        } else {
            this.CharacterNames.add(person);
            this.CharacterCounts.add(1);
        }
    }
    public void findAllCharacters(){
        this.CharacterNames.clear();
        this.CharacterCounts.clear();
        FileResource fr = new FileResource();
        for(String line : fr.lines()){
            if(line.contains(".")){
                update(line.substring(0,line.indexOf(".")));
            }
        }
    }
    public void tester(){
        this.findAllCharacters();
        System.out.println();
        for(int i = 0; i < this.CharacterNames.size(); i++){
            System.out.println(this.CharacterNames.get(i) + ": " + this.CharacterCounts.get(i));
        }
//        this.charactersWithNumParts(10, 15);
    }
    public void charactersWithNumParts(int num1, int num2){
        for(int i = 0; i < this.CharacterNames.size(); i++){
            if(this.CharacterCounts.get(i) >= num1 && this.CharacterCounts.get(i) <= num2){
                System.out.println("NumParts" + this.CharacterNames.get(i));
            }
        }
    }
    public static void main(String[] args) {
        CharactersInPlay inst = new CharactersInPlay();
        inst.tester();
    }
}

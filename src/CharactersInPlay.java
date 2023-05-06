import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> CharacterNames;
    private ArrayList<Integer> CharacterCounts;
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
        FileResource fr = new FileResource();
        for(String line : fr.lines()){
            if(line.contains(".")){
                update(line.substring(0,line.indexOf(".")));
            }
        }
    }
    public void tester(){
        findAllCharacters();
        for(int i = 0; i < this.CharacterNames.size(); i++){
            System.out.println(this.CharacterNames.get(i) + ": " + this.CharacterCounts.get(i));
        }
    }

    public static void main(String[] args) {
        CharactersInPlay inst = new CharactersInPlay();
        inst.tester();
    }
}

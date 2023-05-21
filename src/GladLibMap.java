import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class GladLibMap {
	private HashMap<String, ArrayList<String>> myMap;
	private ArrayList<String> usedWordList;
	private ArrayList<String> usedCategoryList;
	private Random myRandom;

	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";

	public GladLibMap(){
		myMap = new HashMap<String, ArrayList<String>>();
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}

	public GladLibMap(String source){
		myMap = new HashMap<String, ArrayList<String>>();
		initializeFromSource(source);
		myRandom = new Random();
	}

	private void initializeFromSource(String source) {
		String[] categories = {"/adjective.txt", "/noun.txt", "/color.txt", "/country.txt", "/name.txt", "/animal.txt", "/timeframe.txt", "/verb.txt", "/fruit.txt"};
		for(String category : categories){
			ArrayList<String> words = readIt(source + category);
			myMap.put(category, words);
		}
	}

	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}

	private String getSubstitute(String label) {
		if(myMap.containsKey(label)){
			ArrayList<String> randoms = myMap.get(label);
			return randomFrom(randoms);
		}
		return "**UNKNOWN**";
	}

	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String category = w.substring(first+1,last);
		String sub = getSubstitute(category);
		if (usedWordList.contains(sub)){
			return w;
		}
		usedWordList.add(sub);
		usedCategoryList.add(category);
		return prefix+sub+suffix;
	}

	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}

	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		} else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}

	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		} else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	public int totalWordsInMap(){
		int count = 0;
		for(String word : myMap.keySet()){
			count += myMap.get(word).size();
		}
		return count;
	}
	public int totalWordsConsidered(){
		int wordsConsidered = 0;
		for(String category : usedCategoryList){
			wordsConsidered += myMap.get(category).size();
		}
		return wordsConsidered;
	}
	public void makeStory(){
		usedWordList = new ArrayList<String>();
		usedCategoryList = new ArrayList<String>();
		System.out.println("\n");
		String story = fromTemplate("data/madtemplate2.txt");
		printOut(story, 60);
		System.out.println("Total words = " + totalWordsInMap());
		System.out.println("Total words considered = " + totalWordsConsidered());
	}

	public static void main(String[] args) {
		GladLibMap inst = new GladLibMap();
		inst.makeStory();
	}
}

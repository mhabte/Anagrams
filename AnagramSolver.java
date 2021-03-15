
import java.util.*;

//This class creates an anagram from a phrase
public class AnagramSolver {
	private Map<String, LetterInventory> dictionaryMap;
	private List<String> dictionary;
	
	//Given a list words, the constructor organizes the list in a way that
	//reduces the runtime of the code
	public AnagramSolver(List<String> list) {
		this.dictionary = list;
		this.dictionaryMap = new HashMap<>();
		
		for(int i = 0; i < list.size(); i++) {
			this.dictionaryMap.put(list.get(i), new LetterInventory(list.get(i)));
		}
	}
	
	//Throws an IllegalArgumentException if the integer passed is less than 0
	//Given a string and an integer, this method creates and prints an anagram from the string
	//with the amount of words determined by the integer passed. If the integer is 0,
	//then there is no limit to how many words the anagram is made up of
	public void print(String s, int max) {
		if(max < 0) {
			throw new IllegalArgumentException();
		}
		
		LetterInventory phrase = new LetterInventory(s);
		List<String> shortDictionary = new ArrayList<>();
		
		for(int i = 0; i < this.dictionary.size(); i++) {
			if(phrase.subtract(this.dictionaryMap.get(this.dictionary.get(i))) != null) {
				shortDictionary.add(this.dictionary.get(i));
			}
		}
		
		anagramMaker(phrase, max, shortDictionary, new ArrayList<String>());
	}
	
	//Given a letter inventory, an integer, and two lists, this method assists the print method
	//with its task of creating and printing anagrams.
	//The letter inventory is of the string used to make anagrams out of
	//The integer represents the number of words used in the anagram, unlimited if the integer is 0
	//The two lists represent the shortened version of the original list of words and
	//the list of words that make up the anagram
	private void anagramMaker(LetterInventory phrase, int max, List<String> shortDictionary,
			List<String> words) {
		if(phrase != null && phrase.isEmpty()) {
			System.out.println(words);
		}else {
			for(int i = 0; i < shortDictionary.size(); i++) {
				LetterInventory result = phrase.subtract(this.dictionaryMap.
						get(shortDictionary.get(i)));
						
				if(result != null && (max != 0 && words.size() < max || max == 0)) {
					words.add(shortDictionary.get(i));
					anagramMaker(result, max, shortDictionary, words);
					words.remove(words.size() - 1);
				}
			}
		}
	}
}

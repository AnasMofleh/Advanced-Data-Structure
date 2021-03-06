package javafx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor {
	
	public Map<String, Integer> generalWords = new TreeMap<String, Integer>();
	public Set<String> stopWords = new HashSet<String>();
	
	public GeneralWordCounter(Set<String> stopWords){
		this.stopWords = stopWords;
	}

	@Override
	public void process(String w) {
		// TODO Auto-generated method stub
		if(!stopWords.contains(w)){ //om w inte finns med i stopWords, lägg till i map....../plussa om det redan finns i map
			if(generalWords.containsKey(w)){ //om värdet redan finns i wordmap...
				generalWords.put(w, generalWords.get(w) +1); //...plussa med 1
			}
			else{
				generalWords.put(w, 1); //....annars skapa ny nyckel o ge värde 1
			}
		}
		
	}

	@Override
	public void report() {
		
		Set<Map.Entry<String, Integer>> wordSet = generalWords.entrySet(); //returnerar ett set av generalWords k/v-par
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet); //lägger in dem i en lista
		
		wordList.sort(new WordCountComparator());
		
		System.out.println("\ntest");
		for(int i = 0; i<5; i++){
			System.out.println(wordList.get(i));
		}
		
		/*
		System.out.println("\n Alla ord");
		for(String key : generalWords.keySet()){
			if(generalWords.get(key) >= 200){
				System.out.println(key + " -> " + generalWords.get(key));
			}
		}
		*/
	}
	//lab3kod
	public Set<Map.Entry<String, Integer>> getWords(){
		return generalWords.entrySet();
	}

}

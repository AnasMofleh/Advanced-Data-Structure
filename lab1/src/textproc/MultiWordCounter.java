package textproc;


import java.util.HashMap;
import java.util.Map;

public class MultiWordCounter implements TextProcessor {
	
	public Map<String, Integer> wordMap = new HashMap<String, Integer>();
	
	
	public MultiWordCounter(String[] words){
		for(String wordElement : words){
			wordMap.put(wordElement, 0);
		}
	}

	@Override
	public void process(String w) {
		if(wordMap.containsKey(w)){ //om värdet finns i wordmap är det ett av de sökta orden
			wordMap.put(w, wordMap.get(w) +1); //uppdattera värde
		}
		
	}

	@Override
	public void report() {
		System.out.println("\n Landskap");
		for(String key : wordMap.keySet()){
			System.out.println(key + " -> " + wordMap.get(key));
		}
		
	}

}

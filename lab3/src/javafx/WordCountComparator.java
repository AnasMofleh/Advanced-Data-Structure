package javafx;

import java.util.*;
import java.util.Map.Entry;

public class WordCountComparator implements Comparator<Map.Entry<String, Integer>> {

	@Override
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		if (o1.getValue() > o2.getValue())
			return -1;
		else if (o1.getValue() < o2.getValue())
			return 1;
		else
			return o1.getKey().compareTo(o2.getKey());
	}
	//compare ska returnera ett heltalsvärde <0 om o1 ska komma före o2
	// se integer.compare

}

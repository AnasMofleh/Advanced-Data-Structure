package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		long t0 = System.nanoTime();
		
		TextProcessor p = new SingleWordCounter("nils");
		
		Scanner scan = new Scanner(new File("undantagsord.txt")); // läser in stopwords från fil
		Set<String> stopWords = new HashSet<String>();
		while(scan.hasNext()){									//skapar set med stopwords från scanner
			stopWords.add(scan.next().toLowerCase());
		}
		
		List<TextProcessor> textProcessorList = new ArrayList<TextProcessor>(); //tillaggt
		textProcessorList.add(p);
		textProcessorList.add(new SingleWordCounter("norge"));
		textProcessorList.add(new MultiWordCounter(REGIONS));
		textProcessorList.add(new GeneralWordCounter(stopWords));
		
		
		
		

		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();

			for(TextProcessor processor : textProcessorList){
				processor.process(word);
			}
			
			//p.process(word);
		}

		s.close();

		for(TextProcessor processor : textProcessorList){
			processor.report();
		}
		//p.report();
		
		long t1 = System.nanoTime();
		System.out.println("tid: " + (t1 - t0)/ 1000000.0 + " ms");
	}
}
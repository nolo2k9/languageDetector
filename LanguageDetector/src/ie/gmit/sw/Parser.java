package ie.gmit.sw;

import java.io.*;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 
 * @author Keith Nolan 
 *         The parser class reads in the wili file and compares it
 *         against the query file. When the file path is entered for both files
 *         it sends the information here for processing.
 *         Several of the methods here call on methos from othe classes to help with the langauge detection.
 *         When the processing ha finished, It then returns the language.
 */

public class Parser implements Runnable {
	// declaring database variable
	private Database db = null;
	// declaring String file variable
	private String file;
	// declaring int k
	private int k;

	
	public Parser(BlockingQueue<Database> queue) {
		super();
		this.queue = queue;
	}

	private BlockingQueue<Database> queue;
	//constructor with parameters file, k
	public Parser(String file, int k) {
		super();
		this.file = file;
		this.k = k;
	}
	//database constructor db 
	public void databaseSetup(Database db) {
		this.db = db;
	}

	/**
	 * 
	 * @author Keith Nolan 
	 * 
	 * 		   Reads in the wili file While the readline is not null
	 *         splits it at the @ symbol.
	 *         If the length of the record is not 2 then continue
	 *         Split into array then, pass to parse method (1st
	 *         and second record)
	 *         
	 */
	@Override
	public void run() {
		try {
			// creating buffered reader passing it in file (wili)
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			//Setting line to null
			String line = null;
			//while reader is  not null
			while ((line = reader.readLine()) != null) {
				
				String[] record = line.trim().split("@");

				if (record.length != 2)
					continue;
				
				parse(record[0], record[1]);

			} // while

			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}// run

	/**
	 * 
	 * @param text
	 * @param lang
	 * @param ks
	 * 
	 *             Maps language to enum language (enum class) Loop over the
	 *             sequence reading off 1 kmer at a time Read substring from i to i
	 *             + k Add kmer and language to map
	 */
	private void parse(String text, String lang, int... ks) {

		Language language = Language.valueOf(lang);

		for (int i = 0; i <= text.length() - k; i++) {

			CharSequence kmer = text.substring(i, i + k);

			db.add(kmer, language);

		} // for

	}// parse

	/**
	 * @author Keith Nolan
	 * @param s
	 * @return
	 * 
	 * 		   Creates new ConcurrentSkipListMap which holds an integer and a
	 *         LanguageEntry
	 * 
	 *         Set initial frequency to 0 loop over query file set khash to the
	 *         kmerhash code. Check if map contains kmer if the database already contains the kmer set frequency to 1
	 *         ,if not add it to map. 
	 *         Put result into a new LanguageEntry
	 *         passing (kHash and frequency) into map Call getLanguage() passing it
	 *         the stored values Print out the result using getlanguage() Return the
	 *         contents of the file
	 */

	public String analyseQuery(String s) {
		// Creating new ConcurrentSkipListMap which holds an integer and a String
		Map<Integer, LanguageEntry> checkDb = new ConcurrentSkipListMap<>();
		//Set frequency to 0
		int frequency = 0;
		
		for (int i = 0; i < s.length() - k; i++) {
			CharSequence kmer = s.substring(i, i + k);

			int kHash = kmer.hashCode();//setting khash to the hashcode
			//if hashcode already exists frequency = 1
			if (checkDb.containsKey(kHash)) {
				frequency = 1;
				
			} else {
				frequency++;
			}
			checkDb.put(kHash, new LanguageEntry(kHash, frequency));

		} // for

		db.getLanguage(checkDb);//get language
		System.out.println("Language appears to be : " + db.getLanguage(checkDb) + "\n");//output findings
		return s; //return contents of file

	}// analyseQuery

}// Parser

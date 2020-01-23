package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author Keith Nolan
 *
 *         The class database is responsible for numerous functions in this
 *         programs, in many ways its the heart.
 * 
 *         It stores the kmers (shingles) in various maps (one for each
 *         language) and then sorts them by the top assigned most occurring (ex
 *         300). Evidence shows that the top 300 most occurring kmers will tell
 *         you what the language is. Only 400 characters of text are required to
 *         generated a sufficient set of n-grams to allow a language to be
 *         identified correctly This varies from language to language but
 *         provides a good basis for language detection.
 * 
 *         The out of place metric is used to measure the distance between kmers
 *         and languages. For each n-gram, the Out-of-Place distancemeasure if
 *         computed asd(n-gram) = Rs(n-gram) –Rq(n-gram), where Rs(n-gram) is
 *         the rank of an n-gram in a subject database and Rq(n-gram) is the
 *         rank of the same n-gram in the query database. The sum of all the
 *         Out-of-Placevalues for all n-grams is the distance measure of the
 *         document for that language
 * 
 *         It then has a method for getting the language based on the data that
 *         has been stored and processed in the various maps.
 */
public class Database {

	/**
	 * @author Keith Nolan 
	 * Maps Each languages to their n-grams and frequency of occurrence
	*/
	private Map<Language, Map<Integer, LanguageEntry>> db = new TreeMap<>(); //db

	/**
	 * @author Keith Nolan
	 * @param s
	 * @param lang
	 * 
	 *             Kmer and language passed in. Converts kmer into it's hashcode.
	 *             Doesn't store String, stores the (int) (cheaper in terms of
	 *             computer resources) Calls getLanguageEntries() passes it the
	 *             language. If language map contains kmer increment its frequency
	 *             initially set frequency to 1 Otherwise re-insert into map and
	 *             overwrite existing kmer with new language entry and frequency
	 */

	public void add(CharSequence s, Language lang) {
		int kmer = s.hashCode();
		Map<Integer, LanguageEntry> langDb = getLanguageEntries(lang);

		int frequency = 1;
		if (langDb.containsKey(kmer)) {
			frequency += langDb.get(kmer).getFrequency();
		}
		langDb.put(kmer, new LanguageEntry(kmer, frequency));

	}//add

	/**
	 * @author Keith Nolan
	 * @param lang
	 * @return
	 * 
	 * 		This method checks if it has the language map already stored. If it
	 *         does gets it, otherwise it creates it and adds it to the database
	 *         (Map db). Returns the language map.
	 * 
	 */
	Map<Integer, LanguageEntry> getLanguageEntries(Language lang) {
		Map<Integer, LanguageEntry> langDb = null;
		if (db.containsKey(lang)) {
			langDb = db.get(lang);
		} else {
			langDb = new TreeMap<Integer, LanguageEntry>();
			db.put(lang, langDb);
		}
		return langDb;
		
	}//getLanguageEntries
	
	
/**
 * @author Keith Nolan
 * @param max
 * 
 * This method cuts down number of entries to number specified (300)
 *  Set<Language> keys = db.keySet(); -  Gets set of all the languages 
 *  For each language get the mapping of integers (hashcode) to their language and re-insert them into the map
 *  Does this with whatever langauge is called when you call getTop() pass integer (number of entries) and the language. 
 */
	
	public void resize(int max) {
		
		Set<Language> keys = db.keySet();
		for (Language lang : keys) {
			
			Map<Integer, LanguageEntry> top = getTop(max, lang);
			db.put(lang, top);
		}//for
		
	}//resize

	/**
	 * @author Keith Nolan
	 * @param max
	 * @param lang
	 * @return
	 * 
	 * Rebuilds the map, passes back to resize and overwrites existing entries with the map.
	 * 
	 * Pass in the max (resize) and the language
	 * Keep the top 300 kmers in the map
	 * Create a temporary map
	 * Gets the set of frequencys for a language from the main map(db). x
	 * 
	 * Set initial rank to 1
	 * Uses compareTo in languageEntry
	 * Put rank into temporary map, when max is hit break out of loop
	 * Otherwise increment rank
	 * Return temp
	 * 
	 */
	public Map<Integer, LanguageEntry> getTop(int max, Language lang) {
		Map<Integer, LanguageEntry> temp = new ConcurrentSkipListMap<>();// concurrenctSkiplist
		List<LanguageEntry> les = new ArrayList<>(db.get(lang).values());
		Collections.sort(les);

		int rank = 1;
		for (LanguageEntry le : les) {
			le.setRank(rank);
			temp.put(le.getKmer(), le);
			if (rank == max)
				break;
			rank++;
			
		}//for

		return temp;
		
	}//getTop
	
	
	/**
	 * @author Keith Nolan
	 * @param query
	 * @return
	 * 
	 * Takes in a query.
	 * Create mapping of n-grams or kmers to language entries.
	 * Create a treeset ordered based on comparable(OutOfPlaceMetric)
	 * Compare query against database of languages.
	 * For each language in the map add it to sorted treeset.
	 * Add new oopm with langauge name, then call getOutOfPlaceDistance, pass it the query and the map for the language.
	 * Returns the lowest element in the set
	 */

	public Language getLanguage(Map<Integer, LanguageEntry> query) {
		TreeSet<OutOfPlaceMetric> oopm = new TreeSet<>();

		Set<Language> langs = db.keySet();
		for (Language lang : langs) {
			oopm.add(new OutOfPlaceMetric(lang, getOutOfPlaceDistance(query, db.get(lang))));
			
		}//for
		return oopm.first().getLanguage();
		
	}//getLanguage
	
	
	/**
	 * @author Keith Nolan
	 * @param query
	 * @param subject
	 * @return
	 * 
	 * This compares query map with the subject map( contains number of top entries specified)
	 * The distance is initially set to zero
	 * Creates a new treeset and is sorted bases on the query values.
	 * It then gets the sorted language entries from the treeset.
	 * 
	 * for each language entry in the query
	 * It gets a language entry from the database
	 * if it doesn't exist it sets the distance to be the subject size + 1
	 * This is because it's further than anything already stored.
	 * 
	 * If it is already there it sets distance to be the subject rank minus the query rank.
	 * 
	 * 
	 */

	private int getOutOfPlaceDistance(Map<Integer, LanguageEntry> query, Map<Integer, LanguageEntry> subject) {
		int distance = 0;

		Set<LanguageEntry> les = new TreeSet<>(query.values());
		for (LanguageEntry q : les) {
			LanguageEntry s = subject.get(q.getKmer());
			if (s == null) {
				distance += subject.size() + 1;
			} else {
				distance += s.getRank() - q.getRank();
			}//if/else
			
		}//for
		
		return distance;
		
	}//getOutOfPlaceDistance
	
	
	/*@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		int langCount = 0;
		int kmerCount = 0;
		Set<Language> keys = db.keySet();
		for (Language lang : keys) {
			langCount++;
			sb.append(lang.name() + "->\n");

			Collection<LanguageEntry> m = new TreeSet<>(db.get(lang).values());
			kmerCount += m.size();
			for (LanguageEntry le : m) {
				sb.append("\t" + le + "\n");
			}
		}
		sb.append(kmerCount + " total k-mers in " + langCount + " languages");
		return sb.toString();
	}
	*/

	// returns true if the parameter is contained
	public boolean contains(String s) {
		
		return false;
	}

}//Database
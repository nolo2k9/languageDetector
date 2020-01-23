package ie.gmit.sw;

/**
 * 
 * @author Keith
 * The Class LanguageEntry implements comparable
 * It stores the kmer, frequency, and the rank
 * kmer stored as int to allow use of hashCode
 * Frequency stores the frequency of occurrence of a kmer in a language
 * Rank stores the rank of a kmer in terms of it's frequency in the language
 * 
 */

public class LanguageEntry implements Comparable<LanguageEntry> {
	// declaring int variable kmer
	private int kmer;
	// Frequency of occurrence in a language
	private int frequency;
	// rank kmer in terms of frequency in the language
	private int rank;

	//languageEntry constructor
	public LanguageEntry(int kmer, int frequency) {
		super();
		this.kmer = kmer;
		this.frequency = frequency;
	}
	//getters/setters
	public int getKmer() {
		return kmer;
	}

	public void setKmer(int kmer) {
		this.kmer = kmer;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	/**
	 * compare 1 kmer to another in descending order by frequency
	 * Most frequently occurring first
	 */
	@Override
	public int compareTo(LanguageEntry next) {
		return -Integer.compare(frequency, next.getFrequency());
	}
	
    /** 
     * Return kmer, frequency and rank in string format
     */
	
	@Override
	public String toString() {
		return "[" + kmer + "/" + frequency + "/" + rank + "]";
	}
	
}//LanguageEntry
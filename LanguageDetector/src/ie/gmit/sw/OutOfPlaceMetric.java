package ie.gmit.sw;



/**
 * 
 * @author Keith Nolan
 * This class implements comparable, it shows the distance between the language and the query.
 */

public class OutOfPlaceMetric implements Comparable<OutOfPlaceMetric>  {
	
	private Language lang;
	private int distance;

	public OutOfPlaceMetric(Language lang, int distance) {
		super();
		this.lang = lang;
		this.distance = distance;
	}//constructor

	public Language getLanguage() {
		return lang;
	}
	
	/**
	 * 
	 * @return
	 * 
	 * Gets rid of minus sign when getting the distance between two numbers
	 */
	public int getAbsoluteDistance() {
		return Math.abs(distance);
	}

	/**
	 * Get the distance in ascending order by absolute distance, the closest to zero goes to the top of the list
	 */
	@Override
	public int compareTo(OutOfPlaceMetric o) {
		return Integer.compare(this.getAbsoluteDistance(), o.getAbsoluteDistance());
	}

	/**
	 * Returns lang(language) and the Absolute Distance
	 */
	@Override
	public String toString() {
		return "[lang=" + lang + ", distance=" + getAbsoluteDistance() + "]";
	}


}//OutOfPlaceMetric

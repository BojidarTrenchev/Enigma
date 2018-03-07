import java.util.ArrayList;

/*
 * The plugboard has 26 sockets, one for each letter of the alphabet. Plugs can then be used to
 *	connect any two sockets together. 
 */
public class Plugboard {

	/*
	 * A container for all the plugs
	 */
	private ArrayList<Plug> plugs = new ArrayList<Plug>();
	
	public static final int MAX_PLUGS = 13;
	
	/*
	 * Returns the number of connected plugs
	 */
	public int getNumPlugs() {
		return plugs.size();
	}
	
	/*
	 * Clears all the plugs from the arraylist
	 */
	public void clear() {
		plugs.clear();
	}
	
	
	/*
	 * Creates a new plug.
	 * If the plug doesn't clash with another one,
	 * it is added to the arraylist and  the method returns true.
	 * Otherwise it returns false.
	 */
	public boolean addPlug(char end1, char end2) {
		Plug plug = new Plug(end1, end2);

		if(plugs.size() != 0) {
			for(Plug currentPlug : plugs) {
				if(plug.clashesWith(currentPlug)) {
					return false;
				}
			}
		}

		plugs.add(plug);
		return true;
	}
	
	/*
	 * The method substitutes a given letter by calling the plugs' encode method 
	 * until one of them returns a different letter. 
	 * If they don't, then the same letter is returned.
	 */
	public char substitute(char letter) {
		
		char encodedLetter;
		if(plugs.size() != 0) {			
			for(Plug plug : plugs) {				
				encodedLetter = plug.encode(letter);
				
				if(encodedLetter != letter) {
					return encodedLetter;
				}
			}
		}
		
		return letter;
	}
}

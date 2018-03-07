
/*
 * A Plug connects two sockets. Each socket is a letter from A to Z
 */
public class Plug {
	
	private char end1;
	private char end2;
	
	/*
	 * A basic constructor which sets the two ends of the plug
	 */
	public Plug(char end1, char end2) {
		this.end1 = end1;
		this.end2 = end2;
	}
	
	/*
	 * Getter for end1
	 */
	public char getEnd1() {
		return end1;
	}
	
	/*
	 * Getter for end2
	 */
	public char getEnd2() {
		return end2;
	}
	
	/*
	 * Setter for end1
	 */
	public void setEnd1(char endLetter) {
		end1 = endLetter;
	}
	
	/*
	 * Setter for end2
	 */
	public void setEnd2(char endLetter) {
		end2 = endLetter;
	}
	
	/*
	 * The method takes a letter and encodes it.
	 * If it matches to one of the ends of the plug, then it returns the other end. 
	 * Otherwise, the same letter is returned.
	 */
	public char encode(char letterIn) {
		if(letterIn == end1) {
			return end2;
		}
		else if(letterIn == end2) {
			return end1;
		}
		else {
			return letterIn;
		}
	}
	
	/*
	 *  The method returns true if either end of the Plug is shared with 
	 *  the Plug passed into the method
	 */
	public boolean clashesWith(Plug plugin) {
		char otherEnd1 = plugin.getEnd1();
		char otherEnd2 = plugin.getEnd2();
		
		return otherEnd1 == end1 || otherEnd1 == end2 || otherEnd2 == end1 || otherEnd2 == end2;
	}
}

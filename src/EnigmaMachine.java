/*
 * A class which models the real Engima machine.
 */
public class EnigmaMachine {
	
	private Plugboard plugboard;
	private BasicRotor[] rotors;
	private Reflector reflector;
	
	/*
	 * The constructor initialises the plugboard, the rotors and the reflector
	 */
	public EnigmaMachine() {
		plugboard = new Plugboard();
		rotors = new BasicRotor[3];
		reflector = new Reflector();
	}
	
	/*
	 * Add a plug by given letters. If the adding was succesful true is returned,
	 * otherwise false
	 */
	public boolean addPlug(char end1, char end2) {
		return plugboard.addPlug(end1, end2);
	}
	
	/*
	 * Remove all current plugs
	 */
	public void clearPlugboard() {
		plugboard.clear();
	}
	
	/*
	 * Add a basic rotor to the given slot
	 */
	public void addRotor(BasicRotor rotor, int slot) {
		rotors[slot] = rotor;
	}
	
	/*
	 * Return the rotor at the given slot
	 */
	public BasicRotor getRotor(int slot) {
		return rotors[slot];
	}
	
	/*
	 * Add a reflector
	 */
	public void addReflector(Reflector reflector) {
		this.reflector = reflector;
	}
	
	/*
	 * Return the reflector
	 */
	public Reflector getReflector() {
		return reflector;
	}
	
	/*
	 * Set the position of a rotor at a given slot
	 */
	public void setPosition(int slot, int position) {
		rotors[slot].setPosition(position);
	}
	
	/*
	 * Encode a single character
	 */
	public char encodeLetter(char letter) {
		
		//If there is a Plug connected to the Plugboard socket corresponding to that letter
		//The letter is changed to that of the other end of the Plug.
		//Otherwise, the original letter is passed on
		letter = plugboard.substitute(letter);
		
		//Parse the char to an int between 0 and 25
		//If that is not possible, catch the exception
		int numLetter = 0;
		try {
			numLetter = letterToInt(letter);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		//Substitute the numLetter through all rotors
		for(int i = 0; i < rotors.length; i++) {
			numLetter = rotors[i].substitute(numLetter);
		}
		
		//Substitute it from the reflector
		numLetter = reflector.substitute(numLetter);
		
		//Substitute the numLetter in the rotor again but using thir inverse mapping
		for(int i = rotors.length - 1; i >= 0; i--) {
			numLetter = rotors[i].substituteBack(numLetter);
		}
		
		//Parse the integer to char
		letter = intToLetter(numLetter);
		
		//Check again the plugs
		letter = plugboard.substitute(letter);
		
		//Rotate the first rotor
		rotors[0].rotate();
		
		return letter;
	}
	
	/*
	 * Encode every character in a given string. If the user wishes, 
	 * the positions of the rotors are reset to their initial positions
	 */
	public String encodeText(String text, boolean resetEnigma) {

    	//remove any punctuation or white space from the line
        text = (text.replaceAll("[\\W]", ""));
		char[] encodedSymbols = new char[text.length()];
		for(int i = 0; i < encodedSymbols.length; i++) {
			encodedSymbols[i] = encodeLetter(text.charAt(i));
		}
		
		if(resetEnigma) {
			for(int i = 0; i < rotors.length;i++) {
				rotors[i].resetPosition();
			}
		}

		return new String(encodedSymbols);
	}
	
	/*
	 * Return the numeric representation from 0 to 25 of a letter.
	 * The method is case-insensitive
	 */
	private int letterToInt(char letter) throws Exception {
		if(letter >= 'A' || letter <= 'Z') {
			letter -= 'A';
		}
		else if(letter >= 'a' || letter <= 'z') {
			letter -= 'a';
		}
		else {
			throw new Exception("The symbol " + letter + " cannot be encoded");
		}
		return letter;
	}
	
	/*
	 * Parse an int from 0 to 25 to a letter
	 */
	private char intToLetter(int numLetter) {
		return (char)(numLetter + 'A');
	}
}

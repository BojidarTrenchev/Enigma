
/*
 * This class models the basic functionality of all types of rotors
 */
public abstract class Rotor {
	
	/*
	 * The current position of the rotor
	 */
	protected int position;
	
	/*
	 * The first assgned position of the rotor
	 */
	
	private int initialPosition;
	/*
	 * The name (type) of the rotor and the mapping can be accessed by the children classes
	 */
	protected String name;
	protected int[] mapping;
	
	protected static final int ROTORSIZE = 26;
	
	/*
	 * Setter for the position
	 */
	public void setPosition(int newPosition) {
		position = newPosition;
		initialPosition = position;
	}
	
	/*
	 * Getter for the position
	 */
	public int getPosition() {
		return position;
	}
	
	/*
	 * A getter for the name (type)
	 */
	public String getType() {
		return name;	
	}
	
	/*
	 * Resets the position by making it equal to the initial position
	 */
	public void resetPosition() {
		position = initialPosition;
	}
	
	/*
	 * Sets the mapping of the rotor
	 */
	public abstract void initialise(String nameOfRotor);
	
	/*
	 * Takes the integer representation of a character and
	 * substitutes it with another character from the mapping
	 */
	public abstract int substitute(int number);
}

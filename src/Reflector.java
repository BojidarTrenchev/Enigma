
/*
 * The reflector is a simple type of rotor which maps its input to a different output
 * The output is redirected back to the other 3 rotors which makes the Enigma self-reciprocal
 */
public class Reflector extends Rotor{
	
	/*
	 * A default constructor
	 */
	public Reflector() {
		
	}
		
	public Reflector(String nameOfRotor) {
		initialise(nameOfRotor);
	}
	
	/*
	 * Sets the mapping of the reflector
	 */
	@Override
	public void initialise(String nameOfRotor) {
		super.name = nameOfRotor;
		
		if(super.name == "ReflectorI")
			super.mapping = new int[] {24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 13, 6, 14, 10, 12, 8, 4, 1, 5, 25, 2, 22, 21, 9, 0, 19};
		else if(super.name == "ReflectorII")
			super.mapping = new int[] {5, 21, 15, 9, 8, 0, 14, 24, 4, 3, 17, 25, 23, 22, 6, 2, 19, 10, 20, 16, 18, 1, 13, 12, 7, 11};		
	}

	/*
	 * Takes and integer representation of a character and 
	 * returns the value it maps to in the mapping
	 */
	@Override
	public int substitute(int number) {
		return super.mapping[number];		
	}
}

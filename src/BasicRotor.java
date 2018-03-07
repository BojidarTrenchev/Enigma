
/*
 * The Basic Rotor can endode a character to its corresponding value in its mapping
 * but because it has position which changes the same letter won’t subsequently be encoded the same way. 
 */
public class BasicRotor extends Rotor{
	
	/*
	 * A default constructor
	 */
	public BasicRotor() {
		
	}
	
	/*
	 * This constructor initialises the rotor
	 */
	public BasicRotor(String type) {
		initialise(type);	
	}
	
	/*
	 * This constructor initialises the rotor and sets the posotion
	 */
	public BasicRotor(String type, int position) {
		initialise(type);	
		super.setPosition(position);
	}
		
	/*
	 * Sets the mapping of the rotor
	 */
	@Override
	public void initialise(String nameOfRotor) {
		super.name = nameOfRotor;
		
		if(nameOfRotor == "I") 
			super.mapping = new int[] { 4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9 };
		else if(nameOfRotor == "II")
			super.mapping = new int[] { 0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4 };
		else if (nameOfRotor == "III")
			super.mapping = new int[]  { 1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14 };
		else if (nameOfRotor == "IV")
			super.mapping = new int[] {4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17, 7, 23, 11, 13, 5, 19, 6, 10, 3, 2, 12, 22, 1 };
		else if (nameOfRotor == "V")
			super.mapping = new int[]  { 21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13, 7, 11, 23, 0, 22, 12, 9, 16, 14, 5, 4, 2, 10 };
	}

		
	/*
	 *  The method takes an integer representing a letter and 
	 *  returns an integer represented by that position in the mapping
	 */
	@Override
	public int substitute(int number) {
		int index = number - super.getPosition();
		
		if(index < 0) {
			index = Rotor.ROTORSIZE + index; 
		}
		
		int letter = super.mapping[index];
		
		letter += super.getPosition();
		
		if(letter >= Rotor.ROTORSIZE)
			letter -= Rotor.ROTORSIZE;
		
		return letter;
	}
	
	/*
	 * The method is the same as substitute(int) but uses the inverse of the mapping
	 */
	public int substituteBack(int number) {
		int result = 0;
		int index = number - super.getPosition();
		
		if(index < 0) {
			index = Rotor.ROTORSIZE + index; 
		}
		
		//Seacrh for the value in the mapping which corresponds to the 'index' variable 
		//and return its position in the array
		for(int i = 0; i < super.mapping.length; i++){
			if(super.mapping[i] == index) {
				result = i;
				break;
			}
		}
		
		result += super.getPosition();
		
		if(result >= Rotor.ROTORSIZE)
			result -= Rotor.ROTORSIZE;
		
		return result;
	}
	
	/*
	 * Add 1 to the position and if it is greater than the RotorSize make it 0
	 */
	public void rotate() {
		int newPosition = super.position + 1;
		
		if(newPosition >= Rotor.ROTORSIZE) {
			newPosition = 0;
		}
		
		super.position = newPosition;
	}

}
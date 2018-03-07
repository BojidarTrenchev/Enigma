import java.util.ArrayList;

/*
 * This class models simple test harnesses for finding missing plugs, 
 * positions or types of rotor in given enigma configurations
 */
public class Bombe {

	public static void main(String[] args) {
		
		Bombe bombe = new Bombe();
		
		//Challenge 1
		bombe.tryPlugs("JBZAQVEBRPEVPUOBXFLCPJQSYFJI", "ANSWER");
		
		//Message: DAISYDAISYGIVEMEYOURANSWERDO 
		//Plugs: [D, T] - [S, A]
		
		//Challenge 2
		bombe.tryRotorPositons("AVPBLOGHFRLTFELQEZQINUAXHTJMXDWERTTCHLZTGBFUPORNHZSLGZMJNEINTBSTBPPQFPMLSVKPETWFD", "ELECTRIC");
		
		//Message: WELLALWAYSBETOGETHERHOWEVERFARITSEEMSWELLALWAYSBETOGETHERTOGETHERINELECTRICDREAMS 
		//Rotor 0 position: 3; Rotor 1 position: 9; Rotor 2 position: 15
				
		//Challenge 3
		bombe.tryRotorTypes("WMTIOMNXDKUCQCGLNOIBUYLHSFQSVIWYQCLRAAKZNJBOYWW", "JAVA");
		
		//Message: ILOVECOFFEEILOVETEAILOVETHEJAVAJIVEANDITLOVESME 
		//rotor 0 is type V; rotor 1 is type III; rotor 2 is type II
	}
	
	/*
	 * The method runs through Challenge 1 trying different combinations of the missing plug.
	 * It prints the encoded message which contains the expected word
	 */
	public void tryPlugs(String messageToEncode, String expectedWord) {
				
		//Setting up the enigma configuration for Challenge 1
		EnigmaMachine enigma = new EnigmaMachine();
		
		BasicRotor rotor1 = new BasicRotor("IV");
		BasicRotor rotor2 = new BasicRotor("III");
		BasicRotor rotor3 = new BasicRotor("II");
		
		rotor1.setPosition(8);
		rotor2.setPosition(4);
		rotor3.setPosition(21);
		
		Reflector reflector = new Reflector();
		reflector.initialise("ReflectorI");
				
		enigma.addRotor(rotor1, 0);
		enigma.addRotor(rotor2, 1);
		enigma.addRotor(rotor3, 2);
		enigma.addReflector(reflector);
				
		char plug1Char = 'D';
		char plug2Char = 'S';

		//Create a list with all possible characters. 
		//That excludes the characters which are already in use by the plugs
		ArrayList<Character> allPossibleChars = new ArrayList<Character>();		
		char character;
		for(int i = 0; i < 26; i++) {
			character = (char)('A' + i);
			
			if(character != plug1Char && character != plug2Char) {
				allPossibleChars.add(character);
			}				
		}	
				
		String encodedMessage = null;		
		char possibleChar1;
		char possibleChar2;
	
		//Iterate through all possible combinations of plugs
		for(int i = 0; i < allPossibleChars.size(); i++) {
			possibleChar1 = allPossibleChars.get(i);
			
			for(int j = 0; j < allPossibleChars.size(); j++) {				
				possibleChar2 = allPossibleChars.get(j);
				
				//Check if the two chars are different
				if(possibleChar2 != possibleChar1) {
				
					//Assign the known and the possible letters to the plugs
					enigma.addPlug(plug1Char, possibleChar1);
					enigma.addPlug(plug2Char, possibleChar2);
					
					//Use the engima to encode the message
					encodedMessage = enigma.encodeText(messageToEncode, true);

					//Check if the encoded text contains the expected word and print it together with the plugs configurations
					if(encodedMessage.contains(expectedWord)) {
						System.out.println(encodedMessage + " Plugs: [" + plug1Char + ", " + possibleChar1 + "] - [" + plug2Char + ", " + possibleChar2 + "]");
					}
					
					//Reset the positions of the rotors and the plugboard
					rotor1.resetPosition();
					rotor2.resetPosition();
					rotor3.resetPosition();
					enigma.clearPlugboard();	
				}
			}
		}
	}
	
	/*
	 * The method runs through all possible values for the positions of the rotors.
	 * If the expected word is contained in the encoded text, a message is printed
	 */
	public void tryRotorPositons(String messageToEncode, String expectedWord) {
		
		//Setting up the Enigma for Challenge 2
		EnigmaMachine enigma = new EnigmaMachine();
		
		BasicRotor rotor1 = new BasicRotor("V");
		BasicRotor rotor2 = new BasicRotor("III");
		BasicRotor rotor3 = new BasicRotor("II");
		
		Reflector reflector = new Reflector();
		reflector.initialise("ReflectorI");
				
		enigma.addRotor(rotor1, 0);
		enigma.addRotor(rotor2, 1);
		enigma.addRotor(rotor3, 2);
		enigma.addReflector(reflector);
		enigma.addPlug('H', 'L');	
		enigma.addPlug('G', 'P');

				
		String encodedMessage = null;
		
		//Iterate through all possible positions of the 3 rotors
		for(int rotor1Pos = 0; rotor1Pos < 26; rotor1Pos++) {
			rotor1.setPosition(rotor1Pos);
			
			for(int rotor2Pos = 0; rotor2Pos < 26; rotor2Pos++) {				
				rotor2.setPosition(rotor2Pos);
				
				for(int rotor3Pos = 0; rotor3Pos < 26; rotor3Pos++) {
					rotor3.setPosition(rotor3Pos);
					
					encodedMessage = enigma.encodeText(messageToEncode, true);

					//If the encoded text contains the expected text, print it
					if(encodedMessage.contains(expectedWord)) {						
						System.out.println(encodedMessage + " Rotor 0 position: " + rotor1Pos + " Rotor 1 position: " + rotor2Pos + " Rotor 2 position: " + rotor3Pos);
					}	
					
					//There is no need to reset the plugboard as the plugs are not changed
				}
			}
		}
	}

	/*
	 * The method runs through all possible values for the types of the rotors.
	 * If the expected word is contained in the encoded text, a message is printed
	 */
	public void tryRotorTypes(String messageToEncode, String expectedWord) {
		
		//Setting up the Enigma for Challenge 3
		EnigmaMachine enigma = new EnigmaMachine();
		
		BasicRotor rotor1 = new BasicRotor();
		BasicRotor rotor2 = new BasicRotor();
		BasicRotor rotor3 = new BasicRotor();
		rotor1.setPosition(22);
		rotor2.setPosition(24);
		rotor3.setPosition(23);
		
		Reflector reflector = new Reflector();
		reflector.initialise("ReflectorI");
				
		enigma.addRotor(rotor1, 0);
		enigma.addRotor(rotor2, 1);
		enigma.addRotor(rotor3, 2);
		enigma.addReflector(reflector);
		enigma.addPlug('M', 'F');	
		enigma.addPlug('O', 'I');

				
		String encodedMessage = null;
		
		//Iterate through all possible values of the rotors' type
		for(int rotor1Type = 1; rotor1Type <= 5; rotor1Type++) {
			
			rotor1.initialise(getRotorType(rotor1Type));
			
			for(int rotor2Type = 1; rotor2Type <= 5; rotor2Type++) {				
				rotor2.initialise(getRotorType(rotor2Type));
				
				for(int rotor3Type = 1; rotor3Type <= 5; rotor3Type++) {
					rotor3.initialise(getRotorType(rotor3Type));
					
					encodedMessage = enigma.encodeText(messageToEncode, true);

					//If the encoded text contains the expected text, print it
					if(encodedMessage.contains(expectedWord)) {
						System.out.println(encodedMessage + " rotor 0 is type " + getRotorType(rotor1Type) + 
								" rotor 1 is type " + getRotorType(rotor2Type) + " rotor 2 is type " + getRotorType(rotor3Type));
					}
					
					//Reset the rotors' positions. There is no need to reset the plugs as they are not changed
					rotor1.resetPosition();
					rotor2.resetPosition();
					rotor3.resetPosition();
				}
			}
		}
	}
	
	/*
	 * Parse the numerical representation of a rotor type into a Roman letter
	 */
	private String getRotorType(int numberType) {
		String type = null;
		switch (numberType) {
			case 1: type = "I";
					break;
			case 2: type = "II";
					break;
			case 3: type = "III";
					break;
			case 4: type = "IV";
					break;
			case 5: type = "V";
					break;
		}
		
		return type;
	}
}



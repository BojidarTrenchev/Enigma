import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterface {
	
	private BufferedReader reader;
	private EnigmaMachine enigma;
	
	/*
	 * A constructor which initialises the global buffered reader
	 */
	public UserInterface() {
		reader = new BufferedReader(new InputStreamReader(System.in));
		enigma = new EnigmaMachine();
	}
	
	public static void main(String[] args) {
		UserInterface ui = new UserInterface();
		ui.startUI();
	}
	
	/*
	 * Starts the user interface
	 */
	public void startUI() {
		
		//Special commands:
		//"a" - encode again
		//"e" - exit
		//"s" - setup settings
		
		String command = "a";
		
		while(command.equals("s") || command.equals("a")) {
			System.out.println("Welcome to the Engima Machine! Set up your settings:");
				
			//Set up the enigma
			setPlugs();
			setRotors();		
			setReflector();
			
			while(command.equals("a")) {
				System.out.println("+++The machine is ready+++");					
				encodeFile();
				
				System.out.println("Enter 'e' to exit or 'a' to encode again or 's' to set up new settings");
				command = readString(); 
				
				//Validate user input
				while(command != "e" || command != "a" || command != "s") {
					System.out.println("Enter 'e' to exit or 'a' to encode again or 's' to set up new settings");
					command = readString(); 
				}
				
				if(command.equals("e")) {
					System.out.println("Goodbye!");
					System.exit(0);
				}
			}			
		}
	}
	
	/*
	 * Gets  input and output file paths from the user and encodes the message from input
	 * and writes the result in output
	 */
	private void encodeFile() {
		System.out.println("Enter input file path:");
		String inputPath = readString();
		System.out.println("Enter output file path:");
		String outputPath = readString();
		
		EnigmaFile enigmaFile = new EnigmaFile();
		enigmaFile.encryptFile(inputPath, outputPath, enigma);
		System.out.println("Done!");
	}

	
	/*
	 * Prompts the user to enter settings for the reflector
	 */
	private void setReflector() {
		System.out.println("Now set up the reflector");
		
		System.out.println("Enter 1 for Reflector I or enter 2 for Reflector II");
		
		//Read and validate input
		int type = readInt();
		while(type < 1 || type > 2) {
			System.out.println("Wrong input!");
			System.out.println("Try again");
			System.out.println("Enter 1 for Reflector I or enter 2 for Reflector II");
			type = readInt();
		}
		
		if(type == 1) {
			enigma.addReflector(new Reflector("ReflectorI"));
		}
		else {
			enigma.addReflector(new Reflector("ReflectorII"));
		}
	}
	
	/*
	 * Prompts the user to enter the rotors' settings
	 */
	private void setRotors() {
		System.out.println("Now set up the rotors");
		
		System.out.println("Enter 1 for basic rotors or enter 2 for turnover rotors");
		
		int type = readInt();
		
		//Check for valid input
		while(type < 1 || type > 2) {
			System.out.println("Wrong input!");
			System.out.println("Try again");
			System.out.println("Enter 1 for basic rotors or enter 2 for turnover rotors");
			type = readInt();
		}
		
		TurnoverRotor currentTurnRotor;
		
		//This variable is used to save the value of the previous turn over rotor 
		//so that it can be connected to the next one
		TurnoverRotor previousTurnRotor = new TurnoverRotor("I");
		
		int mappingType = 0;
		int position = -1;
		int i = 0;
		
		//Iterate through all 3 rotors 
		while(i < 3) {
			System.out.println("Enter a number between 1 and 5 for the type of mapping of rotor " + i);
			
			//Read the types of rotors
			mappingType = readInt();
			
			//Check for valid input
			if(mappingType >= 1 && mappingType <= 5) {
				
				System.out.println("Now enter the position of the rotor between 0 and 25");
				
				//Read the position of the rotor and check for valid input
				position = readInt();
				while(position < 0 || position > 25) {
					System.out.println("Wrong input. Try again");
					position = readInt();
				}
				
				if(type == 1) {
					//Add Basic rotor with the given settings
					enigma.addRotor(new BasicRotor(getStringMappingOfRotor(mappingType), position), i);
				}
				else {
					//Add Turnover rotor with the given settings
					currentTurnRotor = new TurnoverRotor(getStringMappingOfRotor(mappingType), position);
					enigma.addRotor(currentTurnRotor, i);
					
					//Keep the reference to the previous rotor so that it can be added as nextRotor
					if(i > 0)
						previousTurnRotor.setNextRotor(currentTurnRotor);
					
					previousTurnRotor = currentTurnRotor;
				}
				
				i++;
			}
			else {
				System.out.println("Wrong input. Try again");
			}
		}
	}
	
	/*
	 * Prompts the user to enter the plugs
	 */
	private void setPlugs() {
		System.out.println("How many plugs do you want?");

		int allPlugs = readInt();
		
		//Check if the input is valid. If not, then make the user reenter it
		while(allPlugs < 0 || allPlugs > Plugboard.MAX_PLUGS) {
			System.out.println("The maximum plugs can be 13");
			System.out.println("Try again!");
			allPlugs = readInt();
		}
		
		char char1;
		char char2;
		int i = 0;
		
		//The plugs are given as one line of 2 letters separated by interval
		//If the user enters invalid input, then the user has to try again 
		while(i < allPlugs) {
			System.out.println("Enter letters for plug " + (i + 1) + ", separated by interval");
			
			String[] input = readString().split(" ");	
			
			//Check for correct input
			if(input.length == 2) {
				
				//Separate the string to 2 characters
				char1 = input[0].toUpperCase().charAt(0);
				char2 = input[1].toUpperCase().charAt(0);
				
				//Check for correct input
				if(char1 < 'A' || char1 > 'Z' || char2 < 'A' || char2 > 'Z')
				{
					System.out.println("Incorrect input! Try again!");
					continue;
				}
			}
			else {
				System.out.println("Incorrect input! Try again!");
				continue;
			}
			
			//Add the letters to a plug for the enigma
			//And check if the new plug clashes with another
			if(enigma.addPlug(char1, char2)) {				
				i++;
			}
			else{
				System.out.println("This plug clashes with another");
				System.out.println("Try again");
			}
		}
	}
	
	/*
	 * Reads one line of integer
	 */
	private int readInt()  {
		String line = null;
		int result = -1;
		try {
			line = reader.readLine();
			result = Integer.parseInt(line);

		}
		catch(Exception e) {
			System.out.println("Invalid input");
		}
		return result;
	}
	
	/*
	 * Reads one line of string
	 */
	private  String readString() {
		String line = null;
		try {
			line = reader.readLine();
		}
		catch (IOException e) {
			System.out.println("Invalid input");
		}
		return line;
	}
	/*
	 * Parses an integer from 0 to 5 to roman letters
	 */
	private String getStringMappingOfRotor(int type) {
		String result = null;
		
		switch(type) {
			case 1: 
				result = "I";
				break;
			case 2:
				result = "II";
				break;
			case 3: 
				result = "III";
				break;
			case 4:
				result = "IV";
				break;
			case 5:
				result = "V";
				break;
		}
		
		return result;
	}
}

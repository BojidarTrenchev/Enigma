import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class EnigmaFile {
	
	/*
	 * Take a text file, encode all the text in it and save it to another file
	 */
	public void encryptFile(String inputPath, String outputPath, EnigmaMachine enigma) {
		ArrayList<String> lines = readFile(inputPath);
		
		for(int i = 0; i < lines.size(); i++) {
			lines.set(i, enigma.encodeText(lines.get(i), true));
		}
		
		writeFile(outputPath, lines);
	}
	
	/*
	 * Gets the path to the file it has to save to, and an arrayList with the text that has to be saved
	 * Then the text is saved into that file
	 */
	private void writeFile(String path, ArrayList<String> lines) {
		try {
			PrintWriter writer = new PrintWriter(path, "UTF-8");
			
			for(int i = 0; i < lines.size(); i++) {
				writer.println(lines.get(i));
			}
			
			writer.close();
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * Takes the path to the file to read from.
	 * It reads each line
	 * and saves the line in an array list which is returned
	 */
	private ArrayList<String> readFile(String path) {
		
        ArrayList<String> lines = new ArrayList<String>();
		
		try {
	        // FileReader reads text files in the default encoding.
	        FileReader fileReader = new FileReader(path);
	
	        // Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	     
	        String line;
	        
	        while((line = bufferedReader.readLine()) != null) {
	        	
	            lines.add(line);
	        }   
	        
	        bufferedReader.close();
	        fileReader.close();

		}
		catch(IOException e) {
			System.out.println(e.getMessage());		
		}
		
        return lines;
	}
}

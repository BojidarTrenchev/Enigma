COMP1202 Coursework

Enigma machine simulator

The program simulates an encoding enigma machine.

How to run the Enigma?
1. Compile and run UserInterface.java
2. The user is prompted to enter the settings of the machine. That includes plugs, rotor types and positions;
3. The user has to provide a path to a file containing some text and provide path to another output file where the result of the encoding will be stored;
4. After the encoding has finished, the user can choose whether to exit the application, to continue encoding other files with the same configuration or change the configuration of the machine.

How to run Bombe?
1. Compile and run Bombe.java
2. The program outputs the result of the three challenges given in the coursework specification. They are as follows:
	* Challenge 1 Message: DAISYDAISYGIVEMEYOURANSWERDO Plugs: [D, T] - [S, A];
	* Challenge 2 Message:WELLALWAYSBETOGETHERHOWEVERFARITSEEMSWELLALWAYSBETOGETHERTOGETHERINELECTRICDREAMS
		Rotor 0 position: 3; Rotor 1 position: 9; Rotor 2 position: 15;
	* Challenge 3 Message: ILOVECOFFEEILOVETEAILOVETHEJAVAJIVEANDITLOVESME; 
		rotor 0 is type V; rotor 1 is type III; rotor 2 is type II.

All parts from 1 to 8 were attempted.

Extensions:
* Console user interface – the user is allowed to enter the configuration of the enigma and encode the content of text files;
* Punctuation and white space between words removal – the program removes any punctuation and interval in the text which is going to be encoded. Note that it does not remove new lines!
* Added some additional constructors for almost all classes for convinience
* The user/programmer can choose whether to reset the position of the Rotor when encoding a new piece of text or line

Author: Bozhidar Trenchev

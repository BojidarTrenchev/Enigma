
/*
 * The Turnover Rotor is the same a Basic Rotor but has additional turnover position and reference to the next rotor.
 *  If after being rotated the first Rotor is at its turnover position then it will cause the Rotor to its right to rotate one position.
 */
public class TurnoverRotor extends BasicRotor {

	private int turnoverPosition;
	private BasicRotor nextRotor;
	
	/*
	 * A constructor which calls the parent's constructor and sets the turnOverPosition
	 */
	public TurnoverRotor(String type) {
		super(type);
		setTurnoverPosition(type);
	}
	
	/*
	 * A constructor which calls the parent's constructor, sets the turnOverPosition and the nextRotor
	 */
	public TurnoverRotor(String type, BasicRotor nextRotor) {
		super(type);
		setTurnoverPosition(type);
		setNextRotor(nextRotor);
	}
	
	/*
	 * A constructor which sets the turnOverPosition and the position
	 */
	public TurnoverRotor(String type, int position) {
		this(type);
		setPosition(position);
	}
		
	/*
	 * A constructor which sets the turnOverPosition, the nextRotor and the position
	 */
	public TurnoverRotor(String type, BasicRotor nextRotor, int position) {
		this(type, nextRotor);
		setPosition(position);
	}

	
	/*
	 * Sets the special turnOverPosition of the rotor
	 */
	public void setTurnoverPosition(String type) {
		switch (type) {
			case "I":
				turnoverPosition = 24;
				break;
			case "II":
				turnoverPosition = 12;
				break;
			case "III":
				turnoverPosition = 3;
				break;
			case "IV":
				turnoverPosition = 17;
				break;
			case "V":
				turnoverPosition = 7;
				break;
		}
	}
	
	/*
	 * Sets a reference to the next rotor whose position will be changed depending
	 */
	public void setNextRotor(BasicRotor nextRotor){
		this.nextRotor = nextRotor;
	}
	
	/*
	 * Adds 1 to the position of the rotor but it becomes equal to the turnOverPosition,
	 * then the next rotor is rotated as well
	 */
	@Override
	public void rotate() {
		int newPosition = super.position + 1;
				
		if(newPosition >= Rotor.ROTORSIZE) {
			newPosition = 0;
		}
		
		if(nextRotor != null && newPosition == turnoverPosition) {
			nextRotor.rotate();
		}
		
		super.position = newPosition;
	}

}

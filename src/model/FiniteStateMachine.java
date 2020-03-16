package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is the finite state machine. It could be Mealy or Moore.
 * @author johan
 * @version 1.0
 */

public class FiniteStateMachine {
	
	public final static int MEALY_MACHINE = 1;
	public final static int MOORE_MACHINE = 2;

	private int type;
	private String name;
	private ArrayList<State> states; // The states set Q
	private State initialState;
	private ArrayList<Character> S; // The input symbols set
	private ArrayList<Character> R; // The output symbols set
	private HashMap<String, State> transition; // The transition function
	private HashMap<String, Character> outputFunction; // The output function (Mealy and Moore)
	
	/**
	 * CONSTRUCTOR METHOD
	 * @param type - the type of FSM: Mealy or Moore
	 * @param name - the name of this machine
	 */
	public FiniteStateMachine(int type, String name) {
		this.type = type;
		this.name = name;
		states = new ArrayList<State>();
		initialState = null;
		S = new ArrayList<Character>();
		R = new ArrayList<Character>();
		transition = new HashMap<String, State>();
		outputFunction = new HashMap<String, Character>();
	}

	/**
	 * GET TYPE
	 * Returns the type of FSM: Mealy or Moore.
	 * @return type: int - 1 if it is Mealy. 2 if it is Moore.
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * GET NAME
	 * Returns the machines's name
	 * @return name: String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * GET STATES
	 * Returns the array that contains the states.
	 * @return states: ArrayList<State>
	 */
	public ArrayList<State> getStates(){
		return states;
	}
	
	/**
	 * ADD STATE
	 * This method creates a new State object taking the parameter s as its name. Then, it is inserted into the states
	 * ArrayList.
	 * @param s
	 */
	public void addState(String s) {
		states.add(new State(s));
	}
	
	/**
	 * GET INDEX BY NAME
	 * Returns the index of the state that has the name given as the input.
	 * @param name - the name of the state being searched
	 * @return the index of the position in which the state is. If it is -1, then the state is not in the ArrayLis.
	 */
	public int getIndexByName(String name) {
		boolean found = false;
		int index = -1;
		for(int i = 0; i < states.size() && !found; i++) {
			if(states.get(i).getName().equals(name)) {
				found = true;
				index = i;
			}
		}
		return index;
	}
	
	/**
	 * SET INPUTS
	 * Fills the ArrayList S that contains the input symbols with each char value in the char array that enters as input.
	 * @param inputs - this is the char array that contains the input symbols.
	 */
	public void setInputs(char[] inputs) {
		for(int i = 0; i < inputs.length; i++) {
			S.add(inputs[i]);
		}
	}
	
	/**
	 * SET OUTPUTS
	 * Fills the ArrayList R that contains the output symbols with each char value in the char array that enters as input.
	 * @param outputs - this is the char array that contains the output symbols.
	 */
	public void setOutputs(char[] outputs) {
		for(int i = 0; i < outputs.length; i++) {
			R.add(outputs[i]);
		}
	}
	
	/**
	 * SET TRANSITION FUNCTION
	 * Maps a state with a name given by an input to a key consisting in another state's name and an input symbol
	 * @param stateKeyName: String - the name of the state in the key for this map.
	 * @param s: char - the input symbol.
	 * @param s_successorName: Stirng - the name of the state in the value for this map.
	 */
	public void setTransitionFunction(String stateKeyName, char s, String s_successorName) {
		String key = stateKeyName + s;
		State value = states.get(getIndexByName(s_successorName));
		transition.put(key, value);
	}
	
	/**
	 * F FUNCTION
	 * This method is the transition function of the automaton. It receives the name of the current state and an input
	 * symbol, then returns the s-successor of the current state.
	 * @param name: String - the name of the current state.
	 * @param input: char - the input symbol.
	 * @return the s-successor of the current state when the automaton reads the input symbol.
	 */
	public State f(String name, char input) {
		return transition.get(name + input);
	}
	
	/**
	 * GET HASHMAP F
	 * Returns the HashMap which is the transition function f.
	 * @return f: HashMap<String, State> - the transition function f.
	 */
	public HashMap<String, State> getHashMapF(){
		return transition;
	}
	
	/**
	 * GET OUTPUT FUNCTION HASHMAP
	 * Returns the HashMap which is the output function for Mealy or Moore.
	 * @return
	 */
	public HashMap<String, Character> getOutputFunctionHashMap(){
		return outputFunction;
	}
	
	/**
	 * SET OUTPUT FUNCTION MEALY
	 * This method sets the output function of the Mealy automaton.
	 */
	public void setOutputFunctionMealy(String stateKeyName, char s, char r) {
		String key = stateKeyName + s;
		outputFunction.put(key, r);
	}
	
	/**
	 * SET OUTPUT FUNCTION MOORE
	 * This method sets the output function of the Moore automaton.
	 */
	public void setOutputFunctionMoore(String stateKeyName, char r) {
		String key = stateKeyName;
		outputFunction.put(key, r);
	}
	
	/**
	 * G FUNCTION
	 * This method is the output function of the Mealy automaton.
	 * @param name: String - the state's name
	 * @param input: char - the input symbol
	 */
	public char g(String name, char inputSymbols) {
		return outputFunction.get(name + inputSymbols);
	}
	
	/**
	 * H FUNCTION
	 * This method is the output function of the Moore automaton.
	 * @param name: String - the state's name
	 */
	public char h(String name) {
		return outputFunction.get(name);
	}
	
	/**
	 * GET INPUT ALPHABET
	 * Returns the input alphabet of the machine
	 * @return S: ArrayList<Character>
	 */
	public ArrayList<Character> getInputAlphabet(){
		return S;
	}

	/**
	 * GET OUTPUT ALPHABET
	 * Returns the output alphabet of the machine
	 * @return R: ArrayList<Character>
	 */
	public ArrayList<Character> getOutputAlphabet() {
		return R;
	}
	
	/**
	 * SET INITIAL STATE
	 * Sets the machine's initial state
	 */
	public void setInitialState() {
		initialState = states.get(0);
	}

	/**
	 * GET INITIAL STATE
	 * Returns the machine's initial state
	 * @return
	 */
	public State getInitialState() {
		return initialState;
	}
	
	/**
	 * SET STATES SET
	 * Changes the state set of this machine
	 * @param q - new state set
	 */
	public void setStatesSet(ArrayList<State> q) {
		states = q;
	}
	
	/**
	 * CREATE ACCESSIBLE STATES ARRAY
	 * Creates a temp array that will store the states that are accessible from the initial one.
	 * @return temp: ArrayList<State> - The temporary array with the accessible states from the initial state
	 */
	public ArrayList<State> createAccessibleStatesArray(){
		ArrayList<State> temp = new ArrayList<State>();
		temp.add(initialState);
		for(int i = 0; i < temp.size(); i++) {
			State q = temp.get(i);
			for(int k = 0; k < S.size(); k++) {
				State s_successor = f(q.getName(), S.get(k));
				if(!temp.contains(s_successor)) {
					temp.add(s_successor);
				}
			}
		}
		return temp;
	}
	
	
	
}

package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is the finite state machine. It could be Mealy or Moore.
 * @author johan
 * @version 1.0
 */

public class FiniteStateMachine {

	private int type;
	private String name;
	private ArrayList<State> states; // The states set Q
	private State initialState;
	private ArrayList<Character> S; // The input symbols set
	private ArrayList<Character> R; // The output symbols set
	private HashMap<String, State> f; // The transition function
	private HashMap<String, Character> g; // The output function (Mealy and Moore)
	
	public FiniteStateMachine(int type, String name) {
		this.type = type;
		this.name = name;
		states = new ArrayList<State>();
		initialState = null;
		S = new ArrayList<Character>();
		R = new ArrayList<Character>();
		f = new HashMap<String, State>();
		g = new HashMap<String, Character>();
	}

	public int getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<State> getStates(){
		return states;
	}
	
	/**
	 * This method creates a new State object taking the parameter s as its name. Then, it is inserted into the states
	 * ArrayList.
	 * @param s
	 */
	public void addState(String s) {
		states.add(new State(s));
	}
	
	/**
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
	 * Fills the ArrayList S that contains the input symbols with each char value in the char array that enters as input.
	 * @param inputs - this is the char array that contains the input symbols.
	 */
	public void setInputs(char[] inputs) {
		for(int i = 0; i < inputs.length; i++) {
			S.add(inputs[i]);
		}
	}
	
	/**
	 * Fills the ArrayList R that contains the output symbols with each char value in the char array that enters as input.
	 * @param outputs - this is the char array that contains the output symbols.
	 */
	public void setOutputs(char[] outputs) {
		for(int i = 0; i < outputs.length; i++) {
			R.add(outputs[i]);
		}
	}
	
	/**
	 * Maps a state with a name given by an input to a key consisting in another state's name and an input symbol
	 * @param stateKeyName: String - the name of the state in the key for this map.
	 * @param s: char - the input symbol.
	 * @param s_successorName: Stirng - the name of the state in the value for this map.
	 */
	public void setTransitionFunction(String stateKeyName, char s, String s_successorName) {
		String key = stateKeyName + s;
		State value = states.get(getIndexByName(s_successorName));
		f.put(key, value);
	}
	
	/**
	 * This method is the transition function of the automaton. It receives the name of the current state and an input
	 * symbol, then returns the s-successor of the current state.
	 * @param name: String - the name of the current state.
	 * @param input: char - the input symbol.
	 * @return the s-successor of the current state when the automaton reads the input symbol.
	 */
	public State transition(String name, char input) {
		return f.get(name + input);
	}
	
	/**
	 * Returns the HashMap which is the transition function f.
	 * @return f: HashMap<String, State> - the transition function f.
	 */
	public HashMap<String, State> getHashMap(){
		return f;
	}
	
	/**
	 * This method is the output function of the automaton. 
	 */
	public void setOutputFunction(String stateKeyName, char s, char r) {
		String key = stateKeyName + s;
		g.put(key, r);
	}
<<<<<<< HEAD
	
	/**
	 * Returns the input alphabet of the machine
	 * @return S: ArrayList<Character>
	 */
	public ArrayList<Character> getInputAlphabet(){
		return S;
	}

	/**
	 * Returns the output alphabet of the machine
	 * @return R: ArrayList<Character>
	 */
	public ArrayList<Character> getOutputAlphabet() {
		return R;
	}

=======
>>>>>>> dcbf625101e8e1b2484987f7f4b9f3dab7c6e97a
}

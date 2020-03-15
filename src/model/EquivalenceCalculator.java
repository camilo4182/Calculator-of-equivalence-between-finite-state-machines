package model;

import java.util.ArrayList;

/**
 * This class will be responsible for executing the partition algorithm.
 * @author johan
 *
 */

public class EquivalenceCalculator {
	
	private FiniteStateMachine m1, m2;
	private ArrayList<Character> S1, S2, R1, R2; // These are input and output alphabets of the two machines
	private ArrayList<State> accessibleStatesM1, accessibleStatesM2; // The accessible states of the two machines
	
	public EquivalenceCalculator(FiniteStateMachine m1, FiniteStateMachine m2) {
		this.m1 = m1;
		this.m2 = m2;
		S1 = null;
		S2 = null;
		R1 = null;
		R2 = null;
		accessibleStatesM1 = new ArrayList<State>();
		accessibleStatesM1.add(m1.getInitialState());
		accessibleStatesM2 = new ArrayList<State>();
		accessibleStatesM2.add(m2.getInitialState());
	}
	
	/**
	 * Sets the input and output alphabets by passing two machines as an argument.
	 * @param m1: FiniteStateMachine - the first finite state machine
	 * @param m2: FiniteStateMachine - the second finite state machine
	 */
	public void setInputAndOutputAlphabets(FiniteStateMachine m1, FiniteStateMachine m2) {
		S1 = m1.getInputAlphabet();
		S2 = m2.getInputAlphabet();
		R1 = m1.getOutputAlphabet();
		R2 = m2.getOutputAlphabet();
	}
	
	/**
	 * Verifies if the input alphabets of the two machines are equals and the output alphabets too. In other words, if
	 * S1 = S2 and R1 = R2.
	 * @return  true if the condition is met.
	 * 			false if not.
	 */
	public boolean verifyAlphabets() {
		if(verifyInputAlphabets() && verifyOutputAlphabets())
			return true;
		else
			return false;
	}
	
	/**
	 * Verifies if S1 and S2 are the same alphabet.
	 * @return  true if the condition is met.
	 * 			false if not.
	 */
	public boolean verifyInputAlphabets() {
		if(S1.equals(S2)) 
			return true;
		else
			return false;
	}
	
	/**
	 * Verifies if R1 and R2 are the same alphabet.
	 * @return  true if the condition is met.
	 * 			false if not.
	 */
	public boolean verifyOutputAlphabets() {
		if(R1.equals(R2))
			return true;
		else
			return false;
	}
	
	/**
	 * Eliminates the inaccessible states from the initial state
	 */
	public void eliminateInaccessibleStates() {
		
	}
	
	/**
	 * Partition algorithm
	 */
	public boolean PartitionAlgorithm() {
		
		return false;
	}

}

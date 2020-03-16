package model;

import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * This class will be responsible for executing the partition algorithm.
 * @author johan
 *
 */

public class EquivalenceCalculator {
	
	private FiniteStateMachine m1, m2;
	private ArrayList<Character> S1, S2, R1, R2; // These are input and output alphabets of the two machines
	private ArrayList<State> accessibleStatesM1, accessibleStatesM2; // The accessible states of the two machines
	private ArrayList<State> directSumStates; // The direct sum between M1 and M2
	private FiniteStateMachine directSumMachine;
	
	public EquivalenceCalculator(FiniteStateMachine m1, FiniteStateMachine m2) {
		this.m1 = m1;
		this.m2 = m2;
		S1 = null;
		S2 = null;
		R1 = null;
		R2 = null;
		accessibleStatesM1 = new ArrayList<State>();
		accessibleStatesM2 = new ArrayList<State>();
		directSumStates = new ArrayList<State>();
		directSumMachine = new FiniteStateMachine(m1.getType(), "Sum");
	}
	
	/**
	 * Returns the array that contains the accessible states of M1 from its initial state.
	 * @return accessibleStatesM1: ArrayList<State>
	 */
	public ArrayList<State> getAccessibleStatesM1(){
		return accessibleStatesM1;
	}
	
	/**
	 * Returns the array that contains the accessible states of M2 from its initial state.
	 * @return accessibleStatesM2: ArrayList<State>
	 */
	public ArrayList<State> getAccessibleStatesM2(){
		return accessibleStatesM2;
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
	 * Eliminates the inaccessible states from the initial state of the machine M1
	 */
	public void eliminateInaccessibleStatesFromM1() {
		accessibleStatesM1.add(m1.getInitialState());
		for(int i = 0; i < accessibleStatesM1.size(); i++) {
			State accessibleState = accessibleStatesM1.get(i);
			for(int j = 0; j < S1.size(); j++) {
				State temp = m1.transition(accessibleState.getName(), S1.get(j));
				if(!accessibleStatesM1.contains(temp))
					accessibleStatesM1.add(temp);
			}
		}
	}
	
	/**
	 * Eliminates the inaccessible states from the initial state of the machine M2
	 */
	public void eliminateInaccessibleStatesFromM2() {
		accessibleStatesM2.add(m2.getInitialState());
		for(int i = 0; i < accessibleStatesM2.size(); i++) {
			State accessibleState = accessibleStatesM2.get(i);
			for(int j = 0; j < S1.size(); j++) {
				State temp = m2.transition(accessibleState.getName(), S1.get(j));
				if(!accessibleStatesM2.contains(temp))
					accessibleStatesM2.add(temp);
			}	
		}
	}
	
	/**
	 * Changes the state's names of the two machines.
	 */
	public void changeStateNames() {
		for(int i = 0; i < accessibleStatesM1.size(); i++) {
			State s = accessibleStatesM1.get(i);
			for(int j = 0; j < accessibleStatesM2.size(); j++) {
				State t = accessibleStatesM2.get(j);
				if(s.getName().equals(t.getName())) {
					s.setName(m1.getName() + i);
					t.setName(m2.getName() + j);
				}
			}
		}
	}
	
	/**
	 * Makes the direct sum between M1 and M2
	 */
	public void directSum() {
		directSumStates.addAll(accessibleStatesM1);
		directSumStates.addAll(accessibleStatesM2);
		directSumMachine.setStatesSet(directSumStates);
		directSumMachine.getHashMapF().putAll(m1.getHashMapF());
		directSumMachine.getHashMapF().putAll(m2.getHashMapF());
		directSumMachine.getHashMapG().putAll(m1.getHashMapG());
		directSumMachine.getHashMapG().putAll(m2.getHashMapG());
	}
	
	/**
	 * Partition algorithm
	 */
	public boolean PartitionAlgorithm() {
		ArrayList<ArrayList<State>> P = firstPartition();
		
		return false;
	}
	
	/**
	 * 
	 */
	public ArrayList<ArrayList<State>> firstPartition(){
		ArrayList<ArrayList<State>> P = new ArrayList<ArrayList<State>>();
		boolean finish = false;
		while(!finish) {
			int n = -1;
			for(int i = 0; i < directSumMachine.getStates().size(); i++) {
				State s = directSumMachine.getStates().get(i);
				if(!s.isInBlock()) {
					n++;
					s.setInBlock(true);
					P.get(n).add(s);
					for(int j = i + 1; j < directSumMachine.getStates().size(); j++) {
						State t = directSumMachine.getStates().get(j);
						if(!t.isInBlock()) {
							boolean stop = false;
							for(int k = 0; k < S1.size() && !stop; k++) {
								if(directSumMachine.getType() == FiniteStateMachine.MEALY_MACHINE) {
									if(directSumMachine.g(s.getName(), S1.get(k)) != directSumMachine.g(t.getName(), S1.get(k))) {
										stop = true;
									}
								}
								else {
									if(directSumMachine.h(s.getName()) != directSumMachine.h(t.getName())) {
										stop = true;
									}
								}
							}
							if(!stop) {
								t.setInBlock(true);
								P.get(n).add(t);
							}
						}
					}
				}
			}
			finish = true;
		}
		return P;
	}
	
	
	
	
}
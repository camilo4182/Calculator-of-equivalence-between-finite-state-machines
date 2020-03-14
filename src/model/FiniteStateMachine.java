package model;

import java.util.ArrayList;

public class FiniteStateMachine {

	private String type;
	private ArrayList<State> states;
	
	public FiniteStateMachine(String type) {
		this.type = type;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
	public void addState(State newState) {
		states.add(newState);
	}
}

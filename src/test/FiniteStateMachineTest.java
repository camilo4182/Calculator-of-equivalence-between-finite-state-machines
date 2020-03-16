package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import model.*;

class FiniteStateMachineTest {
	
	private FiniteStateMachine m1;
	
	void case1() {
		m1 = new FiniteStateMachine(1, "M");
	}
	
	void case2() {
		case1();
		// Accessible states
		m1.addState("A");
		m1.addState("B");
		m1.addState("C");
		m1.addState("D");
		m1.addState("E");
		// Inaccessible states
		m1.addState("F");
		m1.addState("G");
		
		char[] inputs = {'0', '1'};
		m1.setInputs(inputs);
		char[] outputs = {'0', '1', '2', '3'};
		m1.setOutputs(outputs);
		
		m1.setInitialState();
		
		m1.setTransitionFunction("A", '0', "A");
		m1.setTransitionFunction("A", '1', "B");
		m1.setTransitionFunction("B", '0', "D");
		m1.setTransitionFunction("B", '1', "C");
		m1.setTransitionFunction("C", '0', "E");
		m1.setTransitionFunction("C", '1', "B");
		m1.setTransitionFunction("D", '0', "E");
		m1.setTransitionFunction("D", '1', "C");
		m1.setTransitionFunction("E", '0', "A");
		m1.setTransitionFunction("E", '1', "B");
		
		m1.setTransitionFunction("F", '0', "E");
		m1.setTransitionFunction("F", '1', "F");
		m1.setTransitionFunction("G", '0', "G");
		m1.setTransitionFunction("G", '1', "G");
		
		m1.setOutputFunctionMealy("A", '0', '0');
		m1.setOutputFunctionMealy("A", '1', '1');
		m1.setOutputFunctionMealy("B", '0', '3');
		m1.setOutputFunctionMealy("B", '1', '2');
		m1.setOutputFunctionMealy("C", '0', '2');
		m1.setOutputFunctionMealy("C", '1', '0');
		m1.setOutputFunctionMealy("D", '0', '1');
		m1.setOutputFunctionMealy("D", '1', '0');
		m1.setOutputFunctionMealy("E", '0', '3');
		m1.setOutputFunctionMealy("E", '1', '2');
		
		m1.setOutputFunctionMealy("F", '0', '2');
		m1.setOutputFunctionMealy("F", '1', '0');
		m1.setOutputFunctionMealy("G", '0', '1');
		m1.setOutputFunctionMealy("G", '1', '0');
	}
	
	@Test
	void addStateTest() {
		case1();
		m1.addState("A");
		m1.addState("B");
		m1.addState("C");
		m1.addState("D");
		assertEquals(4, m1.getStates().size());
	}
	
	@Test
	void getIndexByNameTest() {
		addStateTest();
		assertEquals(0, m1.getIndexByName("A"));
		assertEquals(1, m1.getIndexByName("B"));
		assertEquals(2, m1.getIndexByName("C"));
		assertEquals(3, m1.getIndexByName("D"));
		assertEquals(-1, m1.getIndexByName("F"));
	}

	@Test
	void setTransitionTest() {
		addStateTest();
		m1.setTransitionFunction("A", '0', "A");
		m1.setTransitionFunction("A", '1', "C");
		m1.setTransitionFunction("B", '0', "C");
		m1.setTransitionFunction("B", '1', "D");
		m1.setTransitionFunction("C", '0', "B");
		m1.setTransitionFunction("C", '1', "D");
		m1.setTransitionFunction("D", '0', "B");
		m1.setTransitionFunction("D", '1', "A");
		assertEquals("A", m1.f("A", '0').getName());
		assertEquals("A", m1.f("D", '1').getName());
		System.out.println("---------setTransitionTest---------");
		System.out.println(m1.getHashMapF());
	}
	
	@Test
	void setOuputFunctionTest() {
		case2();
		assertEquals('0', m1.g("A", '0'));
		assertEquals('0', m1.g("G", '1'));
		System.out.println("---------setOuputFunctionTest---------");
		System.out.println(m1.getOutputFunctionHashMap());
	}
	
	@Test
	void createAccessibleStatesArrayTest() {
		case2();
		ArrayList<State> temp = m1.createAccessibleStatesArray();
		assertEquals(5, temp.size(), "It was supposed to be 5, but it was " + temp.size());
		assertEquals("A", temp.get(0).toString());
		assertEquals("B", temp.get(1).toString());
		assertEquals("D", temp.get(2).toString());
		assertEquals("C", temp.get(3).toString());
		assertEquals("E", temp.get(4).toString());
	}

}

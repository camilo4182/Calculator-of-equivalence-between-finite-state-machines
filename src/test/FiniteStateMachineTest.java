package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.FiniteStateMachine;

class FiniteStateMachineTest {
	
	private FiniteStateMachine m1;
	
	void case1() {
		m1 = new FiniteStateMachine(0, "M");
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
		assertEquals("A", m1.transition("A", '0').getName());
		assertEquals("A", m1.transition("D", '1').getName());
		System.out.println(m1.getHashMapF());
	}

}

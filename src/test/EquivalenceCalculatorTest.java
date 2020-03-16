package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.*;

class EquivalenceCalculatorTest {
	
	private EquivalenceCalculator calc;
	private FiniteStateMachine m1, m2;
	
	void case1() {
		m1 = new FiniteStateMachine(0, "M");
		m2 = new FiniteStateMachine(0, "N");
		calc = new EquivalenceCalculator(m1, m2);
	}
	
	void case_equals_alphabets() {
		case1();
		
		char[] i1 = {'0', '1', '2'};
		m1.setInputs(i1);
		
		char[] i2 = {'0', '1', '2'};
		m2.setInputs(i2);
		
		calc.setInputAndOutputAlphabets(m1, m2);
	}
	
	void case_different_lenth() {
		case1();
		
		char[] i1 = {'0', '1'};
		m1.setInputs(i1);
		
		char[] i2 = {'0', '1', '2'};
		m2.setInputs(i2);
		
		calc.setInputAndOutputAlphabets(m1, m2);
	}
	
	void case_different_type() {
		case1();
		
		char[] i1 = {'a', 'b'};
		m1.setInputs(i1);
		
		char[] i2 = {'c', 'd'};
		m2.setInputs(i2);
		
		calc.setInputAndOutputAlphabets(m1, m2);
	}
	
	void case_inaccessible_states() {
		case_different_type();
		m1.addState("A");
		m1.addState("B");
		m1.addState("C");
		m1.addState("D");
		m1.addState("E");
		m1.addState("F");
		m1.addState("G");
		m1.setInitialState();
		// Accessible states
		m1.setTransitionFunction("A", 'a', "A");
		m1.setTransitionFunction("A", 'b', "B");
		m1.setTransitionFunction("B", 'a', "D");
		m1.setTransitionFunction("B", 'b', "C");
		m1.setTransitionFunction("C", 'a', "E");
		m1.setTransitionFunction("C", 'b', "B");
		m1.setTransitionFunction("D", 'a', "E");
		m1.setTransitionFunction("D", 'b', "C");
		m1.setTransitionFunction("E", 'a', "A");
		m1.setTransitionFunction("E", 'b', "B");
		// Inaccessible states
		m1.setTransitionFunction("F", 'a', "E");
		m1.setTransitionFunction("F", 'b', "F");
		m1.setTransitionFunction("G", 'a', "G");
		m1.setTransitionFunction("G", 'b', "G");
	}
	
	void case_states_with_same_names() {
		case1();
		
		m1.addState("A");
		m1.addState("B");
		m1.addState("C");
		m1.addState("D");
		
		m2.addState("E");
		m2.addState("C");
		m2.addState("A");
		
		m1.setInitialState();
		m2.setInitialState();
		
		char[] inputs = {'0', '1'};
		m1.setInputs(inputs);
		m2.setInputs(inputs);
		calc.setInputAndOutputAlphabets(m1, m2);
		
		m1.setTransitionFunction("A", '0', "A");
		m1.setTransitionFunction("A", '1', "B");
		m1.setTransitionFunction("B", '0', "C");
		m1.setTransitionFunction("B", '1', "D");
		m1.setTransitionFunction("C", '0', "C");
		m1.setTransitionFunction("C", '1', "B");
		m1.setTransitionFunction("D", '0', "D");
		m1.setTransitionFunction("D", '1', "A");
		
		m2.setTransitionFunction("E", '0', "C");
		m2.setTransitionFunction("E", '1', "A");
		m2.setTransitionFunction("C", '0', "E");
		m2.setTransitionFunction("C", '1', "A");
		m2.setTransitionFunction("A", '0', "C");
		m2.setTransitionFunction("A", '1', "E");
	}
	
	@Test
	void inputAlphabetsTest() {
		case_equals_alphabets();
		assertEquals(3, m1.getInputAlphabet().size());
		assertEquals('0', m1.getInputAlphabet().get(0));
		assertEquals('1', m1.getInputAlphabet().get(1));
		assertEquals('2', m1.getInputAlphabet().get(2));
	}

	@Test
	void verifyInputAlphabetsTest() {
		case_equals_alphabets();
		assertTrue(calc.verifyInputAlphabets());
		
		case_different_lenth();
		assertFalse(calc.verifyInputAlphabets());
		
		case_different_type();
		assertFalse(calc.verifyInputAlphabets());
	}
	
	@Test
	void eliminateInaccessibleStatesFromM1Test() {
		case_inaccessible_states();
		assertEquals(0, calc.getAccessibleStatesM1().size());
		calc.eliminateInaccessibleStatesFromM1();
		assertEquals(5, calc.getAccessibleStatesM1().size());
		assertTrue(calc.getAccessibleStatesM1().contains(m1.getStates().get(m1.getIndexByName("A"))));
		assertTrue(calc.getAccessibleStatesM1().contains(m1.getStates().get(m1.getIndexByName("B"))));
		assertTrue(calc.getAccessibleStatesM1().contains(m1.getStates().get(m1.getIndexByName("C"))));
		assertTrue(calc.getAccessibleStatesM1().contains(m1.getStates().get(m1.getIndexByName("D"))));
		assertTrue(calc.getAccessibleStatesM1().contains(m1.getStates().get(m1.getIndexByName("E"))));
		assertFalse(calc.getAccessibleStatesM1().contains(m1.getStates().get(m1.getIndexByName("F"))));
		assertFalse(calc.getAccessibleStatesM1().contains(m1.getStates().get(m1.getIndexByName("G"))));
	}
	
	@Test
	void changeStateNamesTest() {
		case_states_with_same_names();
		
		calc.eliminateInaccessibleStatesFromM1();
		calc.eliminateInaccessibleStatesFromM2();
		
		assertEquals("A", m1.getStates().get(0).getName());
		assertEquals("B", m1.getStates().get(1).getName());
		assertEquals("C", m1.getStates().get(2).getName());
		assertEquals("D", m1.getStates().get(3).getName());
		
		assertEquals("E", m2.getStates().get(0).getName());
		assertEquals("C", m2.getStates().get(1).getName());
		assertEquals("A", m2.getStates().get(2).getName());
		
		calc.changeStateNames();
		
		assertEquals("M0", m1.getStates().get(0).getName());
		assertEquals("B", m1.getStates().get(1).getName());
		assertEquals("M2", m1.getStates().get(2).getName());
		assertEquals("D", m1.getStates().get(3).getName());
		
		assertEquals("E", m2.getStates().get(0).getName());
		assertEquals("N1", m2.getStates().get(1).getName());
		assertEquals("N2", m2.getStates().get(2).getName());
	}

}

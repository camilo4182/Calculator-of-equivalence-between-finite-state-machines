package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.*;

class EquivalenceCalculatorTest {
	
	private EquivalenceCalculator calc;
	private FiniteStateMachine m1, m2;
	
	void case1() {
		m1 = new FiniteStateMachine(0, "M1");
		m2 = new FiniteStateMachine(0, "M2");
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
	

}

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeBookTester {

	GradeBook gb1 = new GradeBook(5);
	GradeBook gb2 = new GradeBook(5);
	
	@BeforeEach
	void setUp() throws Exception {	
		gb1.addScore(4);
		gb1.addScore(5);
		gb1.addScore(6);
		
		gb2.addScore(100);
		gb2.addScore(10);
	}

	@AfterEach
	void tearDown() throws Exception {
		gb1 = null;
		gb2 = null;
	}

	@Test
	void testAddScore() {
		assertTrue(gb1.toString().equals("4.0 5.0 6.0 "));
		assertEquals(3, gb1.getScoreSize(), 0.01);
		assertTrue(gb2.toString().equals("100.0 10.0 "));
		assertEquals(2, gb2.getScoreSize(), 0.01);
	}

	@Test
	void testSum() {
		assertEquals(15.0, gb1.sum(), 0.01);
		assertEquals(110.0, gb2.sum(), 0.01);
	}

	@Test
	void testMinimum() {
		assertEquals(4.0, gb1.minimum(), 0.01);
		assertEquals(10.0, gb2.minimum(), 0.01);
	}

	@Test
	void testFinalScore() {
		assertEquals(11.0, gb1.finalScore(), 0.01);
		assertEquals(100.0, gb2.finalScore(), 0.01);
	}

}

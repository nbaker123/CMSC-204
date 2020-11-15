import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MorseCodeTreeTest {

	MorseCodeTree tree;
	
	@BeforeEach
	void setUp() throws Exception {
		tree = new MorseCodeTree();
	}

	@AfterEach
	void tearDown() throws Exception {
		tree = null;
	}

	@Test
	void testFetch() {
		
		/*
		 * NOTE: TESTING fetch SERVES AS A TEST
		 * FOR fetchNode SINCE THE ONLY THING
		 * IT DOES IS CALL fetchNode
		 */
		assertEquals("a", tree.fetch(".-"));
		assertEquals("b", tree.fetch("-..."));
		assertEquals("c", tree.fetch("-.-."));
		assertEquals("d", tree.fetch("-.."));
		assertEquals("e", tree.fetch("."));
		assertEquals("f", tree.fetch("..-."));
		assertEquals("g", tree.fetch("--."));
		assertEquals("h", tree.fetch("...."));
		assertEquals("i", tree.fetch(".."));
		assertEquals("j", tree.fetch(".---"));
		assertEquals("k", tree.fetch("-.-"));
		assertEquals("l", tree.fetch(".-.."));
		assertEquals("m", tree.fetch("--"));
		assertEquals("n", tree.fetch("-."));
		assertEquals("o", tree.fetch("---"));
		assertEquals("p", tree.fetch(".--."));
		assertEquals("q", tree.fetch("--.-"));
		assertEquals("r", tree.fetch(".-."));
		assertEquals("s", tree.fetch("..."));
		assertEquals("t", tree.fetch("-"));
		assertEquals("u", tree.fetch("..-"));
		assertEquals("v", tree.fetch("...-"));
		assertEquals("w", tree.fetch(".--"));
		assertEquals("x", tree.fetch("-..-"));
		assertEquals("y", tree.fetch("-.--"));
		assertEquals("z", tree.fetch("--.."));
		assertEquals("", tree.fetch(""));
	}

	@Test
	void testDelete() {
		try {
			tree.delete("e");
		}
		catch(UnsupportedOperationException e) {
			assertTrue(true, "It passed!");
		}
		catch(Exception e) {
			e.printStackTrace();
			assertTrue(false, "It threw a different exception");
		}
	}

	@Test
	void testUpdate() {
		try {
			tree.update();
		}
		catch(UnsupportedOperationException e) {
			assertTrue(true, "It passed!");
		}
		catch(Exception e) {
			e.printStackTrace();
			assertTrue(false, "It threw a different exception");
		}
	}

	@Test
	void testInsert() {
		
		/*
		 * NOTE: TESTING insert SERVES AS A TEST
		 * FOR addNode SINCE THE ONLY THING
		 * IT DOES IS CALL addNode
		 */
		tree.insert(".....", "uiop");
		tree.insert(".-...", "testString");
		
		//Assuming that fetch() already works
		assertEquals("uiop", tree.fetch("....."));
		assertEquals("testString", tree.fetch(".-..."));
	}

	@Test
	void testToArrayList() {
		/*
		 * NOTE: TESTING toArrayList SERVES AS A TEST
		 * FOR buildTree and LNRoutpurTraversal because
		 * it is used to test buildTre, and LNRoutputTraversal
		 * is used to complete the function of this method.
		 */
		ArrayList<String> resultList = tree.toArrayList();
		String resultString = "";
		String correctResult = "h s v i f u e l r a p w j  b d x n c k y t z g q m o";
		
		for(int i = 0; i < resultList.size(); i++)
			resultString += resultList.get(i) + " ";
		
		resultString = resultString.trim(); //Getting rid of the extra space at the end
		assertEquals(correctResult, resultString);
	}

}

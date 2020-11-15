import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MorseCodeConverterTest_STUDENT {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testPrintTree() {
		String correctResult = "h s v i f u e l r a p w j  b d x n c k y t z g q m o";
		String s = MorseCodeConverter.printTree();
		s = s.trim(); // take off preceding or succeeding spaces
		assertEquals(correctResult, s);
	}

	@Test
	void testConvertToEnglishString() {
		String result1 = MorseCodeConverter.convertToEnglish("- .... .. ... / .. ... / - .... . "
				+ "/ ..-. .. .-. ... - / -- --- .-. ... . / -.-. --- -.. . / - . ... -");
		
		String result2 = MorseCodeConverter.convertToEnglish("-- -.-- / -. .- -- . / .. ... / "
				+ "-. .. -.-. --- / .- -. -.. / - .... .. ... / .. ... / -- -.-- "
				+ "/ .--. .-. --- --. .-. .- --");
		
		assertEquals("this is the first morse code test", result1);
		assertEquals("my name is nico and this is my program", result2);
	}

	@Test
	void testConvertToEnglishFile() {
		String result1 = MorseCodeConverter.convertToEnglish(new File("firstFileTest.txt"));
		String result2 = MorseCodeConverter.convertToEnglish(new File("secondFileTest.txt"));
		
		assertEquals("this is the first file test", result1);
		assertEquals("may those driven by morals be blessed with strength and "
				+ "impartiality and those by temptations with compassion and forbearance", result2);
	}

}

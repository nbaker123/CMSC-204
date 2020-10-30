import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseDBManager_STUDENT_Test {

	CourseDBManager manager;
	
	@BeforeEach
	void setUp() throws Exception {
		manager = new CourseDBManager();
	}

	@AfterEach
	void tearDown() throws Exception {
		manager = null;
	}

	@Test
	void testAdd() {
		manager.add("CMSC203", 20976, 3, "SC444", "Bob");
		assertEquals("\nCourse:CMSC203 CRN:20976 Credits:3 Instructor:Bob Room:SC444", 
				manager.database.hashTable[4].get(0).toString());
		manager.add("CMSC204", 20876, 3, "SC445", "Bobby");
		assertEquals("\nCourse:CMSC204 CRN:20876 Credits:3 Instructor:Bobby Room:SC445", 
				manager.database.hashTable[3].get(0).toString());
	}

	@Test
	void testGet() {
		manager.add("CMSC203", 20976, 3, "SC444", "Bob");
		manager.add("CMSC204", 20876, 3, "SC445", "Bobby");
		assertEquals("\nCourse:CMSC203 CRN:20976 Credits:3 Instructor:Bob Room:SC444", 
				manager.get(20976).toString());
		assertEquals("\nCourse:CMSC204 CRN:20876 Credits:3 Instructor:Bobby Room:SC445", 
				manager.get(20876).toString());
	}

	@Test
	void testReadFile() {
		try {
			manager.readFile(new File("courses.txt"));
		}
		catch(Exception e) {
			e.printStackTrace();
			fail("Should not have thrown an exception");
		}
		assertEquals("\nCourse:CMSC100 CRN:21556 Credits:2 Instructor:Janet E. Joy Room:Distance-Learning", 
				manager.get(21556).toString());
		assertEquals("\nCourse:CMSC100 CRN:22344 Credits:2 Instructor:Gloria E. Barron Room:SW217", 
				manager.get(22344).toString());
		assertEquals("\nCourse:CMSC100 CRN:22974 Credits:2 Instructor:Janet E. Joy Room:Distance-Learning", 
				manager.get(22974).toString());
		assertEquals("\nCourse:CMSC110 CRN:21561 Credits:3 Instructor:Rabiha J. Kayed Room:SC451", 
				manager.get(21561).toString());
		assertEquals("\nCourse:CMSC110 CRN:20484 Credits:3 Instructor:Madhvi A. Shah Room:HT300", 
				manager.get(20484).toString());
		assertEquals("\nCourse:CMSC110 CRN:23363 Credits:3 Instructor:Sascha Simkanich Room:SC451", 
				manager.get(23363).toString());
		assertEquals("\nCourse:CMSC110 CRN:21565 Credits:3 Instructor:Janet E. Joy Room:Distance Learning", 
				manager.get(21565).toString());
		assertEquals("\nCourse:CMSC110 CRN:21564 Credits:3 Instructor:Behzad Maghami Room:SC451", 
				manager.get(21564).toString());
		assertEquals("\nCourse:CMSC110 CRN:21560 Credits:3 Instructor:Behzad Maghami Room:SC450", 
				manager.get(21560).toString());
	}

	@Test
	void testShowAll() {
		try {
			manager.readFile(new File("courses.txt"));
		}
		catch(Exception e) {
			e.printStackTrace();
			fail("Should not have thrown an exception");
		}
		ArrayList<String> testList = manager.showAll();
		
		assertEquals("\nCourse:CMSC110 CRN:20484 Credits:3 Instructor:Madhvi A. Shah Room:HT300", 
				testList.get(0).toString());
		assertEquals("\nCourse:CMSC100 CRN:21556 Credits:2 Instructor:Janet E. Joy Room:Distance-Learning", 
				testList.get(1).toString());
		assertEquals("\nCourse:CMSC110 CRN:21560 Credits:3 Instructor:Behzad Maghami Room:SC450", 
				testList.get(2).toString());
		assertEquals("\nCourse:CMSC110 CRN:21561 Credits:3 Instructor:Rabiha J. Kayed Room:SC451", 
				testList.get(3).toString());
		assertEquals("\nCourse:CMSC110 CRN:21564 Credits:3 Instructor:Behzad Maghami Room:SC451", 
				testList.get(4).toString());
		assertEquals("\nCourse:CMSC110 CRN:21565 Credits:3 Instructor:Janet E. Joy Room:Distance Learning", 
				testList.get(5).toString());
		assertEquals("\nCourse:CMSC100 CRN:22344 Credits:2 Instructor:Gloria E. Barron Room:SW217", 
				testList.get(6).toString());
		assertEquals("\nCourse:CMSC100 CRN:22974 Credits:2 Instructor:Janet E. Joy Room:Distance-Learning", 
				testList.get(7).toString());
		assertEquals("\nCourse:CMSC110 CRN:23363 Credits:3 Instructor:Sascha Simkanich Room:SC451", 
				testList.get(8).toString());
	}

}

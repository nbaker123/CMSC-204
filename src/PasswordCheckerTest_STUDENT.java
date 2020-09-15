import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Nicholas A. Baker
 *
 */
public class PasswordCheckerTest_STUDENT {

	ArrayList<String> passwords;
	ArrayList<String> invalids;
	
	@Before
	public void setUp() throws Exception {
		passwords = PasswordCheckerUtility.readFile("passwords");
		invalids = PasswordCheckerUtility.getInvalidPasswords(passwords);
	}

	@After
	public void tearDown() throws Exception {
		passwords = null;
		invalids = null;
	}

	/**
	 * Test if the password is less than 8 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("He1lodud3$"));
			PasswordCheckerUtility.isValidPassword("hello");
			assertTrue("Did not throw a LengthException",false);
		}
		catch(LengthException e) {
			assertTrue("Successfully threw a LengthException",true);
		}
		catch(Exception e) {
			assertTrue("Threw a different Exception", false);
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("He1lodud3tt#$"));
			PasswordCheckerUtility.isValidPassword("he1lodud3tt#$");
			assertTrue("Did not throw a NoUpperAlphaException",false);
		}
		catch(NoUpperAlphaException e) {
			assertTrue("Successfully threw a NoUpperAlphaException",true);
		}
		catch(Exception e) {
			assertTrue("Threw a different Exception", false);
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("G00dby3bro$"));
			PasswordCheckerUtility.isValidPassword("HELLOWOR1D");
			assertTrue("Did not throw a NoLowerAlphaException",false);
		}
		catch(NoLowerAlphaException e) {
			assertTrue("Successfully threw a NoLowerAlphaException",true);
		}
		catch(Exception e) {
			assertTrue("Threw a different Exception", false);
		}
	}
	/**
	 * Test if the password is weak (6 <= length <= 9)
	 * This test should not throw any exceptions, and the second case should be weak
	 */
	@Test
	public void testIsWeakPassword()
	{
		try{
			assertTrue(PasswordCheckerUtility.isValidPassword("all4D$$!!."));
			boolean weakPwd = PasswordCheckerUtility.isWeakPassword("all4D$$");
			assertTrue(weakPwd);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			assertTrue("Threw some incorrect exception",false);
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("G00dby3bro$kys"));
			PasswordCheckerUtility.isValidPassword("G000dby3bro$kys");
			assertTrue("Did not throw a InvalidSequenceException",false);
		}
		catch(InvalidSequenceException e) {
			assertTrue("Successfully threw a InvalidSequenceException",true);
		}
		catch(Exception e) {
			assertTrue("Threw a different Exception", false);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("P@ssw0rd!!"));
			PasswordCheckerUtility.isValidPassword("P@sswrd!!");
			assertTrue("Did not throw a NoDigitException",false);
		}
		catch(NoDigitException e) {
			assertTrue("Successfully threw a NoDigitException",true);
		}
		catch(Exception e) {
			assertTrue("Threw a different Exception", false);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("P@ssw0rd!!"));
			assertTrue(PasswordCheckerUtility.isValidPassword("T3$tc@se!!2"));
			assertTrue(PasswordCheckerUtility.isValidPassword("#j9oRYxv"));
			assertTrue(PasswordCheckerUtility.isValidPassword("ASD45thn#"));
			assertTrue(PasswordCheckerUtility.isValidPassword("!@#LKJpo9"));
			
		}catch(Exception e) {
			assertTrue("One of the passwords is invalid, or your code is wrong", false);
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		
		//The readFile and getInvalidPasswords methods have already been run in the setUp method
		assertTrue(invalids.get(0).contains("334455BB"));
		assertTrue(invalids.get(1).contains("george2ZZZ"));
		assertTrue(invalids.get(2).contains("4Sal#"));
		assertTrue(invalids.get(3).contains("bertha22"));
		assertTrue(invalids.get(4).contains("august30"));
		assertTrue(invalids.get(5).contains("a2cD#"));
		assertTrue(invalids.get(6).contains("ApplesxxYYzz"));
		assertTrue(invalids.get(7).contains("aa11#"));
		assertTrue(invalids.get(8).contains("pilotProject"));
		assertTrue(invalids.get(9).contains("myPassword"));
	}
	
}
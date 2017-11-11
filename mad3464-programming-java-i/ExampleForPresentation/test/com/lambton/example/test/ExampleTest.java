
package com.lambton.example.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExampleTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Executed one time before all test methods");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("Executed one time before each test method");
	}

	@Test
	public void testMethodOne() throws Exception {
		// GIVEN
		// Data test Initializations

		// THEN
		System.out.println("Execute test method one");

		// ASSERT
		assertEquals("message", "tests", "tests");
	}
	
	@Test
	public void testMethodTwo() throws Exception {
		// GIVEN
		// Data test Initializations

		// THEN
		System.out.println("Execute test method two");

		// ASSERT
		assertEquals("message", "tests", "tests");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Executed one time after each test method");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Executed one time after all test methods");
	}

}

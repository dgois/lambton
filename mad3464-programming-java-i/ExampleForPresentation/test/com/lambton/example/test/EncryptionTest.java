package com.lambton.example.test;

import com.lambton.example.MemoryDatabase;
import com.lambton.example.Encryption;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

public class EncryptionTest {
	
	private Encryption encryption;
	
	@Before
	public void setUp() throws Exception {
		encryption = new Encryption(new MemoryDatabase());
	}
	
	@Test
	public void shouldEncryptTheMessage() throws Exception {
		// GIVEN
		String message = "I am at lambton college, Toronto";
		String key = "SECRET";
		
		// THEN
		String encryptedMessage = encryption.encrypt(message, key);
		
		// ASSERT
		// Junit Assertions
		assertEquals("The message was encrypted", "alneo   olTo mceo ma gr Ittl tabo,n ", encryptedMessage);
		
		//Hamcrest Assertions
		assertThat(encryptedMessage, is(equalTo("alneo   olTo mceo ma gr Ittl tabo,n ")));
	}
	
	@Test
	public void shouldEncryptADifferentMessage() throws Exception {
		// GIVEN
		String message = "Hello, Lambton";
		String key = "PARO";
		
		// THEN
		String encryptedMessage = encryption.encrypt(message, key);
		
		// ASSERT
		// Junit Assertions
		assertEquals("The message was encrypted", "e,mnlLt Hoaol b ", encryptedMessage);
		
		//Hamcrest Assertions
		assertThat(encryptedMessage, is(equalTo("e,mnlLt Hoaol b ")));
	}

}

/* Hamcrest mathers

allOf - matches if all matchers match (short circuits)

anyOf - matches if any matchers match (short circuits)

not - matches if the wrapped matcher doesn’t match and vice

equalTo - test object equality using the equals method

is - decorator for equalTo to improve readability

hasToString - test Object.toString

instanceOf, isCompatibleType - test type

notNullValue, nullValue - test for null

sameInstance - test object identity

hasEntry, hasKey, hasValue - test a map contains an entry, key or value

hasItem, hasItems - test a collection contains elements

hasItemInArray - test an array contains an element

closeTo - test floating point values are close to a given value

greaterThan, greaterThanOrEqualTo, lessThan, lessThanOrEqualTo

equalToIgnoringCase - test string equality ignoring case

equalToIgnoringWhiteSpace - test string equality ignoring differences in runs of whitespace

containsString, endsWith, startsWith - test string matching

*/

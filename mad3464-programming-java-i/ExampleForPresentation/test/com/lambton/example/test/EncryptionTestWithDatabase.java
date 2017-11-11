package com.lambton.example.test;

import com.lambton.example.EncryptionDatabase;
import com.lambton.example.Encryption;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

//@RunWith(MockitoJUnitRunner.class)
public class EncryptionTestWithDatabase {
	
	@Mock EncryptionDatabase database;
	
	@InjectMocks private Encryption encryption;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldEncryptTheMessage() throws Exception {
		// GIVEN
                when(database.save(anyString())).thenReturn(Boolean.TRUE);
		String message = "I am at lambton college, Toronto";
		String key = "SECRET";
		
		// THEN
		String encryptedMessage = encryption.encrypt(message, key);
		
		// ASSERT
		// Junit Assertions
		assertEquals("The message was encrypted", "alneo   olTo mceo ma gr Ittl tabo,n ", encryptedMessage);
		
		//Hamcrest Assertions
		assertThat(encryptedMessage, is(equalTo("alneo   olTo mceo ma gr Ittl tabo,n ")));
		
		//Mockito verify
		verify(database).save(encryptedMessage);
	}
	
	@Test
	public void shouldEncryptADifferentMessage() throws Exception {
		// GIVEN
                when(database.save(anyString())).thenReturn(Boolean.TRUE);
		String message = "Hello, Lambton";
		String key = "PARO";
		
		// THEN
		String encryptedMessage = encryption.encrypt(message, key);
		
		// ASSERT
		// Junit Assertions
		assertEquals("The message was encrypted", "e,mnlLt Hoaol b ", encryptedMessage);
		
		//Hamcrest Assertions
		assertThat(encryptedMessage, is(equalTo("e,mnlLt Hoaol b ")));
                
                //Mockito Verify
                verify(database, times(1)).save(encryptedMessage);
	}
        
        @Test(expected=RuntimeException.class)
	public void shouldThrowRunTimeException() throws Exception {
		// GIVEN
                when(database.save(anyString())).thenReturn(Boolean.FALSE);
                
		String message = "Hello, Lambton";
		String key = "PARO";
		
		// THEN
		encryption.encrypt(message, key);
                
                verify(database, times(0)).save(anyString());
	}
        

}

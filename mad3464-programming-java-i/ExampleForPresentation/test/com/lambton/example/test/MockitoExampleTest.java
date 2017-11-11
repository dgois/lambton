package com.lambton.example.test;

import static org.mockito.Mockito.*;
import java.util.List;

import org.junit.Test;

public class MockitoExampleTest {
	
	@Test
	public void testMockitoConfiguraion() throws Exception {
		// mock creation
		List mockedList = mock(List.class);

		// using mock object - it does not throw any "unexpected interaction" exception
		mockedList.add("one");
		mockedList.clear();

		// selective, explicit, highly readable verification
		verify(mockedList).add("one");
		verify(mockedList).clear();
	}

}

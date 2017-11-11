package com.lambton.example;

public class MemoryDatabase implements EncryptionDatabase {
	
	private static String message = "";

	@Override
	public boolean save(String encriptedMessage) {
		MemoryDatabase.message = encriptedMessage;
                return true;
	}

}

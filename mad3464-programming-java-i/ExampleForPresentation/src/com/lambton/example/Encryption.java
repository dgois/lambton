/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lambton.example;

import java.util.Arrays;

/**
 *
 * @author macstudent
 */
public class Encryption {
    
    private EncryptionDatabase database;
    
    public Encryption() {
        
    }
    
    public Encryption(EncryptionDatabase database) {
        this.database = database;
    }
    
    public static void main(String[] args) {
        String data = "Hello Lambton";
        
        String encryptData = new Encryption().encrypt(data, "PARO");
        System.out.println(encryptData);
        String dencryptData = new Encryption().dencrypt(encryptData, "PARO");
        System.out.println(dencryptData);
    }
    
    public String encrypt(String data, String key) {
    	Key[] keyArray = getKeyArray(key);
    	
        char[] dataArray = data.toCharArray();
        int rowsQuantity = calculateRowsQuantity(data, key);
        
        char[][] dataMatrix = new char[key.length()][rowsQuantity];
        
        // Fill matrix with phrase
        int pos = 0;
        for (int i = 0; i < rowsQuantity; i++) {
            for (int j = 0; j < key.length(); j++, pos++) {
                if (pos >= data.length()) {
                    dataMatrix[j][i] = ' ';
                } else {
                    dataMatrix[j][i] = dataArray[pos];
                }
            }
        }
        
        // Create the message to return
        StringBuilder sb = new StringBuilder();
        for (int i = 0, col = 0; i < keyArray.length; i++) {
        	col = keyArray[i].pos;
            for (int j = 0; j < rowsQuantity; j++, pos++) {
                sb.append(dataMatrix[col][j]);
            }
        }
       
//        boolean saved = database.save(sb.toString());
//        if (!saved) {
//            throw new RuntimeException("The message was not saved!");
//        }
        
        return sb.toString();
    }
    
    public String dencrypt(String data, String key) {
    	Key[] keyArray = getKeyArray(key);
        
        char[] dataArray = data.toCharArray();
        int rowsQuantity = calculateRowsQuantity(data, key);
        
        char[][] dataMatrix = new char[key.length()][rowsQuantity];
        
        // Create the matrix to read the message
        int pos = 0;
        for (int i = 0; i < keyArray.length; i++) {
        	int col = keyArray[i].pos;
            for (int j = 0; j < rowsQuantity; j++, pos++) {
            	dataMatrix[col][j] = dataArray[pos];
            }
        }
        
        //printMatrix(dataMatrix);
        
        // Create the message
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rowsQuantity; i++) {
            for (int j = 0; j < keyArray.length; j++, pos++) {
                sb.append(dataMatrix[j][i]);
            }
        }	
        
        return sb.toString();
    }
    
    private Key[] getKeyArray(String key) {
        Key[] keyArray = new Key[key.length()];
    	for (int i = 0; i < key.toCharArray().length; i++) {
            keyArray[i] = new Key(key.charAt(i), i);
        }
    	Arrays.sort(keyArray);
        return keyArray;
    }
    
    private int calculateRowsQuantity(String data, String key) {
        int rowsQuantity = data.length() / key.length();
        if (data.length() % key.length() != 0) {
            rowsQuantity = rowsQuantity + 1;
        }
        return rowsQuantity;
    }
    
    public void printMatrix(char[][] matrix) {
        for(int i = 0; i < matrix[0].length; i++) {
            for(int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[j][i]);
            }
            System.out.println("");
        }
    }
    
}

class Key implements Comparable<Key> {
    
    char character;
    int pos;
    
    public Key(char c, int p) {
    	character = c;
    	pos = p;
    }

	@Override
	public int compareTo(Key o) {
		if (this.character > o.character) return 1;
		else if (this.character < o.character) return -1;
		else return 0;
	}
	
	@Override
	public String toString() {
		return character + " : " + pos;
	}
}

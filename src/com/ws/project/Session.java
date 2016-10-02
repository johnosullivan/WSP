package com.ws.project;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class Session {
	static Cipher cipher;
	private static Session instance = null;
	public static Session getInstance(){
	  if(instance==null){ instance = new Session(); }
	  return instance;
	}
	public boolean login(String username, String password) throws Exception {
		Database db = Database.getInstance();		
		String pass = db.findUser(username);
		if (pass.equals(password)) {
			return true;
		}
		return false;
	}
	public boolean register(String username, String password, String email, String phone, String age,String displayname) throws Exception {
		Database db = Database.getInstance();
		return db.registerUser(username, password, email);
	}
	public static String encrypt(String plainText, SecretKey secretKey)
			throws Exception {
		byte[] plainTextByte = plainText.getBytes();
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedByte = cipher.doFinal(plainTextByte);
		Base64.Encoder encoder = Base64.getEncoder();
		String encryptedText = encoder.encodeToString(encryptedByte);
		return encryptedText;
	}
	public static String decrypt(String encryptedText, SecretKey secretKey)
			throws Exception {
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] encryptedTextByte = decoder.decode(encryptedText);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
		String decryptedText = new String(decryptedByte);
		return decryptedText;
	}	
}

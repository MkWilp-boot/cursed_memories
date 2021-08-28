package com.projekt.CursedMemories.main;

import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Encryptor {
	
	public static final String UNICODE_FORMAT = "UTF8";
	public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
	
	private KeySpec keySpec;
	private SecretKeyFactory sectreKeyFactory;
	private Cipher cipher; 
	private String mEncryptionKey;
	private String mEncryptionScheme;
	private byte[] bytes;
	private SecretKey secretKey;
	
	public Encryptor() throws Exception {
		mEncryptionKey = "ThisIsSpartaThisIsSparta";
		mEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        bytes = mEncryptionKey.getBytes(UNICODE_FORMAT);
        keySpec = new DESedeKeySpec(bytes);
        sectreKeyFactory = SecretKeyFactory.getInstance(mEncryptionScheme);
        cipher = Cipher.getInstance(mEncryptionScheme);
        secretKey = sectreKeyFactory.generateSecret(keySpec);
	}
	
	public String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }


    public String decrypt(String encryptedString) {
        String decryptedText=null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] encryptedText = Base64.decodeBase64(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }
}

package com.mx.Catalago.service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Service;
import java.util.Base64;

@Service
public class EncryptionService {

	private static final String ALGORITHM = "AES";
	private static final String MODE = "CBC";
	private static final String PADDING = "PKCS5Padding";

	// Clave y vector de inicializacion
	private static final String SECRET_KEY = "1234567890123456";
	private static final String IV = "1234567890123456";

	public String encrypt(String plainText) throws Exception {
		SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
		IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes());

		Cipher cipher = Cipher.getInstance(ALGORITHM + "/" + MODE + "/" + PADDING);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

		byte[] encrypted = cipher.doFinal(plainText.getBytes());
		return Base64.getEncoder().encodeToString(encrypted);
	}
}

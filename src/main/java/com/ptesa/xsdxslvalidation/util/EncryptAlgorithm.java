package com.ptesa.xsdxslvalidation.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptAlgorithm {

    public static String encrypt(String plainText, String encryptionKeyString) {
        try {
            // Must use AES encryption algorithm with a 128-bit key
            Key key = new SecretKeySpec(encryptionKeyString.getBytes(), 0, 16, "AES");
            Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
            aes.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptTextByte = aes.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encryptTextByte);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String decrypt(String encryptText, String encryptionKeyString) {
        try {
            // Must use AES encryption algorithm with a 128-bit key
            Key key = new SecretKeySpec(encryptionKeyString.getBytes(), 0, 16, "AES");
            Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
            aes.init(Cipher.DECRYPT_MODE, key);
            byte[] plainTextByte = aes.doFinal(Base64.getDecoder().decode(encryptText));
            return new String(plainTextByte, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException ex) {
            throw new RuntimeException(ex);
        }
    }
}

package com.ptesa.xsdxslvalidation.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class EncryptAlgorithmV2 {

    private final static int GCM_IV_LENGTH = 12;
    private final static int GCM_TAG_LENGTH = 16;

    public static String encrypt(String plainText, String encryptionKeyString) {
        try {
            // Must use AES encryption algorithm with a 128-bit key
            Key key = new SecretKeySpec(encryptionKeyString.getBytes(), 0, 16, "AES");

            byte[] iv = new byte[GCM_IV_LENGTH];
            (new SecureRandom()).nextBytes(iv);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec ivSpec = new GCMParameterSpec(GCM_TAG_LENGTH * Byte.SIZE, iv);
            cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

            byte[] encryptTextByte = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            byte[] encrypted = new byte[iv.length + encryptTextByte.length];
            System.arraycopy(iv, 0, encrypted, 0, iv.length);
            System.arraycopy(encryptTextByte, 0, encrypted, iv.length, encryptTextByte.length);
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                BadPaddingException | InvalidAlgorithmParameterException ex) {
//            throw new RuntimeException(ex);
            System.err.println("CAN NOT ENCRYPT KEY...");
            return "";
        }

    }

    public static String decrypt(String encryptText, String encryptionKeyString) {
        try {
            byte[] decoded = Base64.getDecoder().decode(encryptText);
            byte[] iv = Arrays.copyOfRange(decoded, 0, GCM_IV_LENGTH);

            // Must use AES encryption algorithm with a 128-bit key
            Key key = new SecretKeySpec(encryptionKeyString.getBytes(), 0, 16, "AES");

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec ivSpec = new GCMParameterSpec(GCM_TAG_LENGTH * Byte.SIZE, iv);
            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
            byte[] plainTextByte = cipher.doFinal(decoded, GCM_IV_LENGTH, decoded.length - GCM_IV_LENGTH);
            return new String(plainTextByte, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                BadPaddingException | InvalidAlgorithmParameterException ex) {
            System.err.println("CAN NOT DECRYPT KEY...");
            return "";
//            throw new RuntimeException(ex);
        }
    }
}

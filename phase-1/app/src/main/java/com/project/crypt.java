package com.project;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * this class will handle all encryption and decryption of data
 * general hashing will use the SHA-256 algorithm and
 * keyed encryption will use the AES algorithm
 * 
 * @main method included for testing
 */
public class crypt {
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

    public static String encryptedString(String input) throws NoSuchAlgorithmException {
        return toHexString(getSHA(input));
    }

    public static String encryptedStringKeyed(String input, String key)
            throws InvalidKeyException, NoSuchPaddingException,
            NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
        return toHexString(
                encryptMessage(input.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.UTF_8)));
    }

    public static String decryptedStringKeyed(String input, String key)
            throws InvalidKeyException, NoSuchPaddingException,
            NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
        return toHexString(
                decryptMessage(input.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.UTF_8)));
    }

    public static byte[] encryptMessage(byte[] message, byte[] keyBytes)
            throws InvalidKeyException, NoSuchPaddingException,
            NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(message);
    }

    public static byte[] decryptMessage(byte[] encryptedMessage, byte[] keyBytes) //TODO: bug dare
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(encryptedMessage);
    }

    public static String salt() {
        return new Random().ints(48, 123)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(8).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    public static void main(String[] args) throws Exception { // testing purposes
        String randomString = salt();
        String key = "D*G-KaNdRgUkXp2s", keyedEnc;
        System.out.println("salt: " + randomString);
        System.out.println(encryptedString("borna").toCharArray().length);
        System.out.println(encryptedString("borna1").toCharArray().length);
        System.out.println(encryptedString("hossein").toCharArray().length);
        System.out.println(encryptedString("islhioashfioashfioashfioashfiohasiofhas").toCharArray().length);
        // System.out.println(encryptedString("borna" + randomString));
        System.out.println("key: " + key);
        System.out.println(encryptedString("borna"));
        System.out.println(encryptedString("borna"));
        System.out.println(keyedEnc = encryptedStringKeyed("borna", key));
        System.out.println(decryptedStringKeyed(keyedEnc, key));
    }
}

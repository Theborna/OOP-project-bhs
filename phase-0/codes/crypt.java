import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

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

    public static void main(String[] args) throws Exception {
        String randomString = (new Random().ints(48, 123).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString());
        // System.out.println(randomString);
        System.out.println(encryptedString("borna"));
        System.out.println(encryptedString("borna"));
        System.out.println(encryptedString("borna"));
        System.out.println(encryptedString("borna1"));        
        // System.out.println(encryptedString("borna" + randomString));
    }
}

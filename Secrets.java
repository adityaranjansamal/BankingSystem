
/**
 * This handles the MPIN creation.
 * This java file also encrypts the MPIN using a fairly simple algorithm known as Caesar Cipher but with some modifications by me
 * Caesar Cipher: 'every alphabet+3...(char value)'
 */
import java.util.*;

public class Secrets {
    static Scanner sc = new Scanner(System.in);
    static Scanner stringsc = new Scanner(System.in);
    static char alpha[] = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j' };
    static String hashedpin = "";

    public static void generate_mpin() {// to generate unencrypted mPin
        while (true) {
            System.out.println("Create a MPIN (must be 6 digits)");
            String gpin = stringsc.nextLine();
            if (gpin.length() != 6) {
                System.out.println("Invalid PIN. Criteria Mismatch! Create Again.");
                continue;
            } else {
                System.out.println("PIN Generated!");
                Secrets.encrypt(gpin);
                break;
            }
        }

    }

    public static void encrypt(String gpin)// uses Caesar Cipher Algorithm
    {
        String encrypt = "";
        for (int i = 0; i < gpin.length(); i++) {
            char val = alpha[Integer.parseInt(String.valueOf(gpin.charAt(i)))];
            val += 3;
            encrypt = encrypt + String.valueOf(val);
        }
        hashedpin = encrypt;
        // System.out.println(encrypt);// for debug purposes only. Comment out for
        // security reasons.
    }
}
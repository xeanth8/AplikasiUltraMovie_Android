package mobile.uas.kel_15.ultramovie;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Commons {
    //    public static final String SERVER = "http://192.168.100.19/ultramovie";
    public static final String SERVER = "https://ultramovie-app.000webhostapp.com";

    public static String md5(String plainPassword) {
        StringBuilder hash = new StringBuilder();
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(plainPassword.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            BigInteger no = new BigInteger(1, digest);
            hash = new StringBuilder(no.toString(16));

            while(hash.length() < 32) {
                hash.insert(0, "0");
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return String.valueOf(hash);
    }
}

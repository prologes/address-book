package kr.eric.api.util;

import kr.eric.api.exception.DecryptException;
import kr.eric.api.exception.EncryptException;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES256Util {
    public static String alg = "AES/CBC/PKCS5Padding";

    private static final String key = "01234567890123456789012345678901";

    private static final String iv = key.substring(0, 16); // 16byte

    /**
     * 문자열에 대한 암호화를 한다.
     *
     * @param text
     * @return encrypted string
     */
    public static String encrypt(String text) {
        try {
            if (text == null) {
                return null;
            }
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

            byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new EncryptException();
        }
    }

    /**
     * 암호화 된 문자열을 복호화 한다.
     *
     * @param cipherText
     * @return decrypted string
     */
    public static String decrypt(String cipherText) {
        try {
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

            byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            throw new DecryptException();
        }
    }
}

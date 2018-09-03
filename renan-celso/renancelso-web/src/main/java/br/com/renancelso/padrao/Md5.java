package br.com.renancelso.padrao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Criptografa em MD5
 *
 * @author Renan Celso
 */
public class Md5 {

    /**
     * Metodo criptografador
     *
     * @param input String a ser criptografada
     * @return String criptografada
     */
    public static String getMd5Digest(String campo) {
        try {
            if (campo != null) {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] messageDigest = md.digest(campo.getBytes());
                BigInteger number = new BigInteger(1, messageDigest);
                return number.toString(16);
            } else {
                return campo;
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

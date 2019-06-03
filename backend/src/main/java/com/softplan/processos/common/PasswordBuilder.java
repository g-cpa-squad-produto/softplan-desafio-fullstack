package com.softplan.processos.common;

import java.security.MessageDigest;

public class PasswordBuilder {

    public static final String SALT = "se8f$14vc6~s8e4c*v8";

    public static String toHash(String password) {
        try {
            String passwordWithSalt = password + SALT;
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(passwordWithSalt.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }

            return hexString.toString();
        } catch (Exception e) {
            return null;
        }
    }

}

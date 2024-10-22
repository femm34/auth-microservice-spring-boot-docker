package com.fecd.auth.utils;

import java.util.Base64;

public class Base64Encoder {
    public static String decode(String base64) {
        try {
            return new String(Base64.getDecoder().decode(base64));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid input: The string is not a valid base64 format.", e);
        }
    }
}
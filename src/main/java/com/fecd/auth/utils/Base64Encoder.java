package com.fecd.auth.utils;

import java.util.Base64;

public class Base64Encoder {
    public static String decode(String base64) {
        return new String(Base64.getDecoder().decode(base64));
    }
}

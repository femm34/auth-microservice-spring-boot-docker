package com.fecd.auth.utils;

import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Base64EncoderTest {
    @Test
    void testDecode() {
        String secureJwtKey = "jwtsecurekeyforauthmodule";
        String base64Encoded = Base64.getEncoder().encodeToString(secureJwtKey.getBytes());
        String decoded = Base64Encoder.decode(base64Encoded);
        assertEquals(secureJwtKey, decoded);
    }

    @Test
    void testDecodeInvalidBase64() {
        String invalidBase64 = "thisIsNotBase64@!jeje";

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            Base64Encoder.decode(invalidBase64);
        });

        assertEquals("Invalid input: The string is not a valid base64 format.", thrown.getMessage());
    }
}
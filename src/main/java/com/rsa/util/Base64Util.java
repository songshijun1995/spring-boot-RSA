package com.rsa.util;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {
    public Base64Util() {
    }

    public static byte[] decode(String base64) throws Exception {
        return Base64.decodeBase64(base64);
    }

    public static String encode(byte[] bytes) throws Exception {
        return new String(Base64.encodeBase64(bytes));
    }
}

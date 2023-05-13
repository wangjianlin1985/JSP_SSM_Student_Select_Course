// 
// 
// 

package com.cms.utils;

import java.security.MessageDigest;

public class MD5Util
{
    public static String MD5(final String s) {
        try {
            final MessageDigest md = MessageDigest.getInstance("MD5");
            final byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private static String toHex(final byte[] bytes) {
        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        final StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; ++i) {
            ret.append(HEX_DIGITS[bytes[i] >> 4 & 0xF]);
            ret.append(HEX_DIGITS[bytes[i] & 0xF]);
        }
        return ret.toString();
    }
}

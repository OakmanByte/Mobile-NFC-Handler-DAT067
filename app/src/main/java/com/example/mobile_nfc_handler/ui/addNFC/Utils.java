package com.example.mobile_nfc_handler.ui.addNFC;

public class Utils {


    private static final String HEX_CHAR = "0123456789ABCDEF";
    private static final char[] HEX_CHARS_ARRAY = "0123456789ABCDEF".toCharArray();

    public static byte[] hexStringToByteArray(String hex) {
        byte[] result = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            result[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i+1), 16));
        }
        return result;
    }

    public static String toHex(byte[] byteArray) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < byteArray.length; i++) {
            int v = byteArray[i] & 0xFF;
            result.append(HEX_CHARS_ARRAY[v >>> 4]);
            result.append(HEX_CHARS_ARRAY[v & 0x0F]);
        }
        return result.toString();
    }
}
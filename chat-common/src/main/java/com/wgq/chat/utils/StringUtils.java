package com.wgq.chat.utils;

import com.wgq.chat.common.constant.magic.Symbol;

public class StringUtils {

    /**
     * null或""为true 否则为false
     */
    public static boolean isNullOrEmpty(Object str) {
        return str == null || "".equals(str.toString().trim());
    }

    /**
     * 一定是双位数
     *
     * @param bytes byte[]
     * @return String
     */
    public static String bytes2HexString(byte[] bytes) {
        StringBuilder ret = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret.append(hex.toUpperCase());
        }
        return ret.toString();
    }

    /**
     * 将指定字符串src，以每两个字符分割转换为16进制形式 如："2B44EFD9" byte[]{0x2B, 0x44, 0xEF,0xD9}
     *
     * @param src String
     * @return byte[]
     */
    public static byte[] hexString2Bytes(String src) {
        byte[] tmp = src.getBytes();
        byte[] ret = new byte[tmp.length / 2];
        for (int i = 0; i < tmp.length / 2; i++) {
            byte src0 = tmp[i * 2];
            byte src1 = tmp[i * 2 + 1];
            byte b0 = Byte.decode("0x" + new String(new byte[]{src0}));
            b0 = (byte) (b0 << 4);
            byte b1 = Byte.decode("0x" + new String(new byte[]{src1}));
            ret[i] = (byte) (b0 ^ b1);
        }
        return ret;
    }

    public static String leftPad(String s, char c, int maxLength) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = s.length(); i < maxLength; i++) {
            sb.insert(0, c);
        }
        return sb.toString();
    }

    /**
     * 将字节数组转换为十六进制字符串
     */
    public static String byteToStr(byte[] byteArray) {
        String strDigest = Symbol.EMPTY;
        for (byte b : byteArray) {
            strDigest += byteToHexStr(b);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     */
    private static String byteToHexStr(byte mByte) {
        char[] digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = digit[mByte & 0X0F];
        return new String(tempArr);
    }

}

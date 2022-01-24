package pro.abdiel.ciem.utils;

import com.orhanobut.logger.Logger;

import java.io.UnsupportedEncodingException;

public class Md5 {

    public Md5() {
    }

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            Logger.e(String.valueOf(e));
        } catch(UnsupportedEncodingException ex){
            Logger.e(String.valueOf(ex));
        }
        return null;
    }
}

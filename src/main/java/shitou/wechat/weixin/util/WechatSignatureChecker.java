package shitou.wechat.weixin.util;

import shitou.wechat.weixin.constant.Constant;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by shitou on 14-9-28.
 */
public class WechatSignatureChecker {
    public static boolean check(String timestamp, String nonce, String remoteSignature) throws NoSuchAlgorithmException {
        if (StringUtils.isBlank(timestamp) || StringUtils.isBlank(nonce) || StringUtils.isBlank(remoteSignature))
            return false;

        String[] tempArray = {Constant.WECHAT_TOKEN, timestamp, nonce};
        Arrays.sort(tempArray);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < tempArray.length; i++) {
            sb.append(tempArray[i]);
        }
        String localSig = Encrypt(sb.toString());
        if (localSig.equals(remoteSignature)) {
            return true;
        }
        return false;
    }

    private static String Encrypt(String originString) throws NoSuchAlgorithmException {
        MessageDigest md = null;
        String re = null;
        byte[] bt = originString.getBytes();
        md = MessageDigest.getInstance("SHA-1");
        md.update(bt);
        re = bytes2String(md.digest());
        return re;
    }

    private static String bytes2String(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
}

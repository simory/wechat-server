package shitou.wechat.weixin.util;

import sun.text.resources.FormatData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/21
 * Time: 01:22
 */
public class WechatUtils {

    public static String formatTime(String timeSeconds) {
        if (null == timeSeconds || timeSeconds.trim().isEmpty()) return null;

        DateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        long timeMillis = Long.parseLong(timeSeconds) * 1000L;

        return formater.format(timeMillis);
    }
}

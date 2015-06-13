package shitou.wechat.util;

import org.apache.commons.lang.RandomStringUtils;

/**
 * Created in Intellij IDEA 14 Ultimate
 * User: stoney
 * Date: 2015/06/13
 * Time: 14:02
 */
public class ModelIdGenerator {

    public static String generate(){
        return System.currentTimeMillis() + RandomStringUtils.random(8,true,true);
    }
}

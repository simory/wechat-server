package shitou.wechat.weixin.handle;

import java.util.Map;
import java.util.HashMap;

import shitou.wechat.weixin.MessageType;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/18
 * Time: 22:45
 */
public class HandlerMap {
    public static final Map<String, Class> map = new HashMap<String, Class>();

    static {
        map.put(MessageType.TEXT_MESSAGE, TextMessageHandler.class);
    }
}

package shitou.wechat.weixin.util;

import shitou.wechat.weixin.constant.MessageType;
import shitou.wechat.weixin.handle.TextMessageHandler;

import java.util.HashMap;
import java.util.Map;

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

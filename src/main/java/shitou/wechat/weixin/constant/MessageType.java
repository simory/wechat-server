package shitou.wechat.weixin.constant;

import shitou.wechat.weixin.handle.TextMessageHandler;

import java.util.Map;
import java.util.HashMap;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/17
 * Time: 00:14
 */
public class MessageType {
    public static final String LINK_MESSAGE = "link";
    public static final String TEXT_MESSAGE = "text";
    public static final String IMAGE_MESSAGE = "image";
    public static final String VOICE_MESSAGE = "voice";
    public static final String VIDEO_MESSAGE = "video";
    public static final String LOCATION_MESSAGE = "location";

    public static final String MSG_TYPE = "MsgType";

    public static final String NULL_MESSAGE = "nullMessage";
    public static final String UNKNOWN_MESSAGE = "unkonwnMessage";

    public static class HandlerMap {
        public static final Map<String, Class> map = new HashMap<String, Class>();

        static {
            map.put(TEXT_MESSAGE, TextMessageHandler.class);
        }
    }
}

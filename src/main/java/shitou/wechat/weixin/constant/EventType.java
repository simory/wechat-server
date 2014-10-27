package shitou.wechat.weixin.constant;

import shitou.wechat.weixin.handle.SubscribeEventHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/27
 * Time: 22:00
 */
public class EventType {
    public static final String EVENT_TAG = "Event";
    public static final String SUBSCRIBE_EVENT = "subscribe";
    public static final String NULL_EVENT = "nullEvent";
    public static final String INVALIDE_EVENT = "invalideEvent";
    public static final String EVENT_SIGN = "<MsgType><![CDATA[event]]></MsgType>";

    public static Map<String, Class> eventHandlerMap = new HashMap<String, Class>();

    static {
        eventHandlerMap.put(SUBSCRIBE_EVENT, SubscribeEventHandler.class);
    }
}

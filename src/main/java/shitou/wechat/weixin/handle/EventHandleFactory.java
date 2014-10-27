package shitou.wechat.weixin.handle;

import org.springframework.stereotype.Component;
import shitou.wechat.weixin.constant.Constant;
import shitou.wechat.weixin.constant.EventType;
import shitou.wechat.weixin.util.EventTypePicker;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/27
 * Time: 22:45
 */
@Component
public class EventHandleFactory {
    public String hand(String xml) throws Exception {
        String eventType = EventTypePicker.pick(xml);
        Class<EventHandler> eventHandler = EventType.eventHandlerMap.get(eventType);

        if (null == eventHandler) return Constant.NULL_STRING;

        EventHandler handler = eventHandler.newInstance();
        return handler.handle(xml);
    }
}

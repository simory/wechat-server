package shitou.wechat.weixin.handle;


import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import shitou.wechat.weixin.constant.Constant;
import shitou.wechat.weixin.constant.EventType;
import shitou.wechat.weixin.constant.MessageType;
import shitou.wechat.weixin.util.MessageTypePicker;

import java.util.List;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/17
 * Time: 00:07
 */
@Component
public class HandleFactory {

    @Autowired
    EventHandleFactory eventHandleFactory;

    @Autowired
    IllegalMessageHandler illegalMessageHandler;

    public String handle(String xml) throws Exception {
        if (xml == null || xml.trim().isEmpty()) return Constant.NULL_STRING;

        if (xml.contains(Constant.TEXT_MSG_SIGN)) {
            List<String> list = illegalMessageHandler.splitTextMsgTo3Parts(xml);
            boolean isIllegal = illegalMessageHandler.ifTextMsgContentIllegal(list.get(1));
            if (isIllegal) return illegalMessageHandler.handle(list);
        }

        if (xml.contains(EventType.EVENT_SIGN)) {
            return eventHandleFactory.hand(xml);
        }

        String messageType = MessageTypePicker.pick(xml);
        Class<MessageHandler> specificHandler = MessageType.messageHandlerMap.get(messageType);

        if (specificHandler == null) return Constant.NULL_STRING;

        MessageHandler handler = specificHandler.newInstance();
        return handler.handle(xml);
    }
}

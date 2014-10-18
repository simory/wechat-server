package shitou.wechat.weixin.order;


import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shitou.wechat.weixin.Constant;
import shitou.wechat.weixin.util.HandlerMap;
import shitou.wechat.weixin.util.MessageTypePicker;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/17
 * Time: 00:07
 */

@Component
public class MessageFilter {

    @Autowired
    MessageTypePicker messageTypePicker;

    public String handle(String xml) throws DocumentException, IllegalAccessException, InstantiationException {
        if (xml == null || xml.trim().isEmpty()) return Constant.NULL_STRING;

        String messageType = messageTypePicker.pick(xml);
        Class<MessageHandler> specificHandlerClass = HandlerMap.map.get(messageType);

        if (specificHandlerClass == null) return Constant.NULL_STRING;

        MessageHandler handler = specificHandlerClass.newInstance();
        return handler.handle(xml);
    }
}

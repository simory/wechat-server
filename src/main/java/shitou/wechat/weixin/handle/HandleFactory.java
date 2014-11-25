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

    /*
     * 创建时间 2014/11/25 23:26
     * 第一次更新 2014/11/25 23:36
     * 由于想防范用户发送包含xml标签的文本消息，从而构成非法xml,造成解析错误
     * 解决方案
     * 1.进入标准消息处理流程前判断是否是文本格式的消息
     * 2.如果是则通过正则表达式获取内容部分
     * 3.判断是否有非法内容
     * 4.如果有则转入 IllegalXmlHandler 处理
     * 5.如果无非法内容，则转入正常处理流程
     *
     * 此方法将文本消息拆解为三部分 第二部分为文本内容(list.get(1);)
     */

     /*
     * Create time Nov, 25 2014 11:26pm
     * First time update Nov, 25 2014 11:36pm
     * In order to avoid user(wechat follower) sending text message contains xml tag
     * It may disturb the message xml forwarded from Tencent to illegal xml
     * Then cause the parse error
     *
     * Solution
     * 1.Judging if the message is a text message before the standered processing flow
     * 2.If YES, then split the xml to get the message content by regular expression
     * 3.Judging if illegal content exist
     * 4.If YES, then route to IllegalXmlHandler to process the message
     * 5.If NO, go on processing the xml in normal flow
     *
     * This method will split the xml to 3 parts, message content is the second part (i.e. list.get(1);)
     */

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

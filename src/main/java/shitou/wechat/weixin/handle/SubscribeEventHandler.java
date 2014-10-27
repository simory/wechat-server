package shitou.wechat.weixin.handle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shitou.wechat.core.model.SubscribeEventModel;
import shitou.wechat.core.model.TextMessageModel;
import shitou.wechat.weixin.constant.Constant;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/27
 * Time: 23:26
 */

@Component
public class SubscribeEventHandler implements EventHandler {



    @Override
    public String handle(String xml) throws Exception {
        if (null == xml || xml.trim().isEmpty()) return Constant.NULL_STRING;

        SubscribeEventModel eventModel = new SubscribeEventModel();
        TextMessageModel messageModel = new TextMessageModel();

        eventModel = eventModel.buildFromXml(xml);

        messageModel.setToUserName(eventModel.getFromUserName());
        messageModel.setFromUserName(eventModel.getToUserName());
        return messageModel.toResponsesXml("欢迎关注本公众帐号,本帐号后台服务器基于Java语言编写，运行于阿里云服务器！");
    }
}

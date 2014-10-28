package shitou.wechat.weixin.handle;

import shitou.wechat.weixin.util.WechatUtils;
import shitou.wechat.weixin.constant.Constant;
import org.springframework.stereotype.Component;
import shitou.wechat.core.model.SubscribeEventModel;

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

        eventModel = eventModel.buildFromXml(xml);

        String me = eventModel.getToUserName();
        String user = eventModel.getFromUserName();
        String respMessage = WechatUtils.buildTextMessage(user, me, Constant.SUBSCRIBE_EVENT_WELCOME_MESSAGE);
        return respMessage;
    }
}

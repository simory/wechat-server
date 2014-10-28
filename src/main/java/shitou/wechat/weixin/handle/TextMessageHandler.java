package shitou.wechat.weixin.handle;

import org.dom4j.DocumentException;
import shitou.wechat.weixin.constant.Constant;
import org.springframework.stereotype.Component;
import shitou.wechat.core.model.TextMessageModel;
import shitou.wechat.weixin.util.WechatUtils;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/18
 * Time: 22:49
 */
@Component
public class TextMessageHandler implements MessageHandler {

    @Override
    public String handle(String xml) throws DocumentException {
        if (null == xml || xml.trim().isEmpty()) return Constant.NULL_STRING;

        TextMessageModel textMessageModel = new TextMessageModel();
        textMessageModel = textMessageModel.buildFromXml(xml);

        String user = textMessageModel.getFromUserName();
        String me = textMessageModel.getToUserName();

        StringBuffer sb = new StringBuffer();
        sb.append("小黑收到你的消息了\n");
        sb.append("你是在 " + textMessageModel.getCreateTime() + " 发给我的\n");
        sb.append("你对我说:" + textMessageModel.getContent() + "\n");

        return WechatUtils.buildTextMessage(user, me, sb.toString());
    }
}

package shitou.wechat.weixin.handle;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Component;
import shitou.wechat.core.model.TextMessageModel;
import shitou.wechat.weixin.Constant;

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
        System.out.println(textMessageModel.getCreateTime());

        StringBuffer sb = new StringBuffer();
        sb.append("Message Info:\n");
        sb.append("FromUser: [" + textMessageModel.getFromUserName() + "]\n");
        sb.append("ToUser: [" + textMessageModel.getToUserName() + "]\n");
        sb.append("CreateTime: [" + textMessageModel.getCreateTime() + "]\n");
        sb.append("MsgType: [" + "text]\n");
        sb.append("Content: [" + textMessageModel.getContent() + "]\n");
        sb.append("MsgId: [" + textMessageModel.getMessageID() + "]\n");

        return textMessageModel.toResponsesXml(sb.toString());
    }
}

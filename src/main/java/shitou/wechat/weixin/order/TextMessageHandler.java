package shitou.wechat.weixin.order;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TextMessageHandler implements MessageHandler{

    @Override
    public String handle(String xml) throws DocumentException {
        if (null == xml || xml.trim().isEmpty()) return Constant.NULL_STRING;

        TextMessageModel textMessageModel = new TextMessageModel();
        textMessageModel = textMessageModel.buildFromXml(xml);
        return textMessageModel.toResponsesXml(textMessageModel.getContent());
    }
}

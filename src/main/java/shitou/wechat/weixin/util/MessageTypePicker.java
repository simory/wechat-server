package shitou.wechat.weixin.util;

import org.dom4j.Element;
import org.dom4j.DocumentException;
import shitou.wechat.weixin.MessageType;

import java.util.List;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/17
 * Time: 00:12
 */
public class MessageTypePicker {
    public static String pick(String xml) throws DocumentException {
        if (null == xml || xml.trim().isEmpty()) return MessageType.NULL_MESSAGE;

        List<Element> list = WechatUtils.getRootElements(xml);

        for (Element element : list) {
            if (MessageType.TAG_NAME.equals(element.getName())) {
                return element.getTextTrim().isEmpty() ? MessageType.NULL_MESSAGE : element.getTextTrim();
            }
        }
        return MessageType.UNKONWN_MESSAGE;
    }
}

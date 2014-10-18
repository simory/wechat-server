package shitou.wechat.weixin.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;
import shitou.wechat.weixin.MessageType;

import java.io.StringReader;
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

        SAXReader reader = new SAXReader();
        Document document = reader.read(new StringReader(xml));
        Element root = document.getRootElement();
        List<Element> list = root.elements();

        for (Element element : list) {
            if (MessageType.TAG_NAME.equals(element.getName())) {
                return element.getTextTrim().isEmpty() ? MessageType.NULL_MESSAGE : element.getTextTrim();
            }
        }
        return MessageType.UNKONWN_MESSAGE;
    }
}

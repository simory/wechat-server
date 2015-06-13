package shitou.wechat.util;

import org.dom4j.Element;
import shitou.wechat.weixin.constant.EventType;

import java.util.List;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/27
 * Time: 23:03
 */
public class EventTypePicker {
    public static String pick(String xml) throws Exception {
        if (null == xml || xml.trim().isEmpty()) return EventType.NULL_EVENT;

        List<Element> root = WechatUtils.getXmlRootElements(xml);

        for (Element element : root) {
            if (EventType.EVENT_TAG.equals(element.getName())) {
                return element.getTextTrim().isEmpty() ? EventType.NULL_EVENT : element.getTextTrim();
            }
        }
        return EventType.INVALIDE_EVENT;
    }
}

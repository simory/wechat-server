package shitou.wechat.core.model;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;
import shitou.wechat.weixin.Constant;
import shitou.wechat.weixin.util.WechatUtils;

import java.io.StringReader;
import java.util.List;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/22
 * Time: 23:10
 */
@Component
public class SubscribeEventModel extends EventModel {
    public SubscribeEventModel buildFromXml(String xml) throws DocumentException {
        if (null == xml || xml.trim().isEmpty()) return null;

        SAXReader reader = new SAXReader();
        Document document = reader.read(new StringReader(xml));
        Element root = document.getRootElement();
        List<Element> list = root.elements();

        SubscribeEventModel subscribeModel = new SubscribeEventModel();
        for (Element element : list) {
            if (Constant.EVENT.equals(element.getName())) {
                subscribeModel.setEvent(element.getTextTrim());
            }
            if (Constant.TO_USER_NAME.equals(element.getName())) {
                subscribeModel.setToUserName(element.getTextTrim());
            }

            if (Constant.FROM_USER_NAME.equals(element.getName())) {
                subscribeModel.setFromUserName(element.getTextTrim());
            }

            if (Constant.CREATE_TIME.equals(element.getName())) {
                subscribeModel.setCreateTime(WechatUtils.formatTime(element.getTextTrim()));
            }

        }
        return subscribeModel;
    }
}

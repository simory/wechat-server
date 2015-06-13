package shitou.wechat.core.model;

import org.dom4j.Element;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Component;

import shitou.wechat.util.WechatUtils;
import shitou.wechat.weixin.constant.Constant;

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

        List<Element> list = WechatUtils.getXmlRootElements(xml);

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

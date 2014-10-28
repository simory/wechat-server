package shitou.wechat.core.model;

import org.dom4j.Element;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Component;

import shitou.wechat.weixin.util.WechatUtils;
import shitou.wechat.weixin.constant.Constant;

import java.util.List;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/14
 * Time: 23:24
 */
@Component
public class TextMessageModel {

    private String content;
    private String messageID;
    private String createTime;
    private String toUserName;
    private String fromUserName;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public TextMessageModel buildFromXml(String xml) throws DocumentException {
        if (xml.trim().isEmpty() || null == xml) return null;

        List<Element> list = WechatUtils.getXmlRootElements(xml);

        TextMessageModel textMessageModel = new TextMessageModel();
        for (Element element : list) {
            if (Constant.CONTENT.equals(element.getName())) {
                textMessageModel.setContent(element.getTextTrim());
            }

            if (Constant.MSG_ID.equals(element.getName())) {
                textMessageModel.setMessageID(element.getTextTrim());
            }

            if (Constant.TO_USER_NAME.equals(element.getName())) {
                textMessageModel.setToUserName(element.getTextTrim());
            }

            if (Constant.FROM_USER_NAME.equals(element.getName())) {
                textMessageModel.setFromUserName(element.getTextTrim());
            }

            if (Constant.CREATE_TIME.equals(element.getName())) {
                textMessageModel.setCreateTime(WechatUtils.formatTime(element.getTextTrim()));
            }
        }
        return textMessageModel;
    }
}

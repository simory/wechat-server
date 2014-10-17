package shitou.wechat.core.model;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;
import shitou.wechat.weixin.Constant;

import java.io.StringReader;
import java.sql.Date;
import java.util.List;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/14
 * Time: 23:24
 */
@Component
public class TextMessageModel {

    private String toUserName;
    private String fromUserName;
    private Date createTime;
    private String content;
    private String messageID;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

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

    public TextMessageModel buildFromXml(String xml) throws DocumentException {
        if (xml.trim().isEmpty() || null == xml) return null;

        SAXReader reader = new SAXReader();
        Document document = reader.read(new StringReader(xml));
        Element root = document.getRootElement();
        List<Element> list = root.elements();

        TextMessageModel textMessageModel = new TextMessageModel();
        for (Element element : list){
            if (Constant.TO_USER_NAME.equals(element.getName())){
                textMessageModel.setToUserName(element.getTextTrim());
            }

            if (Constant.FROM_USER_NAME.equals(element.getName())){
                textMessageModel.setFromUserName(element.getTextTrim());
            }

            if (Constant.CREATE_TIME.equals(element.getName())){
                textMessageModel.setCreateTime(new Date(Long.parseLong(element.getTextTrim())));
            }

            if (Constant.CONTENT.equals(element.getName())){
                textMessageModel.setContent(element.getTextTrim());
            }

            if (Constant.MSG_ID.equals(element.getName())){
                textMessageModel.setMessageID(element.getTextTrim());
            }
        }
        return textMessageModel;
    }
}

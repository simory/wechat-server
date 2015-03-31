package shitou.wechat.weixin.handle;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shitou.wechat.core.model.TextMessageModel;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/11/26
 * Time: 01:02
 */

     /*
     * 创建时间 2014/11/25 23:26
     * 第一次更新 2014/11/25 23:36
     * 由于想防范用户发送包含xml标签的文本消息，从而构成非法xml,造成解析错误
     * 解决方案
     * 1.进入标准消息处理流程前判断是否是文本格式的消息
     * 2.如果是则通过正则表达式获取内容部分
     * 3.判断是否有非法内容
     * 4.如果有则转入 IllegalXmlHandler 处理
     * 5.如果无非法内容，则转入正常处理流程
     *
     * 此方法将文本消息拆解为三部分 第二部分为文本内容(list.get(1);)
     */

     /*
     * Create time Nov, 25 2014 11:26pm
     * First time update Nov, 25 2014 11:36pm
     * In order to avoid user(wechat follower) sending text message contains xml tag
     * It may disturb the message xml forwarded from Tencent to illegal xml
     * Then cause the parse error
     *
     * Solution
     * 1.Judging if the message is a text message before the standered processing flow
     * 2.If YES, then split the xml to get the message content by regular expression
     * 3.Judging if illegal content exist
     * 4.If YES, then route to IllegalXmlHandler to process the message
     * 5.If NO, go on processing the xml in normal flow
     *
     * This method will split the xml to 3 parts, message content is the second part (i.e. list.get(1);)
     */

@Component
public class IllegalMessageHandler {

    @Autowired
    TextMessageModel textMessageModel;

    @Autowired
    TextMessageHandler textMessageHandler;

    public String handle(List<String> list) throws DocumentException {
        if (list.size() == 0) return null;

        list.set(1, replaceIllegalChars(list.get(1)) + "\r\n\r\n 你太调皮了!;-D");

        String xml = "";
        for (int i = 0; i < list.size(); i++) {
            xml += list.get(i);
        }

        textMessageModel = textMessageModel.buildFromXml(xml);
        return textMessageHandler.handle(xml);
    }

    public List<String> splitTextMsgTo3Parts(String xml) {
        if (xml.isEmpty() || xml == null) return null;

        String regex = "^(<xml><ToUserName><!\\[CDATA\\[\\w+?\\]\\]><\\/ToUserName><FromUserName><\\!\\" +
                "[CDATA\\[\\w+?\\]\\]\\><\\/FromUserName><CreateTime>\\d+?<\\/CreateTime><MsgType><\\!\\" +
                "[CDATA\\[text\\]\\]><\\/MsgType><Content><\\!\\[CDATA\\[)(.*?)(\\]\\]><\\/Content><MsgId>" +
                "\\d+?<\\/MsgId><\\/xml>)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(xml);

        if (matcher.find()) {
            List<String> list = new ArrayList<String>();
            for (int i = 0; i < matcher.groupCount(); i++) {
                list.add(matcher.group(i + 1));
            }
            return list;
        }
        return null;
    }

    public boolean ifTextMsgContentIllegal(String content) {
        return content.contains("<") || content.contains(">") || content.contains("[") || content.contains("]") || content.contains("/");
    }

    public String replaceIllegalChars(String illegalString) {
        if (illegalString.isEmpty() || null == illegalString) return null;

        illegalString = illegalString.replaceAll("\\[", "\\\\[");
        illegalString = illegalString.replaceAll("]", "\\\\]");
        illegalString = illegalString.replaceAll("<", "\\\\<");
        illegalString = illegalString.replaceAll(">", "\\\\>");
        illegalString = illegalString.replaceAll("!", "\\\\!");
        illegalString = illegalString.replaceAll("/", "\\\\/");

        return illegalString;
    }
}

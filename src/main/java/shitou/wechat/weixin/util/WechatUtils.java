package shitou.wechat.weixin.util;

import org.dom4j.Element;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.dom4j.DocumentException;

import java.util.List;
import java.io.StringReader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/21
 * Time: 01:22
 */
public class WechatUtils {

    public static String formatTime(String timeSeconds) {
        if (null == timeSeconds || timeSeconds.trim().isEmpty()) return null;

        DateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        long timeMillis = Long.parseLong(timeSeconds) * 1000L;

        return formater.format(timeMillis);
    }

    public static List<Element> getXmlRootElements(String xml) throws DocumentException {
        if (null == xml || xml.trim().isEmpty()) return null;

        SAXReader reader = new SAXReader();
        Document document = reader.read(new StringReader(xml));
        Element root = document.getRootElement();
        return root.elements();
    }

    public static String buildTextMessage(String to, String from, String content) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>\r\n");
        sb.append("<ToUserName><![CDATA[" + to + "]]></ToUserName>\r\n");
        sb.append("<FromUserName><![CDATA[" + from + "]]></FromUserName>\r\n");
        sb.append("<CreateTime>" + System.currentTimeMillis() + "</CreateTime>\r\n");
        sb.append("<MsgType><![CDATA[text]]></MsgType>\r\n");
        sb.append("<Content><![CDATA[" + content + "]]></Content>\r\n");
        sb.append("</xml>");

        return sb.toString();
    }
}

package shitou.wechat.weixin.util;

import org.junit.Test;
import org.dom4j.Element;
import junit.framework.TestCase;
import shitou.wechat.core.model.TextMessageModel;

import java.util.List;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/21
 * Time: 01:25
 */
public class WechatUtilsTest extends TestCase {

    @Test
    public void testFormatTime() throws Exception {
        String timeSeconds = "1413824259";
        String formatedTime = WechatUtils.formatTime(timeSeconds);

        assertNotNull(formatedTime);
        assertEquals("2014/10/21 00:57:39", formatedTime);
    }

    @Test
    public void testFormatTimeWithNull() throws Exception {
        String nullTime = null;
        String formatedTime = WechatUtils.formatTime(nullTime);

        assertNull(formatedTime);
    }

    @Test
    public void testFormatTimeWithEmpty() throws Exception {
        String nullTime = " ";
        String formatedTime = WechatUtils.formatTime(nullTime);

        assertNull(formatedTime);
    }

    @Test
    public void testGetRootElements() throws Exception {
        String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";
        List<Element> list = WechatUtils.getXmlRootElements(xml);

        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    public void testBuildTextMessage() throws Exception {
        String resultXml = WechatUtils.buildTextMessage("user_fdkaucskfooshuf", "me_iefhfe83r4eawfue", "hello");

        assertNotNull(resultXml);

        TextMessageModel newModel = new TextMessageModel();
        newModel = newModel.buildFromXml(resultXml);

        assertNotNull(newModel.getCreateTime());
        assertEquals("hello", newModel.getContent());
        assertEquals("user_fdkaucskfooshuf", newModel.getToUserName());
        assertEquals("me_iefhfe83r4eawfue", newModel.getFromUserName());
    }
}
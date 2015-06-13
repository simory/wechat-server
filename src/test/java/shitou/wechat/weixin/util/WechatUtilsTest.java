package shitou.wechat.weixin.util;

import org.junit.Test;
import org.dom4j.Element;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shitou.wechat.core.model.TextMessageModel;
import shitou.wechat.util.WechatUtils;

import java.util.List;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/21
 * Time: 01:25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context.xml"})
public class WechatUtilsTest extends TestCase {

    @Autowired
    TextMessageModel textMessageModel;

    @Test
    public void testFormatTime() throws Exception {
        String timeSeconds = "1413824259";
        String formattedTime = WechatUtils.formatTime(timeSeconds);

        assertNotNull(formattedTime);
        assertEquals("2014/10/21 00:57:39", formattedTime);
    }

    @Test
    public void testFormatTimeWithNull() throws Exception {
        String nullTime = null;
        String formattedTime = WechatUtils.formatTime(nullTime);

        assertNull(formattedTime);
    }

    @Test
    public void testFormatTimeWithEmpty() throws Exception {
        String nullTime = " ";
        String formattedTime = WechatUtils.formatTime(nullTime);

        assertNull(formattedTime);
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

        textMessageModel = textMessageModel.buildFromXml(resultXml);

        assertNotNull(textMessageModel.getCreateTime());
        assertEquals("hello", textMessageModel.getContent());
        assertEquals("user_fdkaucskfooshuf", textMessageModel.getToUserName());
        assertEquals("me_iefhfe83r4eawfue", textMessageModel.getFromUserName());
    }
}
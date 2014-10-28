package shitou.wechat.core.model;

import org.junit.Test;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import shitou.wechat.weixin.util.WechatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/14
 * Time: 23:27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context.xml"})
public class TextMessageModelTest extends TestCase {

    @Autowired
    TextMessageModel textMessageModel;

    @Test
    public void testBuildFromXml() throws Exception {
        String message = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";
        textMessageModel = textMessageModel.buildFromXml(message);

        assertNotNull(textMessageModel);
        assertEquals("toUser", textMessageModel.getToUserName());
        assertEquals("fromUser", textMessageModel.getFromUserName());
        assertEquals("this is a test", textMessageModel.getContent());
        assertEquals("1234567890123456", textMessageModel.getMessageID());
        assertEquals(WechatUtils.formatTime("1348831860"), textMessageModel.getCreateTime());
    }

    @Test
    public void testBuildFromXmlWithNullMessage() throws Exception {
        String message = "";

        textMessageModel = textMessageModel.buildFromXml(message);

        assertNull(textMessageModel);
    }
}
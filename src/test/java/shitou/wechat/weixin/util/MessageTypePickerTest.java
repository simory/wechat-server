package shitou.wechat.weixin.util;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shitou.wechat.weixin.MessageType;


public class MessageTypePickerTest extends TestCase {


    @Test
    public void testPick() throws Exception {
        String textMessageXml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";
        String messageType = MessageTypePicker.pick(textMessageXml);

        assertNotNull(messageType);
        assertEquals(MessageType.TEXT_MESSAGE, messageType);
    }

    @Test
    public void testPickWithNullXml() throws Exception {
        String nullMessage = null;
        String messageType = MessageTypePicker.pick(nullMessage);

        assertNotNull(messageType);
        assertEquals(MessageType.NULL_MESSAGE, messageType);
    }

    @Test
    public void testPickWithEmptyXml() throws Exception {
        String enptyMessage = "";
        String messageType = MessageTypePicker.pick(enptyMessage);

        assertNotNull(messageType);
        assertEquals(MessageType.NULL_MESSAGE, messageType);
    }

    @Test
    public void testPickWithInvalideXml() throws Exception {
        String invalideMessageXml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";
        String messageType = MessageTypePicker.pick(invalideMessageXml);

        assertNotNull(messageType);
        assertEquals(MessageType.UNKONWN_MESSAGE, messageType);
    }
}
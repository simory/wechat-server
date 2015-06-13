package shitou.wechat.weixin.util;

import org.junit.Test;
import junit.framework.TestCase;
import shitou.wechat.util.MessageTypePicker;
import shitou.wechat.weixin.constant.MessageType;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/17
 * Time: 00:17
 */
public class MessageTypePickerTest extends TestCase {


    @Test
    public void testPickTextMessageType() throws Exception {
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
        assertEquals(MessageType.INVALIDE_MESSAGE, messageType);
    }
}
package shitou.wechat.weixin.util;

import junit.framework.TestCase;
import org.junit.Test;
import shitou.wechat.util.EventTypePicker;
import shitou.wechat.weixin.constant.EventType;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/27
 * Time: 23:04
 */
public class EventTypePickerTest extends TestCase {

    @Test
    public void testPickSubscribeEvent() throws Exception {
        String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[subscribe]]></Event></xml>";
        String eventType = EventTypePicker.pick(xml);

        assertNotNull(eventType);
        assertEquals(EventType.SUBSCRIBE_EVENT, eventType);
    }

    @Test
    public void testPickWithNull() throws Exception {
        String xml = null;
        String eventType = EventTypePicker.pick(xml);

        assertNotNull(eventType);
        assertEquals(EventType.NULL_EVENT, eventType);
    }

    @Test
    public void testPickWithInvalidXml() throws Exception {
        String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType></xml>";
        String eventType = EventTypePicker.pick(xml);

        assertNotNull(eventType);
        assertEquals(EventType.INVALIDE_EVENT, eventType);
    }
}
package shitou.wechat.weixin.handle;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shitou.wechat.weixin.util.MessageTypePicker;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/28
 * Time: 00:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context.xml"})
public class SubscribeEventHandlerTest extends TestCase {

    @Autowired
    SubscribeEventHandler handler;

    @Test
    public void testHandle() throws Exception {
        String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[subscribe]]></Event></xml>";
        String result = handler.handle(xml);

        assertNotNull(result);
        assertTrue(result.length() > 0);
        assertEquals("text", MessageTypePicker.pick(result));
    }
}
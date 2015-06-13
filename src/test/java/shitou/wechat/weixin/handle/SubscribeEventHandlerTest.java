package shitou.wechat.weixin.handle;

import org.junit.Test;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import shitou.wechat.core.model.TextMessageModel;
import shitou.wechat.weixin.constant.Constant;
import shitou.wechat.util.MessageTypePicker;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    @Autowired
    TextMessageModel messageModel;

    @Test
    public void testHandle() throws Exception {
        String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[subscribe]]></Event></xml>";
        String result = handler.handle(xml);

        assertNotNull(result);
        assertTrue(result.length() > 0);
        assertEquals("text", MessageTypePicker.pick(result));

        messageModel = messageModel.buildFromXml(result);
        assertEquals("toUser", messageModel.getFromUserName());
        assertEquals("FromUser", messageModel.getToUserName());
        assertEquals(Constant.SUBSCRIBE_EVENT_WELCOME_MESSAGE, messageModel.getContent());
    }
}
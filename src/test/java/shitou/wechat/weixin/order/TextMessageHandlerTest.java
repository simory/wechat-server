package shitou.wechat.weixin.order;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shitou.wechat.weixin.Constant;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context.xml"})
public class TextMessageHandlerTest extends TestCase {

    @Autowired
    TextMessageHandler hanlder;

    @Test
    public void testHandle() throws Exception {
        String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";
        String result = hanlder.handle(xml);

        assertNotNull(result);
        assertNotSame(Constant.NULL_STRING, result);
        assertTrue(result.trim().length() > 0);
    }

    @Test
    public void  testHandleWithEmptyXml() throws Exception{
        String xml = "";
        String result = hanlder.handle(xml);

        assertNotNull(result);
        assertEquals(Constant.NULL_STRING, result);
    }
}
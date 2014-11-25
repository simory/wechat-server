package shitou.wechat.weixin.handle;

import org.junit.Test;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/17
 * Time: 00:13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context.xml"})
public class HandleFactoryTest extends TestCase {

    @Autowired
    HandleFactory handler;

    @Test
    public void testHandle() throws Exception {
        String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";
        String handleResult = handler.handle(xml);

        assertNotNull(handleResult);
        assertTrue(handleResult.trim().length() > 0);
    }

    @Test
    public void testHandleWithInvalidMessage() throws Exception {
        String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[invalid]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";
        String handleResult = handler.handle(xml);

        assertNotNull(handleResult);
        assertTrue(handleResult.trim().length() == 0);
    }

    @Test
    public void testHandleWithIllegalXml() throws Exception {
        String illegalXml = "<xml>" +
                "<ToUserName><![CDATA[toUser]]></ToUserName>" +
                "<FromUserName><![CDATA[fromUser]]></FromUserName>" +
                "<CreateTime>1348831860</CreateTime>" +
                "<MsgType><![CDATA[text]]></MsgType>" +
                "<Content><![CDATA[<Content><![CDATA[this is a illegal text message xml]]></Content>]]></Content>" +
                "<MsgId>1234567890123456</MsgId>" +
                "</xml>";

        String resultXml = handler.handle(illegalXml);
        assertNotNull(resultXml);
        assertTrue(resultXml.length() > 0);
    }
}
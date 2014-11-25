package shitou.wechat.weixin.handle;

import org.junit.Test;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;


/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/11/26
 * Time: 01:03
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context.xml"})
public class IllegalMessageHandlerTest extends TestCase {

    @Autowired
    IllegalMessageHandler illegalMessageHandler;

    @Test
    public void testSplitTextMsgTo3Parts() throws Exception {
        String xml = "<xml>" +
                "<ToUserName><![CDATA[toUser]]></ToUserName>" +
                "<FromUserName><![CDATA[fromUser]]></FromUserName>" +
                "<CreateTime>1348831860</CreateTime>" +
                "<MsgType><![CDATA[text]]></MsgType>" +
                "<Content><![CDATA[<Content><![CDATA[this is a illegal text message xml]]></Content>]]></Content>" +
                "<MsgId>1234567890123456</MsgId>" +
                "</xml>";

        List<String> splitedMsg = illegalMessageHandler.splitTextMsgTo3Parts(xml);
        String expectedXmlPart1 = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[";

        assertNotNull(splitedMsg);
        assertEquals(expectedXmlPart1, splitedMsg.get(0));
        assertEquals("<Content><![CDATA[this is a illegal text message xml]]></Content>", splitedMsg.get(1));
        assertEquals("]]></Content><MsgId>1234567890123456</MsgId></xml>", splitedMsg.get(2));
    }

    @Test
    public void testIfTextMsgContentIllegal() throws Exception {
        String content = "<Content><![CDATA[this is a illegal text message xml]]></Content>";

        boolean ifIllegal = illegalMessageHandler.ifTextMsgContentIllegal(content);

        assertTrue(ifIllegal);
    }

    @Test
    public void testReplaceIllegalChars() throws Exception {
        String expected = "\\<Content\\>\\<\\!\\[CDATA\\[this is a illegal text message xml\\]\\]\\>\\<\\/Content\\>";

        String processed = illegalMessageHandler.replaceIllegalChars("<Content><![CDATA[this is a illegal text message xml]]></Content>");

        assertNotNull(processed);
        assertEquals(expected, processed);
    }

    @Test
    public void testHandle() throws Exception {
        List<String> list = new ArrayList<String>();
        list.add("<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");
        list.add("<Content><![CDATA[this is a illegal text message xml]]></Content>");
        list.add("]]></Content><MsgId>1234567890123456</MsgId></xml>");

        String resultXml = illegalMessageHandler.handle(list);

        assertNotNull(resultXml);
        assertTrue(resultXml.length() > 0);
    }
}
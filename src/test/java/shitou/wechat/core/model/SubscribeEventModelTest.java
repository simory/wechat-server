package shitou.wechat.core.model;

import org.junit.Test;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import shitou.wechat.util.WechatUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/22
 * Time: 23:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context.xml"})
public class SubscribeEventModelTest extends TestCase {

    @Autowired
    SubscribeEventModel subscribeEventModel;

    @Test
    public void testBuildFromXml() throws Exception {
        String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[subscribe]]></Event></xml>";

        subscribeEventModel = subscribeEventModel.buildFromXml(xml);

        assertNotNull(subscribeEventModel);
        assertEquals("subscribe", subscribeEventModel.getEvent());
        assertEquals("toUser", subscribeEventModel.getToUserName());
        assertEquals("fromUser", subscribeEventModel.getFromUserName());
        assertEquals(WechatUtils.formatTime("123456789"), subscribeEventModel.getCreateTime());
    }

    @Test
    public void testBuildFromXmlWithNull() throws Exception {
        String xml = null;

        subscribeEventModel = subscribeEventModel.buildFromXml(xml);

        assertNull(subscribeEventModel);
    }
}
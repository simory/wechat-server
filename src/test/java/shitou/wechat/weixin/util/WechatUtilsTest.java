package shitou.wechat.weixin.util;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class WechatUtilsTest extends TestCase {

    @Test
    public void testFormatTime() throws Exception {
        String timeSeconds = "1413824259";
        String formatedTime = WechatUtils.formatTime(timeSeconds);

        assertNotNull(formatedTime);
        assertEquals("2014/10/21 00:57:39", formatedTime);
    }

    @Test
    public void testFormatTimeWithNull(){
        String nullTime = null;
        String formatedTime = WechatUtils.formatTime(nullTime);

        assertNull(formatedTime);
    }

    @Test
    public void testFormatTimeWithEmpty(){
        String nullTime = " ";
        String formatedTime = WechatUtils.formatTime(nullTime);

        assertNull(formatedTime);
    }
}
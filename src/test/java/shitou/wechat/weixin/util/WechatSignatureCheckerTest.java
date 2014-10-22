package shitou.wechat.weixin.util;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by shitou on 14-9-28.
 */
public class WechatSignatureCheckerTest extends TestCase {

    @Test
    public void testCheckWithEmptyParameter() throws Exception {
        boolean result = WechatSignatureChecker.check("", "", "");
        assertEquals(false, result);
    }

    @Test
    public void testCheck() throws Exception{
        boolean result = WechatSignatureChecker.check("9983762","sodniwyjo","e4002e9200744df0ddb649f239daa6cbba0424f6");
        assertEquals(true, result);
    }

    @Test
    public void testCheckWithIncorrectSignature() throws Exception{
        boolean result = WechatSignatureChecker.check("9983762","sodniwyjo","incorrectSignature");
        assertEquals(false, result);
    }
}
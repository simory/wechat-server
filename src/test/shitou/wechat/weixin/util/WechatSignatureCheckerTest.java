package shitou.wechat.weixin.util;

import junit.framework.TestCase;

public class WechatSignatureCheckerTest extends TestCase {

    public void testCheckWithEmptyParameter() throws Exception {
        WechatSignatureChecker checker = new WechatSignatureChecker();
        boolean result = checker.check("", "", "");
        assertEquals(false, result);
    }

    public void testCheck() throws Exception{
        WechatSignatureChecker checker = new WechatSignatureChecker();
        boolean result = checker.check("9983762","sodniwyjo","e4002e9200744df0ddb649f239daa6cbba0424f6");
        assertEquals(true, result);
    }
}
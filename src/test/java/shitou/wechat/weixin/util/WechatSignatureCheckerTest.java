package shitou.wechat.weixin.util;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context.xml"})
public class WechatSignatureCheckerTest extends TestCase {

    @Autowired
    WechatSignatureChecker signatureChecker;

    @Test
    public void testCheckWithEmptyParameter() throws Exception {
        boolean result = signatureChecker.check("", "", "");
        assertEquals(false, result);
    }

    @Test
    public void testCheck() throws Exception{
        boolean result = signatureChecker.check("9983762","sodniwyjo","e4002e9200744df0ddb649f239daa6cbba0424f6");
        assertEquals(true, result);
    }

    @Test
    public void testCheckWithIncorrectSignature() throws Exception{
        boolean result = signatureChecker.check("9983762","sodniwyjo","incorrectSignature");
        assertEquals(false, result);
    }
}
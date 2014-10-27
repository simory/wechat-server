package shitou.wechat.web;

import org.junit.Test;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.BufferedReader;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created in Intellij IDEA 13 Ultimate
 * @Author shitou
 * On 2014/8/14 23:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context.xml"})
public class WechatServletTest extends TestCase {

    @Autowired
    WechatServlet entrance;

    @Test
    public void testDoGetWithEchostr() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("echostr")).thenReturn("iisjlf");
        when(request.getParameter("nonce")).thenReturn("88927654927");
        when(request.getParameter("timestamp")).thenReturn("201410121828");
        when(request.getParameter("signature")).thenReturn("17ced6ad93223187a771e1ba072dd118bfb6035b");

        HttpServletResponse response = mock(HttpServletResponse.class);
        when(response.getWriter()).thenReturn(out);

        entrance.doGet(request, response);

        String res = stringWriter.toString();
        assertNotNull(res);
        assertEquals("iisjlf", res);
    }

    @Test
    public void testDoGetWithEmptyTimestamp() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("timestamp")).thenReturn("");

        HttpServletResponse response = mock(HttpServletResponse.class);
        when(response.getWriter()).thenReturn(out);

        entrance.doGet(request, response);

        String res = stringWriter.toString();
        assertNotNull(res);
        assertEquals(0, res.length());
    }

    @Test
    public void testDoGetWithEmptySignature() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("signature")).thenReturn("");
        when(request.getParameter("echostr")).thenReturn("iisjlf");
        when(request.getParameter("nonce")).thenReturn("88927654927");
        when(request.getParameter("timestamp")).thenReturn("201410121828");

        HttpServletResponse response = mock(HttpServletResponse.class);
        when(response.getWriter()).thenReturn(out);

        entrance.doGet(request, response);

        String res = stringWriter.toString();
        assertNotNull(res);
        assertEquals(0, res.length());
    }

    @Test
    public void testDoGetWithInvalidSignature() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("echostr")).thenReturn("iisjlf");
        when(request.getParameter("nonce")).thenReturn("88927654927");
        when(request.getParameter("timestamp")).thenReturn("201410121828");
        when(request.getParameter("signature")).thenReturn("invalidSignature");

        HttpServletResponse response = mock(HttpServletResponse.class);
        when(response.getWriter()).thenReturn(out);

        entrance.doGet(request, response);

        String res = stringWriter.toString();
        assertNotNull(res);
        assertEquals(0, res.length());
    }

    @Test
    public void testDoGetWithMessage() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);
        BufferedReader bufferedReader = new BufferedReader(new StringReader("<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>"));

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getReader()).thenReturn(bufferedReader);
        when(request.getParameter("nonce")).thenReturn("88927654927");
        when(request.getParameter("timestamp")).thenReturn("201410121828");
        when(request.getParameter("signature")).thenReturn("17ced6ad93223187a771e1ba072dd118bfb6035b");

        HttpServletResponse response = mock(HttpServletResponse.class);
        when(response.getWriter()).thenReturn(out);

        entrance.doGet(request, response);

        String res = stringWriter.toString();
        assertNotNull(res);
        assertTrue(res.trim().length() > 0);
    }
}
package shitou.wechat.web;

import org.apache.commons.lang.StringUtils;
import shitou.wechat.weixin.util.WechatSignatureChecker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

/**
 * Created in Intellij IDEA 13 Ultimate
 *
 * @Author shitou
 * On 2014/8/14 23:40
 */
public class WechatServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String remoteSig = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");

        boolean pass = false;
        try {
            pass = WechatSignatureChecker.check(timestamp, nonce, remoteSig);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (pass && echostr != null && !remoteSig.isEmpty()) {
            PrintWriter out = resp.getWriter();
            out.print(echostr);
            out.close();
            return;
        }

        if (StringUtils.isBlank(remoteSig) || StringUtils.isBlank(timestamp) || StringUtils.isBlank(echostr)) return;
    }
}

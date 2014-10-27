package shitou.wechat.web;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import shitou.wechat.weixin.handle.HandleFactory;
import shitou.wechat.weixin.util.WechatSignatureChecker;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.security.NoSuchAlgorithmException;

/**
 * Created in Intellij IDEA 13 Ultimate
 * @Author shitou
 * On 2014/8/14 23:40
 */
@Component
public class WechatServlet extends HttpServlet {

    @Autowired
    HandleFactory handleFactory;

    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");
        String remoteSig = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");

        if (StringUtils.isBlank(remoteSig) || StringUtils.isBlank(timestamp) || StringUtils.isBlank(nonce)) return;

        boolean pass = false;
        try {
            pass = WechatSignatureChecker.check(timestamp, nonce, remoteSig);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (pass) {
            if (StringUtils.isNotEmpty(echostr)) {
                PrintWriter out = resp.getWriter();
                out.print(echostr);
                closeIO(out);
                return;
            }

            BufferedReader reader = req.getReader();
            String xml = "";
            String s = null;
            while ((s = reader.readLine()) != null){
                xml += s;
            }

        try {
            resp.setContentType("text/xml");
            PrintWriter out = resp.getWriter();
            String messageReturn = handleFactory.handle(xml);
            out.write(messageReturn);
            closeIO(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }

    private void closeIO(PrintWriter out){
        out.flush();
        out.close();
    }
}

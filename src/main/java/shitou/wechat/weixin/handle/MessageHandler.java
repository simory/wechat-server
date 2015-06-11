package shitou.wechat.weixin.handle;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Component;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/18
 * Time: 22:38
 */
@Component
public interface MessageHandler {

    String handle(String xml) throws DocumentException;
}

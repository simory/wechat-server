package shitou.wechat.weixin.handle;

import org.springframework.stereotype.Component;

/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/27
 * Time: 23:24
 */
@Component
public interface EventHandler {
    public String handle(String xml) throws Exception;
}

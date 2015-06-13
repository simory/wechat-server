package shitou.wechat.util;

import org.junit.Test;
import junit.framework.TestCase;

/**
 * Created in Intellij IDEA 14 Ultimate
 * User: stoney
 * Date: 2015/06/13
 * Time: 14:04
 */
public class ModelIdGeneratorTest extends TestCase {

    @Test
    public void testGenerate() throws Exception {
        String  id = ModelIdGenerator.generate();

        assertNotNull(id);
    }
}
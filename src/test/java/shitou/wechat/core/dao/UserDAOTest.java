package shitou.wechat.core.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import junit.framework.TestCase;
import shitou.wechat.core.model.UserModel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.Date;

/**
 * Created by stoney on 15/6/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class UserDAOTest extends TestCase {


    @Qualifier("userDAO")
    @Autowired
    private UserDAO userDAO;

    @Test
    public void testCreateUser() throws Exception {
        UserModel user = new UserModel();

        user.setUserGroupId("vip");
        user.setUserAvailable(true);
        user.setUserCity("Chengdu");
        user.setUserUnionId("9527");
        user.setUserCountry("China");
        user.setUserRemark("shitou");
        user.setUserName("月下大石头");
        user.setUserLanguage("zh_CN");
        user.setUserProvince("SiChuan");
        user.setPublicId("jlfdf99e34h38d");
        user.setCreateTime(new Date(System.currentTimeMillis()));
        user.setUserHeadImgUrl("http://wx.qq.com/image/jfhhu423u432ff.jpg");

        userDAO.createUser(user);
    }
}
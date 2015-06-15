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
import shitou.wechat.util.ModelIdGenerator;

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
        user.setUserId(ModelIdGenerator.generate());
        user.setCreateTime(new Date(System.currentTimeMillis()));
        user.setUserHeadImgUrl("http://wx.qq.com/image/jfhhu423u432ff.jpg");

        userDAO.createUser(user);
    }

    @Test
    public void testGetUserByUserId() throws Exception {
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
        user.setUserId(ModelIdGenerator.generate());
        user.setCreateTime(new Date(System.currentTimeMillis()));
        user.setUserHeadImgUrl("http://wx.qq.com/image/jfhhu423u432ff.jpg");

        userDAO.createUser(user);

        UserModel userGet = userDAO.getUserByUserId(user.getUserId());
        assertNotNull(userGet);
        assertEquals("vip",userGet.getUserGroupId());
        assertEquals(true,userGet.isUserAvailable());
        assertEquals("Chengdu",userGet.getUserCity());
        assertEquals("9527",userGet.getUserUnionId());
        assertEquals("China",userGet.getUserCountry());
        assertEquals("shitou",userGet.getUserRemark());
        assertEquals("月下大石头", userGet.getUserName());
        assertEquals("zh_CN",userGet.getUserLanguage());
        assertEquals("SiChuan",userGet.getUserProvince());
        assertEquals("jlfdf99e34h38d",userGet.getPublicId());
        assertEquals(user.getCreateTime(),userGet.getCreateTime());
        assertEquals("http://wx.qq.com/image/jfhhu423u432ff.jpg",userGet.getUserHeadImgUrl());
    }

    @Test
    public void testGetUserByUserIdWithNullId(){
        UserModel userModel = new UserModel();
        userModel.setUserId(ModelIdGenerator.generate());
        userModel.setUserName("shitou");

        userDAO.createUser(userModel);

        UserModel userGet = userDAO.getUserByUserId(null);
        assertNull(userGet);
    }

    @Test
    public void testUpdateUserByUserModel() throws Exception {
        UserModel user = new UserModel();

        user.setUserId(ModelIdGenerator.generate());
        user.setUserGroupId("vip");
        user.setUserAvailable(true);
        user.setUserCity("Chengdu");
        user.setUserUnionId("9527");
        user.setUserCountry("China");
        user.setUserRemark("shitou");
        userDAO.createUser(user);

        UserModel newUser = new UserModel();
        newUser.setUserId(user.getUserId());
        newUser.setUserGroupId("vip");
        newUser.setUserAvailable(false);
        newUser.setUserRemark("Stoney");
        userDAO.updateUserByUserModel(newUser);

        UserModel updatedUser = userDAO.getUserByUserId(user.getUserId());

        assertNotNull(updatedUser);
        assertEquals("vip", updatedUser.getUserGroupId());
        assertEquals("Stoney", updatedUser.getUserRemark());
        assertEquals(false, updatedUser.isUserAvailable());
    }

    @Test
    public void testUpdateUserByUserModel1() throws Exception {
        UserModel user = new UserModel();

        user.setUserId(ModelIdGenerator.generate());
        user.setUserGroupId("vip");
        user.setUserAvailable(true);
        user.setUserCity("Chengdu");
        user.setUserUnionId("9527");
        user.setUserCountry("China");
        user.setUserRemark("shitou");
        userDAO.createUser(user);

        userDAO.deleteUserByUserId(user.getUserId());

        UserModel deleteUser = userDAO.getUserByUserId(user.getUserId());
        assertNull(deleteUser);
    }
}
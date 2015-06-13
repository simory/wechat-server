package shitou.wechat.core.dao;

import org.springframework.stereotype.Component;
import shitou.wechat.core.model.UserModel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by stoney on 15/6/12 00:18
 */
@Component
public class UserDAO {

    @PersistenceContext
    EntityManager entityManager;

    public void createUser(UserModel userModel) {
        entityManager.persist(userModel);
    }

    public UserModel getUserByUserId(String userId) {
        if (userId == null) return null;
        String hql = "from UserModel where userId = :userId";
        List<UserModel> list = entityManager.createQuery(hql).setParameter("userId", userId).getResultList();

        if (list == null || list.size() == 0) return null;
        return list.get(0);
    }

    public void updateUserByUserModel(UserModel userModel) {
        if (userModel == null || userModel.getUserId() == null) return;
        UserModel manageUserModel = this.getUserByUserId(userModel.getUserId());
        if (manageUserModel == null) return;

        manageUserModel.setUserSex(userModel.getUserSex());
        manageUserModel.setUserName(userModel.getUserName());
        manageUserModel.setPublicId(userModel.getPublicId());
        manageUserModel.setUserCity(userModel.getUserCity());
        manageUserModel.setUserRemark(userModel.getUserRemark());
        manageUserModel.setCreateTime(userModel.getCreateTime());
        manageUserModel.setUserGroupId(userModel.getUserGroupId());
        manageUserModel.setUserUnionId(userModel.getUserUnionId());
        manageUserModel.setUserCountry(userModel.getUserCountry());
        manageUserModel.setUserLanguage(userModel.getUserLanguage());
        manageUserModel.setUserProvince(userModel.getUserProvince());
        manageUserModel.setUserAvailable(userModel.isUserAvailable());
        manageUserModel.setUserHeadImgUrl(userModel.getUserHeadImgUrl());
    }
}

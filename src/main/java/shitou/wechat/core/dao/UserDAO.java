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
        String hql = "from UserModel where userId = :userId";
        List<UserModel> list = entityManager.createQuery(hql).setParameter("userId", userId).getResultList();

        if (list == null || list.size() == 0) return null;
        return list.get(0);
    }
}

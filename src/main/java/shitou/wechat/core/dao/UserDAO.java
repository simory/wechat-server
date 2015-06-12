package shitou.wechat.core.dao;

import org.springframework.stereotype.Component;
import shitou.wechat.core.model.UserModel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}

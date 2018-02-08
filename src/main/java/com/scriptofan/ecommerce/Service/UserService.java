package com.scriptofan.ecommerce.Service;

import com.scriptofan.ecommerce.DAO.UserDao;
import com.scriptofan.ecommerce.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * Retrieves user specified by userId.
     *
     * @param userId user to fetch.
     * @return user object associated with user ID.
     */
    public User getUser(String userId) {
        return this.userDao.getUser(userId);
    }

}

package com.scriptofan.ecommerce.DAO;

import com.scriptofan.ecommerce.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class UserDao {

    private HashMap<String, User> dummyUsers;

    /**
     * Default constructor. Creates collection of dummy users.
     */
    public UserDao() {
       /*  this.dummyUsers = new HashMap<String, User>(){
            {
                put("00000000", new User(
                        "00000000",
                        "Frank",
                        "Schnurr",
                        "somebody@gmail.com")
                );

                put("00000000", new User(
                        "00000001",
                        "Jeff",
                        "Bezos",
                        "rich.guy@amazon.com")
                );
            }
        }; */
    }

    /**
     * Returns user associated with userId.
     *
     * @param userId id of user to retrieve.
     * @return user associated with userId.
     */
    public User getUser(String userId) {
        return this.dummyUsers.get(userId);
    }

}

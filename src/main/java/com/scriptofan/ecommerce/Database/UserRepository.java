package com.scriptofan.ecommerce.Database;

import com.scriptofan.ecommerce.User.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{

}

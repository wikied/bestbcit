package com.scriptofan.ecommerce.Database;

import com.scriptofan.ecommerce.User.User;
import org.springframework.data.repository.CrudRepository;

public interface Repository extends CrudRepository<User, Integer>{

}

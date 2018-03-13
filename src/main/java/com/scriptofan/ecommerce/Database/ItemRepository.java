package com.scriptofan.ecommerce.Database;

import com.scriptofan.ecommerce.User.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Integer> {

    public Iterable<Item> findItemById(Integer id);
    public Iterable<Item> findItemsByUser_Name(String name);





}

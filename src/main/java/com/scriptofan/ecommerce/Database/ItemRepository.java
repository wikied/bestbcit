package com.scriptofan.ecommerce.Database;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {

    public Item findItemById(Integer id);
    public Item getItemById(Integer id);
    public List<Item> findAllByAvailableQuantity(Integer id);



}

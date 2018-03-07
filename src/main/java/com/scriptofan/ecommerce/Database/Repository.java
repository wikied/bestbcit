package com.scriptofan.ecommerce.Database;


import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called Repository
// CRUD refers Create, Read, Update, Delete

public interface Repository extends CrudRepository<Item, String> {

}

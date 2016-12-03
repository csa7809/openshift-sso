package com.orange.lizard.auth.user.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.orange.lizard.auth.user.entities.User;
 
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);
     
}

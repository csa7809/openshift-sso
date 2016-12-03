package com.orange.lizard.auth.user.repositories;

import org.springframework.data.repository.CrudRepository;

import com.orange.lizard.auth.user.entities.Role;

public interface RoleRepository extends CrudRepository<Role, String> {

}

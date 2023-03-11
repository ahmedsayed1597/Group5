package com.flamingo.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flamingo.persistence.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{
    
}

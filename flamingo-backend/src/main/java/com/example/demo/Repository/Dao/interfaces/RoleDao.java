package com.example.demo.repository.dao.interfaces;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repository.entities.Role;

public interface RoleDao extends JpaRepository<Role,Integer> {
}

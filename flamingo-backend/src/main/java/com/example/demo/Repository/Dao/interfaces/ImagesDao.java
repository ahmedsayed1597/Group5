package com.example.demo.Repository.Dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.Repository.Entities.*;

@RepositoryRestResource(collectionResourceRel = "images", path = "images")
public interface ImagesDao extends JpaRepository<Images,Integer>

{
    
}

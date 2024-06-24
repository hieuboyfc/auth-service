package com.zimji.auth.repo.custom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepo<E, ID extends Serializable> extends JpaRepository<E, ID> {
}
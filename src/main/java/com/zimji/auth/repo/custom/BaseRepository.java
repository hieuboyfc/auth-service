package com.zimji.auth.repo.custom;

import com.zimji.auth.entity.custom.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity<ID>, ID extends Serializable> extends JpaRepository<E, ID> {

    List<E> findByIdIn(Set<ID> ids);

}

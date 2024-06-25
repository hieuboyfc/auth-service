package com.zimji.auth.service;

import com.zimji.auth.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface IUserService {

    Page<UserDTO> search(String search, Integer status, Pageable pageable);

    UserDTO create(UserDTO dto);

    UserDTO update(Long id, UserDTO dto);

    void deleteByIds(Set<Long> ids);

    UserDTO getById(Long id);

}

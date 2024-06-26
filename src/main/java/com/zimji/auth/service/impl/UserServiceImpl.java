package com.zimji.auth.service.impl;

import com.zimji.auth.dto.UserDTO;
import com.zimji.auth.entity.User;
import com.zimji.auth.exception.BusinessException;
import com.zimji.auth.exception.EntityNotFoundException;
import com.zimji.auth.repo.UserRepository;
import com.zimji.auth.service.IUserService;
import com.zimji.auth.utils.Constants.Action;
import com.zimji.auth.utils.Constants.STATUS;
import com.zimji.auth.utils.MapperUtils;
import com.zimji.auth.utils.ValidatorUtils;
import com.zimji.auth.utils.generator.StringGeneratorUtils;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements IUserService {

    static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    final UserRepository userRepo;
    final ValidatorUtils validatorUtils;

    @Override
    public Page<UserDTO> search(String search, Integer status, Pageable pageable) {
        Page<User> page = userRepo.search(search, status, pageable);
        List<User> listEntities = page.getContent();
        List<UserDTO> listDTOs = toDTOs(listEntities);

        return new PageImpl<>(listDTOs, pageable, page.getTotalElements());
    }

    @Override
    public UserDTO create(UserDTO dto) {
        validateInput(dto, Action.CREATE);

        User entity = toEntity(dto, new User());
        userRepo.save(entity);

        return toDTO(entity);
    }

    @Override
    public UserDTO update(Long id, UserDTO dto) {
        dto.setId(id);
        validateInput(dto, Action.UPDATE);

        User entity = userRepo.findById(id)
                .orElseThrow(() -> new BusinessException("4953", "common.input.not_found.id"));

        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        entity.setMobile(dto.getMobile());
        entity.setDateOfBirth(dto.getDateOfBirth());

        userRepo.save(entity);

        return toDTO(entity);
    }

    @Override
    public void deleteByIds(Set<Long> ids) {
        List<User> listEntities = userRepo
                .findByIds(ids)
                .orElseThrow(() -> new BusinessException("4953", "common.data.not_found"));

        if (CollectionUtils.isNotEmpty(listEntities)) {
            listEntities.forEach(e -> {
                e.setStatus(-9);
                e.setModifiedDate(new Date());
            });
            userRepo.saveAll(listEntities);
        } else {
            throw new BusinessException("4953", "common.delete.not_found.data");
        }
    }

    @Override
    public UserDTO getById(Long id) {
        User entity = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class, id));
        return toDTO(entity);
    }

    private void validateInput(UserDTO dto, String actionType) {
        LOGGER.info("--- UserServiceImpl.validateInput()");

        // Thực hiện validation bằng ValidatorUtils
        validatorUtils.validate(true, dto);

        switch (actionType) {
            case Action.CREATE:
                String userId = StringGeneratorUtils.getRandomUUID();
                dto.setUserId(userId);

                userRepo
                        .findByUsernameAndUserId(0L, dto.getUsername(), dto.getUserId(), STATUS.DELETE.getValue())
                        .ifPresent(listChecks -> {
                            if (CollectionUtils.isNotEmpty(listChecks)) {
                                throw new BusinessException("3005", "user.input.validate.duplicate.username");
                            }
                        });
                break;
            case Action.UPDATE:
                if (ObjectUtils.isEmpty(dto.getId())) {
                    throw new BusinessException("1000", "common.input.required.id");
                }

                userRepo
                        .findByUsernameAndUserId(dto.getId(), dto.getUsername(), dto.getUserId(), STATUS.DELETE.getValue())
                        .ifPresent(listChecks -> {
                            if (CollectionUtils.isNotEmpty(listChecks)) {
                                throw new BusinessException("1003", "user.input.validate.exist.username");
                            }
                        });
                break;
        }

    }

    private User toEntity(UserDTO dto, User entity, String... ignore) {
        return MapperUtils.copy(dto, entity, ignore);
    }

    private UserDTO toDTO(User entity) {
        return MapperUtils.copy(entity, new UserDTO());
    }

    public List<UserDTO> toDTOs(List<User> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }

}

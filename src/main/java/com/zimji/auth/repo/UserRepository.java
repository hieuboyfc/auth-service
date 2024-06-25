package com.zimji.auth.repo;

import com.zimji.auth.entity.User;
import com.zimji.auth.repo.custom.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    @Query("SELECT u " +
            "   FROM User u " +
            "   WHERE " +
            "   (COALESCE(:search, '') = '' " +
            "       OR LOWER(CONCAT(" +
            "           COALESCE(u.username, ''), " +
            "           COALESCE(u.fullName, ''))) LIKE LOWER(CONCAT('%', COALESCE(:search,''), '%'))) " +
            "   AND (u.status <> -9 AND (-1 = :status OR u.status = :status)) ")
    Page<User> search(String search, Integer status, Pageable pageable);

    Optional<User> findByUsernameAndStatus(String username, Integer status);

    @Query("SELECT u " +
            "   FROM User u " +
            "   WHERE (0L = :ignoreId OR u.id <> :ignoreId) " +
            "   AND (u.username = :username OR u.userId = :userId) " +
            "   AND u.status <> :status")
    Optional<List<User>> findByUsernameAndUserId(Long ignoreId, String username, String userId, Integer status);

    @Query("SELECT u " +
            "   FROM User u " +
            "   WHERE u.id IN :ids ")
    Optional<List<User>> findByIds(Set<Long> ids);

}
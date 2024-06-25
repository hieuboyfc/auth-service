package com.zimji.auth.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zimji.auth.entity.custom.PersistableEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users",
        indexes = {
                @Index(name = "idx_user_id", columnList = "id"),
                @Index(name = "idx_user_user_id", columnList = "user_id"),
                @Index(name = "idx_user_username", columnList = "username"),
                @Index(name = "idx_user_status", columnList = "status"),
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_user_username", columnNames = "username, user_id")
        }
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends PersistableEntity<Long> {

    @Id
    @GenericGenerator(name = "id", strategy = "com.zimji.auth.utils.generator.SnowflakeId")
    @GeneratedValue(generator = "id")
    Long id;

    @Column(name = "user_id", length = 50, nullable = false)
    String userId;

    @Column(name = "username", length = 50, nullable = false)
    String username;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "full_name", length = 100, nullable = false)
    String fullName;

    @Column(name = "email", length = 100)
    String email;

    @Column(name = "mobile", length = 20)
    String mobile;

    @Column(name = "date_of_birth")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ssZ",
            timezone = "Asia/Ho_Chi_Minh"
    )
    Date dateOfBirth;

    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            indexes = {
                    @Index(name = "idx_user_roles_user_id", columnList = "user_id"),
                    @Index(name = "idx_user_roles_role_id", columnList = "role_id")
            }
    )
    Set<Role> roles;*/

}

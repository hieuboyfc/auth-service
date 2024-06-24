package com.zimji.auth.entity;

import com.zimji.auth.entity.custom.PersistableEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "permissions",
        indexes = {
                @Index(name = "idx_permission_id", columnList = "id"),
                @Index(name = "idx_permission_status", columnList = "status"),
        }
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Permission extends PersistableEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", length = 100, nullable = false)
    String name; // Example: "Update", "Create", etc.

}

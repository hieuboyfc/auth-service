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
@Table(name = "roles",
        indexes = {
                @Index(name = "idx_role_id", columnList = "id"),
                @Index(name = "idx_role_status", columnList = "status")
        }
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role extends PersistableEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", length = 100, nullable = false)
    String name;

    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_menus",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id"),
            indexes = {
                    @Index(name = "idx_role_menus_role_id", columnList = "role_id"),
                    @Index(name = "idx_role_menus_menu_id", columnList = "menu_id")
            }
    )
    Set<Menu> menus;*/

}

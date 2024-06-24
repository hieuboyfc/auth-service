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
@Table(name = "menus",
        indexes = {
                @Index(name = "idx_menu_id", columnList = "id"),
                @Index(name = "idx_menu_parent_id", columnList = "parent_id"),
                @Index(name = "idx_menu_status", columnList = "status"),
        }
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Menu extends PersistableEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", length = 100, nullable = false)
    String name;

    @Column(name = "type", length = 2)
    Integer type;

    @Column(name = "url", length = 200)
    String url;

    @Column(name = "parent_id")
    Long parentId;

    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "menu_endpoints",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "endpoint_id"),
            indexes = {
                    @Index(name = "idx_menu_endpoints_menu_id", columnList = "menu_id"),
                    @Index(name = "idx_menu_endpoints_endpoint_id", columnList = "endpoint_id")
            }
    )
    Set<Endpoint> endpoints;*/

}

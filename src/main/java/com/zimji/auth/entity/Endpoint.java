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
@Table(name = "endpoints",
        indexes = {
                @Index(name = "idx_endpoint_id", columnList = "id"),
                @Index(name = "idx_endpoint_status", columnList = "status"),
        }
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Endpoint extends PersistableEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", length = 100, nullable = false)
    String name;

    @Column(name = "url_api", length = 200, nullable = false)
    String urlApi;

    @Column(name = "type_api", length = 2) // 1 = Public, 2 = Private
    Integer typeApi;

    @Column(name = "description", length = 500)
    String description;

    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "endpoint_permissions",
            joinColumns = @JoinColumn(name = "endpoint_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"),
            indexes = {
                    @Index(name = "idx_endpoint_permissions_endpoint_id", columnList = "endpoint_id"),
                    @Index(name = "idx_endpoint_permissions_permission_id", columnList = "permission_id")
            }
    )
    Set<Permission> permissions;*/

}

package com.zimji.auth.entity;

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
@Table(name = "endpoint_permissions",
        indexes = {
                @Index(name = "idx_endpoint_permissions_endpoint_id", columnList = "endpoint_id"),
                @Index(name = "idx_endpoint_permissions_permission_id", columnList = "permission_id")
        }
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EndpointPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endpoint_id", referencedColumnName = "id", nullable = false)
    Endpoint endpoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id", referencedColumnName = "id", nullable = false)
    Permission permission;

}

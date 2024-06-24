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
@Table(name = "menu_endpoints",
        indexes = {
                @Index(name = "idx_menu_endpoints_menu_id", columnList = "menu_id"),
                @Index(name = "idx_menu_endpoints_endpoint_id", columnList = "endpoint_id")
        }
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuEndpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", referencedColumnName = "id", nullable = false)
    Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endpoint_id", referencedColumnName = "id", nullable = false)
    Endpoint endpoint;

}

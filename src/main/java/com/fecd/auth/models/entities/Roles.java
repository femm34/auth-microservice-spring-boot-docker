package com.fecd.auth.models.entities;


import com.fecd.auth.commons.constants.RoleName;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleName name;

}

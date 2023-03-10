package com.solvd.laba.events.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String password;

    private String email;

    @Column(name = "first_name")
    private String name;

    @Column(name = "last_name")
    private String surname;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Ticket> tickets;

    @Column(name = "is_activated")
    private boolean isActivated;

    public enum Role implements GrantedAuthority {

        ROLE_ADMIN,
        ROLE_USER;

        @Override
        public String getAuthority() {
            return this.name();
        }
    }

}

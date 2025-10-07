package org.catchplace.catchplacebackend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.catchplace.catchplacebackend.constant.SnsType;
import org.catchplace.catchplacebackend.constant.UserRole;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class User implements OAuth2User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String snsId;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private SnsType snsType;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String telephone;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UserRole role = UserRole.VISITOR;

    @OneToMany(mappedBy = "operator", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Booth> operatedBooths = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Waiting> waitings = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Override
    public Map<String, Object> getAttributes() {
        return Collections.emptyMap();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new org.springframework.security.core.authority.SimpleGrantedAuthority(role.getKey()));
    }

    @Override
    public String getName() {
        return snsId;
    }
}
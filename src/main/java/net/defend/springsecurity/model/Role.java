package net.defend.springsecurity.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.DEVELOPER_READ)),ADMIN(Set.of(Permission.DEVELOPER_READ, Permission.DEVELOPER_WRITE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> grantedAuthority(){
        return USER.getPermissions().stream().map(p -> new SimpleGrantedAuthority(p.getPermission())).
                collect(Collectors.toSet());
    }

}

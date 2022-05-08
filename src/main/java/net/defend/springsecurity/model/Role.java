package net.defend.springsecurity.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(List.of(Permission.DEVELOPER_WRITE,Permission.DEVELOPER_READ)),USER(List.of(Permission.DEVELOPER_READ));

    private final List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public List<GrantedAuthority> grantedAuthority(){
        return getPermissions().stream().map(p -> new SimpleGrantedAuthority(p.getPermission())).
                collect(Collectors.toList());
    }

}

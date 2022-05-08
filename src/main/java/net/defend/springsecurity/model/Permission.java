package net.defend.springsecurity.model;

public enum Permission {
    DEVELOPER_READ("developer:read"),DEVELOPER_WRITE("developer:write");

    private String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}

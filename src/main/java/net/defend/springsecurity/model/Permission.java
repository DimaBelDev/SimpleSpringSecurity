package net.defend.springsecurity.model;

public enum Permission {
    DEVELOPER_WRITE("developer:write"), DEVELOPER_READ("developer:read");

    private String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}

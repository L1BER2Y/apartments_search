package user_service.core.dto;

public enum Role {
    ADMIN(0),
    USER(1),
    MANAGER(2);
private final int roleId;

    Role(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }
}

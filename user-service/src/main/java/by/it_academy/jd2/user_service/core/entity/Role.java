package by.it_academy.jd2.user_service.core.entity;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER"),
    MANAGER("MANAGER"),
    SYSTEM("SYSTEM");
private final String roleId;

    Role(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleId() {
        return roleId;
    }
}

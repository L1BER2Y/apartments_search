package by.it_academy.jd2.user_service.core.dto;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER"),
    MANAGER("MANAGER");
private final String roleId;

    Role(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleId() {
        return roleId;
    }
}

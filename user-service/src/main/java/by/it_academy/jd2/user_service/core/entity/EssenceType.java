package by.it_academy.jd2.user_service.core.entity;

public enum EssenceType {

    USER("USER"),
    SYSTEM("SYSTEM");

    private final String essenceId;

    EssenceType(String essenceId) {
        this.essenceId = essenceId;
    }

    public String getEssenceId() {
        return essenceId;
    }
}

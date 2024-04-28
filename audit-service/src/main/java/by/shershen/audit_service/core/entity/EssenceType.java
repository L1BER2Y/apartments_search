package by.shershen.audit_service.core.entity;

public enum EssenceType {
    USER("USER"),
    REPORT("REPORT");

    private final String typeID;

    EssenceType(String typeID) {
        this.typeID = typeID;
    }

    public String getTypeID() {
        return typeID;
    }
}

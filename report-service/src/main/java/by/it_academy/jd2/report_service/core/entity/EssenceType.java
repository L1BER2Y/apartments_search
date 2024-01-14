package by.it_academy.jd2.report_service.core.entity;

public enum EssenceType {
    User("USER"),
    Report("REPORT");

    private final String typeID;

    EssenceType(String typeID) {
        this.typeID = typeID;
    }

    public String getTypeID() {
        return typeID;
    }
}

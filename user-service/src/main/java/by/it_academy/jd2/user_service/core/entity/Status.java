package by.it_academy.jd2.user_service.core.entity;

public enum Status {

    WAITING_ACTIVATION("WAITING_ACTIVATION"),
    ACTIVATED("ACTIVATED"),
    DEACTIVATED("DEACTIVATED");

    private final String statusId;

    Status(String statusId) {
        this.statusId = statusId;
    }

    public String getStatusId() {
        return statusId;
    }
}

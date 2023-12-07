package by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto;

public enum Status {

    WAITING_ACTIVATION(0),
    ACTIVATED(1),
    DEACTIVATED(2);

    private final int statusId;

    Status(int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }
}

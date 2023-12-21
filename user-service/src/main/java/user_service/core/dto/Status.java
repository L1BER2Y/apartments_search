package user_service.core.dto;

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

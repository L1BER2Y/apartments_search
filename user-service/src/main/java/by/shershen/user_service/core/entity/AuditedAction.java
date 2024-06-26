package by.shershen.user_service.core.entity;

public enum AuditedAction {
    REGISTRATION("REGISTRATION"),
    VERIFICATION("VERIFICATION"),
    LOGIN("LOGIN"),
    INFO_ABOUT_ME("INFO_ABOUT_ME"),
    INFO_ABOUT_ALL_USERS("INFO_ABOUT_ALL_USERS"),
    INFO_ABOUT_USER_BY_ID("INFO_ABOUT_USER_BY_ID"),
    SAVE_USER("SAVE_USER"),
    UPDATE_USER("UPDATE_USER");

    private final String actionId;

    AuditedAction(String actionId) {
        this.actionId = actionId;
    }

    public String getActionId() {
        return actionId;
    }
}

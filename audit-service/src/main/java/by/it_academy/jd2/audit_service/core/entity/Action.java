package by.it_academy.jd2.audit_service.core.entity;

public enum Action {
    REGISTRATION("REGISTRATION"),
    VERIFICATION("VERIFICATION"),
    LOGIN("LOGIN"),
    INFO_ABOUT_ME("INFO_ABOUT_ME"),
    INFO_ABOUT_ALL_USERS("INFO_ABOUT_ALL_USERS"),
    INFO_ABOUT_USER_BY_ID("INFO_ABOUT_ALL_USERS"),
    CREATE_USER("CREATE_USER"),
    UPDATE_USER("UPDATE_USER");

    private final String actionId;

    Action(String actionId) {
        this.actionId = actionId;
    }

    public String getActionId() {
        return actionId;
    }
}

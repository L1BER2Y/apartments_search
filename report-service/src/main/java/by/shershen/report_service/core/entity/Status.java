package by.shershen.report_service.core.entity;

public enum Status {
    LOADED("LOADED"),
    PROGRESS("PROGRESS"),
    ERROR("ERROR"),
    DONE("DONE");

    private final String reportStatusId;

    Status(String reportStatusId) {
        this.reportStatusId = reportStatusId;
    }

    public String getReportStatusId() {
        return reportStatusId;
    }
}

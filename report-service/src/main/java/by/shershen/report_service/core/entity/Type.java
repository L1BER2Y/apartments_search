package by.shershen.report_service.core.entity;

public enum Type {

    JOURNAL_AUDIT("JOURNAL_AUDIT");

    private final String reportTypeId;

    Type(String reportTypeId) {
        this.reportTypeId = reportTypeId;
    }

    public String getReportTypeId() {
        return reportTypeId;
    }
}

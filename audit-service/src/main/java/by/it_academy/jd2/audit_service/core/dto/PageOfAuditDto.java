package by.it_academy.jd2.audit_service.core.dto;

import java.awt.print.Pageable;

public class PageOfAuditDto {
    private int number;
    private int size;
    private int totalPages;
    private long totalElements;
    private boolean first;
    private int numberOfElements;
    private boolean last;
    private Pageable content;

    public PageOfAuditDto(int number, int size) {
        this.number = number;
        this.size = size;
    }

}

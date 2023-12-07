package by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto;

public class PageBuilder {
    private int number;
    private int size;
    private int totalPages;
    private int totalElements;
    private boolean first;
    private int numberOfElements;
    private boolean last;
    private User[] content;

    public PageBuilder setNumber(int number) {
        this.number = number;
        return this;
    }

    public PageBuilder setSize(int size) {
        this.size = size;
        return this;
    }

    public PageBuilder setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public PageBuilder setTotalElements(int totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public PageBuilder setFirst(boolean first) {
        this.first = first;
        return this;
    }

    public PageBuilder setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
        return this;
    }

    public PageBuilder setLast(boolean last) {
        this.last = last;
        return this;
    }

    public PageBuilder setContent(User[] content) {
        this.content = content;
        return this;
    }

    public Page createPage() {
        return new Page(number, size, totalPages, totalElements, first, numberOfElements, last, content);
    }
}
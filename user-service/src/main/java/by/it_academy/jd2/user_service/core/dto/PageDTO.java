package by.it_academy.jd2.user_service.core.dto;
import by.it_academy.jd2.user_service.core.entity.UserEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageDTO {
    private int number;
    private int size;
    private int totalPages;
    private long totalElements;
    private boolean first;
    private int numberOfElements;
    private boolean last;
    private List<UserEntity> content;

    public PageDTO() {
    }

    public PageDTO(int number, int size) {
        this.number = number;
        this.size = size;
    }

    public PageDTO(int number, int size, int totalPages, long totalElements, boolean first, int numberOfElements, boolean last, List<UserEntity> content) {
        this.number = number;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.first = first;
        this.numberOfElements = numberOfElements;
        this.last = last;
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public List<UserEntity> getContent() {
        return content;
    }

    public void setContent(List<UserEntity> content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PageDTO pageDTO = (PageDTO) o;

        if (number != pageDTO.number) return false;
        if (size != pageDTO.size) return false;
        if (totalPages != pageDTO.totalPages) return false;
        if (totalElements != pageDTO.totalElements) return false;
        if (first != pageDTO.first) return false;
        if (numberOfElements != pageDTO.numberOfElements) return false;
        if (last != pageDTO.last) return false;
        return content != null ? content.equals(pageDTO.content) : pageDTO.content == null;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + size;
        result = 31 * result + totalPages;
        result = 31 * result + (int) (totalElements ^ (totalElements >>> 32));
        result = 31 * result + (first ? 1 : 0);
        result = 31 * result + numberOfElements;
        result = 31 * result + (last ? 1 : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PageDTO{" +
                "number=" + number +
                ", size=" + size +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                ", first=" + first +
                ", numberOfElements=" + numberOfElements +
                ", last=" + last +
                ", content=" + content +
                '}';
    }
}

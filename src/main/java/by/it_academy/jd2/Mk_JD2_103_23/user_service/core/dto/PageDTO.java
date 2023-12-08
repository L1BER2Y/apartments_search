package by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto;

import java.util.Arrays;

public class PageDTO {
    private int number;
    private int size;
    private int totalPages;
    private int totalElements;
    private boolean first;
    private int numberOfElements;
    private boolean last;
    private UserDTO[] content;

    public PageDTO() {
    }

    public PageDTO(int number, int size, int totalPages, int totalElements, boolean first, int numberOfElements, boolean last, UserDTO[] content) {
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

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
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

    public UserDTO[] getContent() {
        return content;
    }

    public void setContent(UserDTO[] content) {
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
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(content, pageDTO.content);
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + size;
        result = 31 * result + totalPages;
        result = 31 * result + totalElements;
        result = 31 * result + (first ? 1 : 0);
        result = 31 * result + numberOfElements;
        result = 31 * result + (last ? 1 : 0);
        result = 31 * result + Arrays.hashCode(content);
        return result;
    }

    @Override
    public String toString() {
        return "Page{" +
                "number=" + number +
                ", size=" + size +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                ", first=" + first +
                ", numberOfElements=" + numberOfElements +
                ", last=" + last +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}

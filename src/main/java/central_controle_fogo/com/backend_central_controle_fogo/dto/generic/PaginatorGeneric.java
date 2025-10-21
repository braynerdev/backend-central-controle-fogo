package central_controle_fogo.com.backend_central_controle_fogo.dto.generic;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonPropertyOrder({ "currentPage", "pageSize", "totalPages", "totalItems", "hasPrevious", "hasNext", "items" })
@Getter
@Setter
public class PaginatorGeneric<T> {

    private int currentPage;
    private int pageSize;
    private int totalPages;
    private int totalItems;
    private boolean hasPrevious;
    private boolean hasNext;
    private List<T> items;

    public PaginatorGeneric(int currentPage, int pageSize, int totalItems, List<T> items) {
        this.currentPage = currentPage + 1;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
        this.totalPages = (int) Math.ceil((double) totalItems / pageSize);
        this.hasPrevious = currentPage > 1;
        this.hasNext = currentPage < totalPages;
        this.items = items;
    }
}

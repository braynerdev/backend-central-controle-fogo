package central_controle_fogo.com.backend_central_controle_fogo.dto.Paginator;

import lombok.Getter;

@Getter
public class PaginatorResponse {
    private int pageSize;
    private int totalPages;
    private int totalItems;
    private int currentPage;
    private boolean hasNext;
    private boolean hasPrevious;

    public PaginatorResponse(int currentPage, int totalPages, int pageSize, int totalItems) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
        this.hasNext = currentPage < totalPages ? true : false;
        this.hasPrevious = currentPage > 1 ? true : false;
    }
}

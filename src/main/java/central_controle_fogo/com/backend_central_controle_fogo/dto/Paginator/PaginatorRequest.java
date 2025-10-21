package central_controle_fogo.com.backend_central_controle_fogo.dto.Paginator;

import lombok.Getter;

@Getter
public class PaginatorRequest {
    private int page;
    private int pageSize;

    public PaginatorRequest(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize <= 50 ? pageSize : 50;
    }
}

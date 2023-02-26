package pojo.response;

public class OrderPageInfo {
    private Integer page;
    private Long total;
    private Integer limit;

    public OrderPageInfo(Integer page, Long total, Integer limit) {
        this.page = page;
        this.total = total;
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}

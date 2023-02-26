package pojo.response;

public class OrderGetListResponse {
    private OrderExtendedInfoByList[] orders;
    private OrderPageInfo pageInfo;
    private OrderStation[] availableStations;

    public OrderGetListResponse(OrderExtendedInfoByList[] orders, OrderPageInfo pageInfo, OrderStation[] availableStations) {
        this.orders = orders;
        this.pageInfo = pageInfo;
        this.availableStations = availableStations;
    }

    public OrderGetListResponse() {
    }

    public OrderExtendedInfoByList[] getOrders() {
        return orders;
    }

    public void setOrders(OrderExtendedInfoByList[] orders) {
        this.orders = orders;
    }

    public OrderPageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(OrderPageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public OrderStation[] getAvailableStations() {
        return availableStations;
    }

    public void setAvailableStations(OrderStation[] availableStations) {
        this.availableStations = availableStations;
    }
}

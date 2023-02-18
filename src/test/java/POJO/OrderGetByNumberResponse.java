package POJO;

public class OrderGetByNumberResponse {
    private OrderExtendedInfoByNumber order;

    public OrderGetByNumberResponse(OrderExtendedInfoByNumber order) {
        this.order = order;
    }

    public OrderGetByNumberResponse(){}

    public OrderExtendedInfoByNumber getOrder() {
        return order;
    }

    public void setOrder(OrderExtendedInfoByNumber order) {
        this.order = order;
    }
}

package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Assert;
import pojo.request.OrderBase;
import pojo.response.OrderCreateSuccessResponse;
import pojo.response.OrderGetByNumberResponse;
import pojo.response.OrderGetListResponse;

import static io.restassured.RestAssured.given;

public class StepsOrder extends BaseSteps {
    private final String ORDER_PATH = "/api/v1/orders/";
    private final String ORDER_TRACK_PATH = "/api/v1/orders/track";

    @Step
//    Подготовить простые данные заказа для создания
    public OrderBase prepareSimpleOrderDataToCreate() {
        String[] color = new String[]{"BLACK"};
        return new OrderBase("Naruto", "Uzumaki", "Konoha, 142 apt.", "Konoha Haltestelle", "+7 800 355 35 35", 5, "2020-06-06T00:00:00.000Z", "Sasuke, come back to Konoha", color);
    }

    @Step
//    Подготовить сложные данные заказа для создания
    public OrderBase prepareSpecifyOrderDataToCreate(String firstName, String lastName, String address, String metroStation, String phone, Integer rentTime, String deliveryDate, String comment, String[] color) {
        return new OrderBase(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }

    @Step
//    Выполнить POST-запрос на создание заказа
    public Response sendPostRequestCreateOrder(OrderBase order) {
        return given().spec(baseApi.baseSpec())
                .body(order)
                .when()
                .post(ORDER_PATH);
    }

    @Step
//    Выполнить POST-запрос на создание заказа и сохранить номер созданного заказа
    public String sendPostRequestCreateOrderAndSaveTrack(OrderBase order) {
        return given().spec(baseApi.baseSpec())
                .body(order)
                .when()
                .post(ORDER_PATH)
                .as(OrderCreateSuccessResponse.class)
                .getTrack();
    }

    @Step
//    Выполнить GET-запрос на получение списка заказов
    public Response sendGetRequestOrders() {
        return given().spec(baseApi.baseSpec())
                .when()
                .get(ORDER_PATH);
    }

    @Step
//    Выполнить GET-запрос на получение заказа по номеру
    public Response sendGetRequestOrderByNumber(String number) {
        return given().spec(baseApi.baseSpec())
                .queryParam("t", number)
                .when()
                .get(ORDER_TRACK_PATH);
    }

    @Step
//    Проверить данные созданного заказа
    public void checkDataOrderInOrderByNumber(OrderBase order, String track, Response response) {
        OrderGetByNumberResponse orderResult = response.as(OrderGetByNumberResponse.class);
        Assert.assertEquals(track, orderResult.getOrder().getTrack());
        Assert.assertEquals(order.getFirstName(), orderResult.getOrder().getFirstName());
        Assert.assertEquals(order.getLastName(), orderResult.getOrder().getLastName());
        Assert.assertEquals(order.getAddress(), orderResult.getOrder().getAddress());
        Assert.assertEquals(order.getMetroStation(), orderResult.getOrder().getMetroStation());
        Assert.assertEquals(order.getPhone(), orderResult.getOrder().getPhone());
        Assert.assertEquals(order.getRentTime(), orderResult.getOrder().getRentTime());
        Assert.assertEquals(order.getDeliveryDate(), orderResult.getOrder().getDeliveryDate());
        Assert.assertEquals(order.getComment(), orderResult.getOrder().getComment());
        Assert.assertArrayEquals(order.getColor(), orderResult.getOrder().getColor());
    }

    @Step
//    Проверить, что в списке заказов есть хотя бы один заказ
    public void checkOrdersListNotEmpty(Response response) {
        OrderGetListResponse responseBodyObject = response.as(OrderGetListResponse.class);
        Assert.assertTrue(responseBodyObject.getOrders().length > 0);
    }
}

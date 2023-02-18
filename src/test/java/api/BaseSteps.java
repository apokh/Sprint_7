package api;

import POJO.*;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class BaseSteps extends BaseApi {
    @Step
//    Подготовить простые данные курьера для создания
    public CourierToCreate prepareSimpleCourierDataToCreate() {
        return new CourierToCreate("ninjaSecond","1234","Rafael");
    }
    @Step
//    Подготовить специфические данные курьера для создания
    public CourierToCreate prepareSpecifyCourierDataToCreate(String login, String password, String firstName) {
        return new CourierToCreate(login,password,firstName);
    }
    @Step
//    Подготовить простые данные заказа для создания
    public OrderBase prepareSimpleOrderDataToCreate() {
        String[] color = new String[]{"BLACK"};
        return new OrderBase("Naruto","Uzumaki","Konoha, 142 apt.","Konoha Haltestelle","+7 800 355 35 35",5,"2020-06-06T00:00:00.000Z","Sasuke, come back to Konoha",color);
    }
    @Step
//    Подготовить сложные данные заказа для создания
    public OrderBase prepareSpecifyOrderDataToCreate(String firstName, String lastName, String address, String metroStation, String phone, Integer rentTime, String deliveryDate, String comment, String[] color) {
        return new OrderBase(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }
    @Step
//    Выполнить POST-запрос на создание курьера
    public Response sendPostRequestCreateCourier(CourierToCreate courier) {
        return given().spec(baseSpec())
                .body(courier)
                .when()
                .post("/api/v1/courier/");
    }
    @Step
//    Выполнить POST-запрос на авторизацию курьером
    public Response sendPostLoginAsCourier(CourierBase courier) {
        return given().spec(baseSpec())
                .body(courier)
                .when()
                .post("/api/v1/courier/login/");
    }
    @Step
//    Выполнить DELETE-запрос на удаление курьера
    public Response sendDeleteRequestToDeleteCourier(CourierBase courier) {
        Response response = sendPostLoginAsCourier(courier);
        int courierId = response.as(CourierCreateSuccessResponse.class).getId();
        String toDeleteURI = "/api/v1/courier/"+courierId;
        return given().spec(baseSpec())
                .delete(toDeleteURI);
    }
    @Step
//    Выполнить POST-запрос на создание заказа
    public Response sendPostRequestCreateOrder(OrderBase order) {
        return given().spec(baseSpec())
                .body(order)
                .when()
                .post("/api/v1/orders/");
    }
    @Step
//    Выполнить POST-запрос на создание заказа и сохранить номер созданного заказа
    public String sendPostRequestCreateOrderAndSaveTrack(OrderBase order) {
        return given().spec(baseSpec())
                .body(order)
                .when()
                .post("/api/v1/orders/")
                .as(OrderCreateSuccessResponse.class)
                .getTrack();
    }
    @Step
//    Выполнить GET-запрос на получение списка заказов
    public Response sendGetRequestOrders() {
        return given().spec(baseSpec())
                .when()
                .get("/api/v1/orders/");
    }
    @Step
//    Выполнить GET-запрос на получение заказа по номеру
    public Response sendGetRequestOrderByNumber(String number) {
        return given().spec(baseSpec())
                .queryParam("t",number)
                .when()
                .get("/api/v1/orders/track");
    }
    @Step
//    Сохранить исходный логин курьера и заменить на новый
    public String courierLoginSaveAndSetNew(CourierBase courier, String newLogin) {
        String loginSaved = courier.getLogin();
        courier.setLogin(newLogin);
        return loginSaved;
    }
    @Step
//    Сохранить исходный пароль курьера и заменить на новый
    public String courierPasswordSaveAndSetNew(CourierBase courier, String newPassword) {
        String passwordSaved = courier.getPassword();
        courier.setPassword(newPassword);
        return passwordSaved;
    }
    @Step
//    Проверить код ответа сервера
    public void checkStatusCode(Response response, int statusCode) {
        response.then().assertThat()
                .statusCode(statusCode);
    }
    @Step
//    Проверить, что элемент не пустой
    public void checkElementNotNullValue(Response response, String elementName) {
        response.then().assertThat()
                .body(elementName,notNullValue());
    }
    @Step
//    Проверить значение элемента
    public void checkElementEqualTo(Response response, String elementPath, String valueExpected) {
        response.then()
                .assertThat().body(elementPath,equalTo(valueExpected));
    }
    @Step
//    Проверить значение элемента
    public void checkElementEqualTo(Response response, String elementPath, Boolean valueExpected) {
        response.then()
                .assertThat().body(elementPath,equalTo(valueExpected));
    }
    @Step
//    Проверить данные созданного заказа
    public void checkDataOrderInOrderByNumber(OrderBase order,String track,Response response) {
        OrderGetByNumberResponse orderResult = response.as(OrderGetByNumberResponse.class);
        Assert.assertEquals(track,orderResult.getOrder().getTrack());
        Assert.assertEquals(order.getFirstName(), orderResult.getOrder().getFirstName());
        Assert.assertEquals(order.getLastName(),orderResult.getOrder().getLastName());
        Assert.assertEquals(order.getAddress(),orderResult.getOrder().getAddress());
        Assert.assertEquals(order.getMetroStation(),orderResult.getOrder().getMetroStation());
        Assert.assertEquals(order.getPhone(),orderResult.getOrder().getPhone());
        Assert.assertEquals(order.getRentTime(),orderResult.getOrder().getRentTime());
        Assert.assertEquals(order.getDeliveryDate(),orderResult.getOrder().getDeliveryDate());
        Assert.assertEquals(order.getComment(),orderResult.getOrder().getComment());
        Assert.assertArrayEquals(order.getColor(),orderResult.getOrder().getColor());
    }
    @Step
//    Проверить, что в списке заказов есть хотя бы один заказ
    public void checkOrdersListNotEmpty(Response response) {
        OrderGetListResponse responseBodyObject = response.as(OrderGetListResponse.class);
        Assert.assertTrue(responseBodyObject.getOrders().length > 0);
    }
}

package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.request.CourierBase;
import pojo.request.CourierToCreate;
import pojo.response.CourierCreateSuccessResponse;

import static io.restassured.RestAssured.given;

public class StepsCourier extends BaseSteps {
    private final String COURIER_PATH = "/api/v1/courier/";
    private final String COURIER_LOGIN_PATH = "/api/v1/courier/login/";

    @Step
//    Подготовить простые данные курьера для создания
    public CourierToCreate prepareSimpleCourierDataToCreate() {
        return new CourierToCreate("ninjaSecond", "1234", "Rafael");
    }

    @Step
//    Подготовить специфические данные курьера для создания
    public CourierToCreate prepareSpecifyCourierDataToCreate(String login, String password, String firstName) {
        return new CourierToCreate(login, password, firstName);
    }

    @Step
//    Выполнить POST-запрос на создание курьера
    public Response sendPostRequestCreateCourier(CourierToCreate courier) {
        return given().spec(baseApi.baseSpec())
                .body(courier)
                .when()
                .post(COURIER_PATH);
    }

    @Step
//    Выполнить POST-запрос на авторизацию курьером
    public Response sendPostLoginAsCourier(CourierBase courier) {
        return given().spec(baseApi.baseSpec())
                .body(courier)
                .when()
                .post(COURIER_LOGIN_PATH);
    }

    @Step
//    Выполнить DELETE-запрос на удаление курьера
    public Response sendDeleteRequestToDeleteCourier(CourierBase courier) {
        Response response = sendPostLoginAsCourier(courier);
        int courierId = response.as(CourierCreateSuccessResponse.class).getId();
        String toDeleteURI = COURIER_PATH + courierId;
        return given().spec(baseApi.baseSpec())
                .delete(toDeleteURI);
    }

    @Step
//    Изменить логин курьера
    public void setNewLogin(CourierBase courier, String newLogin) {
        courier.setLogin(newLogin);
    }

    @Step
//    Изменить пароль курьера
    public void setNewPassword(CourierBase courier, String newPassword) {
        courier.setPassword(newPassword);
    }

    @Step
//    Изменить имя курьера
    public void setNewFirstName(CourierToCreate courier, String newFirstName) {
        courier.setFirstName(newFirstName);
    }
}

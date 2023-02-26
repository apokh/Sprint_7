package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class BaseSteps {
    public BaseApi baseApi = new BaseApi();

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
                .body(elementName, notNullValue());
    }

    @Step
//    Проверить значение элемента
    public void checkElementEqualTo(Response response, String elementPath, String valueExpected) {
        response.then()
                .assertThat().body(elementPath, equalTo(valueExpected));
    }

    @Step
//    Проверить значение элемента
    public void checkElementEqualTo(Response response, String elementPath, Boolean valueExpected) {
        response.then()
                .assertThat().body(elementPath, equalTo(valueExpected));
    }
}

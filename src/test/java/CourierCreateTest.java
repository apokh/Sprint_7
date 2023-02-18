import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import api.BaseSteps;
import POJO.CourierToCreate;

public class CourierCreateTest {
    public BaseSteps steps = new BaseSteps();

    @Test
    @DisplayName("Запрос на создание курьера (успешный) - код ответа: 201")
    @Description(" - запрос возвращает правильный код ответа \n - курьера можно создать")
    public void createCourierTestStatusCode() {
        CourierToCreate courier = steps.prepareSimpleCourierDataToCreate();
        Response response = steps.sendPostRequestCreateCourier(courier);
        steps.checkStatusCode(response, 201);

        steps.sendDeleteRequestToDeleteCourier(courier);
    }

    @Test
    @DisplayName("Запрос на создание курьера (успешный) - в теле ответа \"ok\": true")
    @Description(" - успешный запрос возвращает ok: true \n - курьера можно создать")
    public void createCourierTestSuccessfullyBodyHasOk() {
        CourierToCreate courier = steps.prepareSimpleCourierDataToCreate();
        Response response = steps.sendPostRequestCreateCourier(courier);
        steps.checkElementEqualTo(response, "ok", true);

        steps.sendDeleteRequestToDeleteCourier(courier);
    }

    @Test
    @DisplayName("Запрос на создание курьера (провальный) - такой курьер уже существует (1:1) - код ответа: 409")
    @Description(" - нельзя создать двух одинаковых курьеров \n - код ответа: 409")
    public void createCourierTestFailureAlreadyExistStatusCode() {
        CourierToCreate courier = steps.prepareSimpleCourierDataToCreate();
        steps.sendPostRequestCreateCourier(courier);
        Response response = steps.sendPostRequestCreateCourier(courier);
        steps.checkStatusCode(response, 409);

        steps.sendDeleteRequestToDeleteCourier(courier);
    }

    @Test
    @DisplayName("Запрос на создание курьера (провальный) - такой курьер уже существует (1:1) - message")
    @Description(" - нельзя создать двух одинаковых курьеров \n" +
            " - \"message\": \"Этот логин уже используется. Попробуйте другой.\"")
    public void createCourierTestFailureAlreadyExistMessage() {
        CourierToCreate courier = steps.prepareSimpleCourierDataToCreate();
        steps.sendPostRequestCreateCourier(courier);
        Response response = steps.sendPostRequestCreateCourier(courier);
        steps.checkElementEqualTo(response, "message", "Этот логин уже используется. Попробуйте другой.");

        steps.sendDeleteRequestToDeleteCourier(courier);
    }

    @Test
    @DisplayName("Запрос на создание курьера (провальный) - логин уже занят - код ответа: 409")
    @Description(" - если создать пользователя с логином, который уже есть, возвращается ошибка (409) \n" +
            " - код ответа: 409")
    public void createCourierTestFailureLoginAlreadyExistStatusCode() {
        CourierToCreate courierNinja = steps.prepareSpecifyCourierDataToCreate("Michelangelo", "1234", "Michelangelo Ninja");
        CourierToCreate courierSculptor = steps.prepareSpecifyCourierDataToCreate("Michelangelo", "12345678", "Michelangelo Buonarroti");
        steps.sendPostRequestCreateCourier(courierNinja);
        Response response = steps.sendPostRequestCreateCourier(courierSculptor);
        steps.checkStatusCode(response, 409);

        steps.sendDeleteRequestToDeleteCourier(courierNinja);
    }

    @Test
    @DisplayName("Запрос на создание курьера (провальный) - login: null - код ответа: 400")
    @Description(" - чтобы создать курьера, нужно передать в ручку все обязательные поля \n" +
            " - если одного из обязательных полей нет (login) - запрос возвращает ошибку (400) \n" +
            " - код ответа: 400")
    public void createCourierTestFailureBadRequestLoginIsNullStatusCode() {
        CourierToCreate courier = steps.prepareSpecifyCourierDataToCreate(null, "1234", "Mr. Null");
        Response response = steps.sendPostRequestCreateCourier(courier);
        steps.checkStatusCode(response, 400);
    }

    @Test
    @DisplayName("Запрос на создание курьера (провальный) - login: \"\" - код ответа: 400")
    @Description(" - чтобы создать курьера, нужно передать в ручку все обязательные поля \n" +
            " - если одного из обязательных полей нет (login) - запрос возвращает ошибку (400) \n" +
            " - код ответа: 400")
    public void createCourierTestFailureBadRequestLoginIsEmptyStatusCode() {
        CourierToCreate courier = steps.prepareSpecifyCourierDataToCreate("", "1234", "Mr. Null");
        Response response = steps.sendPostRequestCreateCourier(courier);
        steps.checkStatusCode(response, 400);
    }

    @Test
    @DisplayName("Запрос на создание курьера (провальный) - password: null - код ответа: 400")
    @Description(" - чтобы создать курьера, нужно передать в ручку все обязательные поля \n" +
            " - если одного из обязательных полей нет (password) - запрос возвращает ошибку (400) \n" +
            " - код ответа: 400")
    public void createCourierTestFailureBadRequestPasswordIsNullStatusCode() {
        CourierToCreate courier = steps.prepareSpecifyCourierDataToCreate("ninjaFirst", null, "Mr. Null");
        Response response = steps.sendPostRequestCreateCourier(courier);
        steps.checkStatusCode(response, 400);
    }

    @Test
    @DisplayName("Запрос на создание курьера (провальный) - password: \"\" - код ответа: 400")
    @Description(" - чтобы создать курьера, нужно передать в ручку все обязательные поля \n" +
            " - если одного из обязательных полей нет (password) - запрос возвращает ошибку (400) \n" +
            " - код ответа: 400")
    public void createCourierTestFailureBadRequestPasswordIsEmptyStatusCode() {
        CourierToCreate courier = steps.prepareSpecifyCourierDataToCreate("ninjaFirst", "", "Mr. Null");
        Response response = steps.sendPostRequestCreateCourier(courier);
        steps.checkStatusCode(response, 400);
    }

    @Test
    @DisplayName("Запрос на создание курьера (успешный) - firstName: null - код ответа: 201")
    @Description(" - чтобы создать курьера, нужно передать в ручку все обязательные поля \n" +
            " - необязательные можно пропустить \n" +
            " - код ответа: 201")
    public void createCourierTestSuccessFirstNameIsNullStatusCode() {
        CourierToCreate courier = steps.prepareSpecifyCourierDataToCreate("ninjaFirst", "1234", null);
        Response response = steps.sendPostRequestCreateCourier(courier);
        steps.checkStatusCode(response, 201);

        steps.sendDeleteRequestToDeleteCourier(courier);
    }

    @Test
    @DisplayName("Запрос на создание курьера (успешный) - firstName: \"\" - код ответа: 201")
    @Description(" - чтобы создать курьера, нужно передать в ручку все обязательные поля \n" +
            " - необязательные можно пропустить \n" +
            " - код ответа: 201")
    public void createCourierTestSuccessFirstNameIsEmptyStatusCode() {
        CourierToCreate courier = steps.prepareSpecifyCourierDataToCreate("ninjaFirst", "1234", "");
        Response response = steps.sendPostRequestCreateCourier(courier);
        steps.checkStatusCode(response, 201);

        steps.sendDeleteRequestToDeleteCourier(courier);
    }
}

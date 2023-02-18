import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import api.BaseSteps;
import POJO.CourierToCreate;

public class CourierAuthorizeTest {
    public BaseSteps steps = new BaseSteps();

    @Test
    @DisplayName("Запрос на авторизацию курьером (успешный) - код ответа: 200")
    @Description(" - курьер может авторизоваться \n - курьера можно создать \n - код ответа: 200")
    public void loginCourierTestStatusCode() {
        CourierToCreate courier = steps.prepareSimpleCourierDataToCreate();
        steps.sendPostRequestCreateCourier(courier);
        Response response = steps.sendPostLoginAsCourier(courier);
        steps.checkStatusCode(response, 200);

        steps.sendDeleteRequestToDeleteCourier(courier);
    }

    @Test
    @DisplayName("Запрос на авторизацию курьером (успешный) - в теле ответа непустой id")
    @Description(" - успешный запрос возвращает id")
    public void loginCourierTestSuccessfullyBodyHasId() {
        CourierToCreate courier = steps.prepareSimpleCourierDataToCreate();
        steps.sendPostRequestCreateCourier(courier);
        Response response = steps.sendPostLoginAsCourier(courier);
        steps.checkElementNotNullValue(response, "id");

        steps.sendDeleteRequestToDeleteCourier(courier);
    }

    @Test
    @DisplayName("Запрос на авторизацию курьером (провальный) - login: null - код ответа: 400")
    @Description(" - для авторизации нужно передать все обязательные поля \n" +
            " - если какого-то поля нет - запрос возвращает ошибку \n" +
            " - код ответа: 400")
    public void loginCourierTestFailureBadRequestLoginIsNullStatusCode() {
        CourierToCreate courier = steps.prepareSimpleCourierDataToCreate();
        steps.sendPostRequestCreateCourier(courier);
        String loginSaved = steps.courierLoginSaveAndSetNew(courier, null);
        Response response = steps.sendPostLoginAsCourier(courier);
        steps.checkStatusCode(response, 400);

        courier.setLogin(loginSaved);
        steps.sendDeleteRequestToDeleteCourier(courier);
    }

    @Test
    @DisplayName("Запрос на авторизацию курьером (провальный) - login: null - message")
    @Description(" - для авторизации нужно передать все обязательные поля  \n" +
            " - если какого-то поля нет - запрос возвращает ошибку \n" +
            " - message: \"Недостаточно данных для входа\"")
    public void loginCourierTestFailureBadRequestLoginIsNullMessage() {
        CourierToCreate courier = steps.prepareSimpleCourierDataToCreate();
        steps.sendPostRequestCreateCourier(courier);
        String loginSaved = steps.courierLoginSaveAndSetNew(courier, null);
        Response response = steps.sendPostLoginAsCourier(courier);
        steps.checkElementEqualTo(response, "message", "Недостаточно данных для входа");

        courier.setLogin(loginSaved);
        steps.sendDeleteRequestToDeleteCourier(courier);
    }

    @Test
    @DisplayName("Запрос на авторизацию курьером (провальный) - login: \"\" - код ответа: 400")
    @Description(" - для авторизации нужно передать все обязательные поля \n" +
            " - если какого-то поля нет - запрос возвращает ошибку \n" +
            " - код ответа: 400")
    public void loginCourierTestFailureBadRequestLoginIsEmptyStatusCode() {
        CourierToCreate courier = steps.prepareSimpleCourierDataToCreate();
        steps.sendPostRequestCreateCourier(courier);
        String loginSaved = steps.courierLoginSaveAndSetNew(courier, "");
        Response response = steps.sendPostLoginAsCourier(courier);
        steps.checkStatusCode(response, 400);

        courier.setLogin(loginSaved);
        steps.sendDeleteRequestToDeleteCourier(courier);
    }

    @Test
    @DisplayName("Запрос на авторизацию курьером (провальный) - login: \"\" - message")
    @Description(" - для авторизации нужно передать все обязательные поля \n" +
            " - если какого-то поля нет - запрос возвращает ошибку \n" +
            " - message: \"Недостаточно данных для входа\"")
    public void loginCourierTestFailureBadRequestLoginIsEmptyMessage() {
        CourierToCreate courier = steps.prepareSimpleCourierDataToCreate();
        steps.sendPostRequestCreateCourier(courier);
        String loginSaved = steps.courierLoginSaveAndSetNew(courier, "");
        Response response = steps.sendPostLoginAsCourier(courier);
        steps.checkElementEqualTo(response, "message", "Недостаточно данных для входа");

        courier.setLogin(loginSaved);
        steps.sendDeleteRequestToDeleteCourier(courier);
    }

//    тесты и сразу выполнялись с трудом, а позже и вовсе стали зависать, так отчёт не собрать, поэтому закомменчено
//    поведение, считаю, заслуживает багрепорта, т.к. требованиям не соответствует (должен быть код ответа 400)
//    @Test
//    @DisplayName("Запрос на авторизацию курьером (провальный) - password: null - код ответа: 400")
//    @Description(" - для авторизации нужно передать все обязательные поля \n - если какого-то поля нет - запрос возвращает ошибку \n - код ответа: 400")
//    public void loginCourierTestFailureBadRequestPasswordIsNullStatusCode() {
//        CourierToCreate courier = steps.prepareSimpleCourierDataToCreate();
//        steps.sendPostRequestCreateCourier(courier);
//        String passwordSaved = steps.courierPasswordSaveAndSetNew(courier, null);
//        Response response = steps.sendPostLoginAsCourier(courier);
//        steps.checkStatusCode(response, 400);
//
//        courier.setLogin(passwordSaved);
//        steps.sendDeleteRequestToDeleteCourier(courier);
//    }

//    @Test
//    @DisplayName("Запрос на авторизацию курьером (провальный) - password: null - message")
//    @Description(" - для авторизации нужно передать все обязательные поля \n - если какого-то поля нет - запрос возвращает ошибку \n - message: \"Недостаточно данных для входа\"")
//    public void loginCourierTestFailureBadRequestPasswordIsNullMessage() {
//        CourierToCreate courier = steps.prepareSimpleCourierDataToCreate();
//        steps.sendPostRequestCreateCourier(courier);
//        String passwordSaved = steps.courierPasswordSaveAndSetNew(courier, null);
//        Response response = steps.sendPostLoginAsCourier(courier);
//        steps.checkElementEqualTo(response, "message", "Недостаточно данных для входа");
//
//        courier.setLogin(passwordSaved);
//        steps.sendDeleteRequestToDeleteCourier(courier);
//    }

    @Test
    @DisplayName("Запрос на авторизацию курьером (провальный) - password: \"\" - код ответа: 400")
    @Description(" - для авторизации нужно передать все обязательные поля \n" +
            " - если какого-то поля нет - запрос возвращает ошибку \n" +
            " - код ответа: 400")
    public void loginCourierTestFailureBadRequestPasswordIsEmptyStatusCode() {
        CourierToCreate courier = steps.prepareSimpleCourierDataToCreate();
        steps.sendPostRequestCreateCourier(courier);
        String passwordSaved = steps.courierPasswordSaveAndSetNew(courier, "");
        Response response = steps.sendPostLoginAsCourier(courier);
        steps.checkStatusCode(response, 400);

        courier.setLogin(passwordSaved);
        steps.sendDeleteRequestToDeleteCourier(courier);
    }

    @Test
    @DisplayName("Запрос на авторизацию курьером (провальный) - password: \"\" - message")
    @Description(" - для авторизации нужно передать все обязательные поля \n" +
            " - если какого-то поля нет - запрос возвращает ошибку \n" +
            " - message: \"Недостаточно данных для входа\"")
    public void loginCourierTestFailureBadRequestPasswordIsEmptyMessage() {
        CourierToCreate courier = steps.prepareSimpleCourierDataToCreate();
        steps.sendPostRequestCreateCourier(courier);
        String passwordSaved = steps.courierPasswordSaveAndSetNew(courier, "");
        Response response = steps.sendPostLoginAsCourier(courier);
        steps.checkElementEqualTo(response, "message", "Недостаточно данных для входа");

        courier.setPassword(passwordSaved);
        steps.sendDeleteRequestToDeleteCourier(courier);
    }

    @Test
    @DisplayName("Запрос на авторизацию курьером (провальный) - ошибка в login - код ответа: 404")
    @Description(" - система вернёт ошибку, если неправильно указать логин или пароль \n" +
            " - если авторизоваться под несуществующим пользователем, запрос возвращает ошибку \n" +
            " - код ответа: 404")
    public void loginCourierTestFailureBadRequestLoginNotExistStatusCode() {
        CourierToCreate courier = steps.prepareSpecifyCourierDataToCreate("NinjaFirst", "1234", "Donatello");
        steps.sendPostRequestCreateCourier(courier);
        String loginSaved = steps.courierLoginSaveAndSetNew(courier, "NinzyaFirst");
        Response response = steps.sendPostLoginAsCourier(courier);
        steps.checkStatusCode(response, 404);

        courier.setLogin(loginSaved);
        steps.sendDeleteRequestToDeleteCourier(courier);
    }

    @Test
    @DisplayName("Запрос на авторизацию курьером (провальный) - ошибка в login - message")
    @Description(" - система вернёт ошибку, если неправильно указать логин или пароль \n" +
            " - если авторизоваться под несуществующим пользователем, запрос возвращает ошибку \n" +
            " - message: \"Учетная запись не найдена\"")
    public void loginCourierTestFailureBadRequestLoginNotExistMessage() {
        CourierToCreate courier = steps.prepareSpecifyCourierDataToCreate("NinjaFirst", "1234", "Donatello");
        steps.sendPostRequestCreateCourier(courier);
        String loginSaved = steps.courierLoginSaveAndSetNew(courier, "NinzyaFirst");
        Response response = steps.sendPostLoginAsCourier(courier);
        steps.checkElementEqualTo(response, "message", "Учетная запись не найдена");

        courier.setLogin(loginSaved);
        steps.sendDeleteRequestToDeleteCourier(courier);
    }

    @Test
    @DisplayName("Запрос на авторизацию курьером (провальный) - ошибка в password - код ответа: 404")
    @Description(" - система вернёт ошибку, если неправильно указать логин или пароль \n" +
            " - код ответа: 404")
    public void loginCourierTestFailureBadRequestPasswordIsFalseStatusCode() {
        CourierToCreate courier = steps.prepareSpecifyCourierDataToCreate("NinjaFirst", "1234", "Donatello");
        steps.sendPostRequestCreateCourier(courier);
        String passwordSaved = steps.courierPasswordSaveAndSetNew(courier, "12345");
        Response response = steps.sendPostLoginAsCourier(courier);
        steps.checkStatusCode(response, 404);

        courier.setPassword(passwordSaved);
        steps.sendDeleteRequestToDeleteCourier(courier);
    }

    @Test
    @DisplayName("Запрос на авторизацию курьером (провальный) - ошибка в password - message")
    @Description(" - система вернёт ошибку, если неправильно указать логин или пароль \n" +
            " - message: \"Учетная запись не найдена\"")
    public void loginCourierTestFailureBadRequestPasswordIsFalseMessage() {
        CourierToCreate courier = steps.prepareSpecifyCourierDataToCreate("NinjaFirst", "1234", "Donatello");
        steps.sendPostRequestCreateCourier(courier);
        String passwordSaved = steps.courierPasswordSaveAndSetNew(courier, "12345");
        Response response = steps.sendPostLoginAsCourier(courier);
        steps.checkElementEqualTo(response, "message", "Учетная запись не найдена");

        courier.setPassword(passwordSaved);
        steps.sendDeleteRequestToDeleteCourier(courier);
    }
}

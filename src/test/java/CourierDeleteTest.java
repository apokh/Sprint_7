import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import api.BaseSteps;
import POJO.CourierToCreate;

public class CourierDeleteTest {
    public BaseSteps steps = new BaseSteps();

    @Test
    @DisplayName("Запрос на удаление курьера (успешный) - код ответа: 200")
    public void deleteCourierTestStatusCode() {
        CourierToCreate courier = steps.prepareSimpleCourierDataToCreate();
        steps.sendPostRequestCreateCourier(courier);
        Response response = steps.sendDeleteRequestToDeleteCourier(courier);
        steps.checkStatusCode(response, 200);
    }

    @Test
    @DisplayName("Запрос на удаление курьера (успешный) - в теле ответа \"ok\": true")
    @Description(" - успешный запрос возвращает ok: true")
    public void deleteCourierTestSuccessfullyBodyHasOk() {
        CourierToCreate courier = steps.prepareSimpleCourierDataToCreate();
        steps.sendPostRequestCreateCourier(courier);
        Response response = steps.sendDeleteRequestToDeleteCourier(courier);
        steps.checkElementEqualTo(response, "ok", true);
    }
}

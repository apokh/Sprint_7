import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;

import api.StepsCourier;
import pojo.request.CourierToCreate;

public class CourierDeleteTest {
    public StepsCourier steps = new StepsCourier();
    public CourierToCreate courier = steps.prepareSimpleCourierDataToCreate();

    @Before
    public void createCourierToDelete() {
        steps.sendPostRequestCreateCourier(courier);
    }

    @Test
    @DisplayName("Запрос на удаление курьера (успешный) - код ответа: 200")
    public void deleteCourierTestStatusCode() {
        Response response = steps.sendDeleteRequestToDeleteCourier(courier);
        steps.checkStatusCode(response, SC_OK);
    }

    @Test
    @DisplayName("Запрос на удаление курьера (успешный) - в теле ответа \"ok\": true")
    @Description(" - успешный запрос возвращает ok: true")
    public void deleteCourierTestSuccessfullyBodyHasOk() {
        Response response = steps.sendDeleteRequestToDeleteCourier(courier);
        steps.checkElementEqualTo(response, "ok", true);
    }
}

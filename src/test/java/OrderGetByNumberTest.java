import POJO.OrderBase;
import api.BaseSteps;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

public class OrderGetByNumberTest {
    public BaseSteps steps = new BaseSteps();

    @Test
    @DisplayName("Запрос на получение заказа по трек-номеру (успешный)")
    @Description(" - успешный запрос возвращает объект с заказом")
    public void getOrdersTestNotNull() {
        OrderBase order = steps.prepareSimpleOrderDataToCreate();
        String track = steps.sendPostRequestCreateOrderAndSaveTrack(order);
        Response response = steps.sendGetRequestOrderByNumber(track);
        steps.checkDataOrderInOrderByNumber(order, track, response);
    }
}

import api.BaseSteps;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

public class OrdersGetListTest {
    public BaseSteps steps = new BaseSteps();

    @Test
    @DisplayName("Запрос на получение списка заказов (успешный) - ответ не пустой")
    @Description(" - в тело ответа возвращается список заказов")
    public void getOrdersTestNotNull() {
        Response response = steps.sendGetRequestOrders();
        steps.checkOrdersListNotEmpty(response);
    }
}

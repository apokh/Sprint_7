import api.BaseSteps;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class OrderGetByNumberNegativeTest {
    private BaseSteps steps;
    private String track;
    private Integer expectedStatusCode;
    private String expectedMessage;

    public OrderGetByNumberNegativeTest(String track, Integer expectedStatusCode, String expectedMessage) {
        this.steps = new BaseSteps();
        this.track = track;
        this.expectedStatusCode = expectedStatusCode;
        this.expectedMessage = expectedMessage;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"1", 404, "Заказ не найден"},
                {"", 400, "Недостаточно данных для поиска"}
        };
    }

    @Test
    @DisplayName("Запрос на получение заказа по трек-номеру (провальный)")
    @Description(" - запрос с несуществующим заказом возвращает ошибку \n" +
            " - запрос без номера заказа возвращает ошибку")
    public void getOrdersTestNegative() {
        Response response = steps.sendGetRequestOrderByNumber(track);
        steps.checkStatusCode(response, expectedStatusCode);
        steps.checkElementEqualTo(response, "message", expectedMessage);
    }
}



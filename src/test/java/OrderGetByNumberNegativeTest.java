import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.*;

import api.StepsOrder;

@RunWith(Parameterized.class)
public class OrderGetByNumberNegativeTest {
    private StepsOrder steps;
    private String track;
    private Integer expectedStatusCode;
    private String expectedMessage;

    public OrderGetByNumberNegativeTest(String track, Integer expectedStatusCode, String expectedMessage) {
        this.steps = new StepsOrder();
        this.track = track;
        this.expectedStatusCode = expectedStatusCode;
        this.expectedMessage = expectedMessage;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"1", SC_NOT_FOUND, "Заказ не найден"},
                {"", SC_BAD_REQUEST, "Недостаточно данных для поиска"}
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



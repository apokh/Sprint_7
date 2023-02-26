import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.*;

import api.StepsOrder;
import pojo.request.OrderBase;

@RunWith(Parameterized.class)
public class OrderCreateTest {
    private final StepsOrder steps;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final Integer rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] color;
    private final int expectedStatusCode;
    private final String expectedElement;

    public OrderCreateTest(String firstName, String lastName, String address, String metroStation, String phone, Integer rentTime, String deliveryDate, String comment, String[] color, int expectedStatusCode, String expectedElement) {
        this.steps = new StepsOrder();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
        this.expectedStatusCode = expectedStatusCode;
        this.expectedElement = expectedElement;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {

        String[] BLACK = new String[]{"BLACK"};
        String[] GREY = new String[]{"GREY"};
        String[] TWO = new String[]{"BLACK", "GREY"};
        String[] NOPE = new String[]{};

        return new Object[][]{
                {"Naruto", "Uzumaki", "Konoha, 142 apt.", "Konoha Haltestelle", "+7 800 355 35 35", 5, "2020-06-06", "Sasuke, come back to Konoha", BLACK, SC_CREATED, "track"},
                {"Naruto", "Uzumaki", "Konoha, 142 apt.", "Konoha Haltestelle", "+7 800 355 35 35", 5, "2020-06-06", "Sasuke, come back to Konoha", GREY, SC_CREATED, "track"},
                {"Naruto", "Uzumaki", "Konoha, 142 apt.", "Konoha Haltestelle", "+7 800 355 35 35", 5, "2020-06-06", "Sasuke, come back to Konoha", TWO, SC_CREATED, "track"},
                {"Naruto", "Uzumaki", "Konoha, 142 apt.", "Konoha Haltestelle", "+7 800 355 35 35", 5, "2020-06-06", "Sasuke, come back to Konoha", null, SC_CREATED, "track"},
                {"Naruto", "Uzumaki", "Konoha, 142 apt.", "Konoha Haltestelle", "+7 800 355 35 35", 5, "2020-06-06", "Sasuke, come back to Konoha", NOPE, SC_CREATED, "track"}
        };
    }

    @Test
    @DisplayName("Запрос на создание заказа (успешный)")
    @Description(" - можно указать один из цветов — BLACK или GREY;\n" +
            " - можно указать оба цвета;\n" +
            " - можно совсем не указывать цвет;\n" +
            " - тело ответа содержит track")
    public void createOrderTestStatusCode() {
        OrderBase order = steps.prepareSpecifyOrderDataToCreate(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        Response response = steps.sendPostRequestCreateOrder(order);
        steps.checkStatusCode(response, expectedStatusCode);
        steps.checkElementNotNullValue(response, expectedElement);
    }
}

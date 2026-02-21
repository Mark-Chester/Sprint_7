package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.OrderCreationModel;

import static data.TestData.GETORDERSLIST_ENDPOINT;
import static data.TestData.ORDERCREATION_ENDPOINT;
import static io.restassured.RestAssured.given;

public class OrderSteps {
    @Step("Создание заказа")
    public static Response createOrder(OrderCreationModel order){
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(order)
                .when()
                .post(ORDERCREATION_ENDPOINT);
    }
    @Step("Получение списка заказов")
    public static Response getOrderList(){
        return given()
                .log().all()
                .get(GETORDERSLIST_ENDPOINT);
    }
}

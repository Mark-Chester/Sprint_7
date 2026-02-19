package steps;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.CourierDeleteBody;
import models.CourierLoginModel;
import models.CourierModel;

import static data.TestData.*;
import static io.restassured.RestAssured.given;

public class CourierSteps {
    @Step("Получение ответа о создании курьера")
    public static Response createCourierResponse(CourierModel courier) { // Получение ответа о создании курьера
        return createCourier(courier)
                .then()
                .extract()
                .response();
    }
    @Step("Создание курьера")
    public static Response createCourier(CourierModel courier) { //Создание курьера
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post(COURIERCREATION_ENDPOINT);
    }

    // Логин курьера
    @Step("Авторизация курьера")
    public static Response loginCourier(CourierLoginModel courier){
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post(COURIERLOGIN_ENDPOINT);
    }
    // Удаление курьера
    @Step("Удаление аккаунта курьера")
    public static void deleteCourier(CourierDeleteBody deleteBody, int id){
         given()
                 .log().all()
                 .contentType(ContentType.JSON)
                 .body(deleteBody)
                 .when()
                 .delete(COURIERDELETE_ENDPOINT + id);
    }
    @Step("Авторизация, получение id, удаление аккаунта")
    public static void deleteAfterLogin(CourierLoginModel courier){
        CourierDeleteBody delete = loginCourier(courier).then().extract().response().as(CourierDeleteBody.class);
        int id = delete.getId();
        deleteCourier(delete,id);
    }// вход, получение id, удаление

}


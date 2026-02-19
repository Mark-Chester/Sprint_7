import jdk.jfr.Description;
import models.CourierModel;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static data.TestData.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static steps.CourierSteps.createCourierResponse;

public class CourierCreationTests extends BaseApiTest{
    @Test
    @DisplayName("Проверка на успешное создание курьера")
    @Description("курьера можно создать,успешный запрос возвращает ok: true;запрос возвращает правильный код ответа 201;")
    public void successCourierCreationTest(){
        CourierModel courier = new CourierModel(LOGIN,PASSWORD,FIRSTNAME);
        createCourierResponse(courier)
                .then()
                .log().all()
                .statusCode(201)
                .body("ok", equalTo(true));
    }
    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    @Description("Нелья создать курьера с занятым логином")
    public void cantDuplicateCourierTest(){
        CourierModel courier = new CourierModel(LOGIN,PASSWORD,FIRSTNAME);
        createCourierResponse(courier)
                .then()
                .log().all()
                .statusCode(201)
                .body("ok", equalTo(true));
        createCourierResponse(courier)
                .then()
                .log().all()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется"));
    }
    @Test
    @DisplayName("Проверка на создание курьера без обязательного поля логин")
    @Description("если одного из обязательных полей нет, запрос возвращает ошибку;")
    public void cantCreateCourierWithoutLoginTest(){
        CourierModel courier = new CourierModel(null,PASSWORD,FIRSTNAME);
        createCourierResponse(courier)
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
    @Test
    @DisplayName("Проверка на создание курьера без обязательного поля пароль")
    @Description("если одного из обязательных полей нет, запрос возвращает ошибку;")
    public void cantCreateCourierWithoutPasswordTest(){
        CourierModel courier = new CourierModel(LOGIN,null,FIRSTNAME);
        createCourierResponse(courier)
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}

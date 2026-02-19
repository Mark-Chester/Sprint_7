import jdk.jfr.Description;
import models.CourierLoginModel;
import models.CourierModel;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static data.TestData.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static steps.CourierSteps.*;

public class CourierLoginTests extends BaseApiTest{
    @Test
    @DisplayName("Курьер может авторизоваться")
    @Description("успешный запрос возвращает id.")
    public void succesCourierLogin(){
        CourierModel courier = new CourierModel(LOGIN,PASSWORD,FIRSTNAME);
        createCourierResponse(courier);
        CourierLoginModel login = new CourierLoginModel(LOGIN,PASSWORD);
        loginCourier(login)
                .then()
                .log().all()
                .statusCode(200)
                .body("id", instanceOf(Integer.class));
    }
    @Test
    @DisplayName("Нельзя авторизоваться без поля логин")
    @Description("если какого-то поля нет, запрос возвращает ошибку 400;")
    public void cantLoginWithouLogin(){
        CourierModel courier = new CourierModel(LOGIN,PASSWORD,FIRSTNAME);
        createCourierResponse(courier);
        CourierLoginModel login = new CourierLoginModel(null,PASSWORD);
        loginCourier(login)
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }
    @Test
    @DisplayName("Нельзя авторизоваться без поля пароль")
    @Description("если какого-то поля нет, запрос возвращает ошибку 400;")
    public void cantLoginWithoutPassword(){
        CourierModel courier = new CourierModel(LOGIN,PASSWORD,FIRSTNAME);
        createCourierResponse(courier);
        CourierLoginModel login = new CourierLoginModel(LOGIN,null);
        loginCourier(login)
                .then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }
    @Test
    @DisplayName("Нельзя авторизоваться под несуществующим курьером")
    @Description("если авторизоваться под несуществующим пользователем, запрос возвращает ошибку 404;")
    public void cantLoginWithUncreatedCourier() {
        CourierModel courier = new CourierModel(LOGIN,PASSWORD,FIRSTNAME);
        createCourierResponse(courier);
        CourierLoginModel login = new CourierLoginModel(LOGIN,PASSWORD);
        deleteAfterLogin(login);
        loginCourier(login)
                .then()
                .log().all()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

}

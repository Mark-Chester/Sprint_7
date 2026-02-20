import jdk.jfr.Description;
import models.CourierLoginModel;
import models.CourierModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.apache.http.HttpStatus.*;
import static data.TestData.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static steps.CourierSteps.*;

public class CourierLoginTests extends BaseApiTest{
    @Test
    @DisplayName("Курьер может авторизоваться")
    @Description("успешный запрос возвращает id.")
    public void succesCourierLogin(){
        CourierLoginModel login = new CourierLoginModel(LOGIN,PASSWORD);
        loginCourier(login)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .body("id", instanceOf(Integer.class));
    }
    @Test
    @DisplayName("Нельзя авторизоваться без поля логин")
    @Description("если какого-то поля нет, запрос возвращает ошибку 400;")
    public void cantLoginWithouLogin(){
        CourierLoginModel login = new CourierLoginModel(null,PASSWORD);
        loginCourier(login)
                .then()
                .log().all()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));
    }
    @Test
    @DisplayName("Нельзя авторизоваться без поля пароль")
    @Description("если какого-то поля нет, запрос возвращает ошибку 400;")
    public void cantLoginWithoutPassword(){
        CourierLoginModel login = new CourierLoginModel(LOGIN,null);
        loginCourier(login)
                .then()
                .log().all()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));
    }
    @Test
    @DisplayName("Нельзя авторизоваться под несуществующим курьером")
    @Description("если авторизоваться под несуществующим пользователем, запрос возвращает ошибку 404;")
    public void cantLoginWithUncreatedCourier() {
        CourierLoginModel login = new CourierLoginModel(LOGIN,PASSWORD);
        deleteAfterLogin(login);
        loginCourier(login)
                .then()
                .log().all()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }
    @After
    public void shtDwn(){
        CourierLoginModel courier = new CourierLoginModel(LOGIN,PASSWORD);
        deleteAfterLogin(courier);
    }
    @Before
    public void createCourier(){
        CourierModel courier = new CourierModel(LOGIN,PASSWORD,FIRSTNAME);
        createCourierResponse(courier);
    }
}

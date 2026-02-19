import io.restassured.RestAssured;
import models.CourierLoginModel;
import org.junit.After;
import org.junit.BeforeClass;

import static data.TestData.*;
import static steps.CourierSteps.deleteAfterLogin;


public class BaseApiTest {
    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = BASE_URL;

    }
    @After
    public void shtDwn(){
       CourierLoginModel courier = new CourierLoginModel(LOGIN,PASSWORD);
       deleteAfterLogin(courier);
    }
}
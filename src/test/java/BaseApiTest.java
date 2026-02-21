import io.restassured.RestAssured;
import org.junit.BeforeClass;

import static data.TestData.*;


public class BaseApiTest {
    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = BASE_URL;

    }
}
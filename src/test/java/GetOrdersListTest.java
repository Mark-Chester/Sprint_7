import jdk.jfr.Description;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;


import static org.hamcrest.CoreMatchers.notNullValue;
import static steps.OrderSteps.getOrderList;

public class GetOrdersListTest extends BaseApiTest{
    @Test
    @DisplayName("Получение списка заказов")
    @Description("Можно получить список с заказами")
    public void getOrdersListTest(){
        getOrderList()
                .then()
                .statusCode(200)
                .body("orders", notNullValue());
    }
}

import jdk.jfr.Description;
import models.OrderCreationModel;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static steps.OrderSteps.createOrder;

@RunWith(Parameterized.class)
public class OrderCreationTest extends BaseApiTest{
    private List<String> color;
    public OrderCreationTest(List<String> color){
        this.color = color;
    }
    @Parameterized.Parameters (name = "Цвет самоката {0}")
    public static Object[][] datagen(){
        return new Object[][] {
                {List.of("BLACK")},
                {List.of("GRAY")},
                {List.of("BLACK","GRAY")},
                {List.of()}
        };
    }
    @Test
    @DisplayName("Создание заказа")
    @Description("Можно создать заказ указав один из цветов, ни одного или всех из доступных")
    public void orderCreation(){
        OrderCreationModel order = new OrderCreationModel(color);
        createOrder(order)
                .then()
                .statusCode(201)
                .body("track", instanceOf(Integer.class));
    }
}

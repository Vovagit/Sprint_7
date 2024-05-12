import helpers.OrderChecks;
import helpers.OrderClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import pojo.Order;
import java.util.List;
import static org.junit.Assert.assertNotEquals;

public class OrderListTest {

    private final OrderClient client = new OrderClient();
    private final OrderChecks check = new OrderChecks();

    @Test
    @DisplayName("Get list of orders test")
    public void getListOfOrdersTest(){
        ValidatableResponse getOrdersResponse= client.getOrders();
        List<Order> orders = check.checkSuccessGetOrders(getOrdersResponse);

        assertNotEquals(0, orders.size());

    }
}

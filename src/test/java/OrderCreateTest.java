import constants.TestValues;
import io.restassured.response.ValidatableResponse;
import helpers.OrderChecks;
import helpers.OrderClient;
import pojo.OrderCreate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertNotEquals;

@RunWith(Parameterized.class)
public class OrderCreateTest {
    private final OrderClient client = new OrderClient();
    private final OrderChecks check = new OrderChecks();

    private final String firstName;
    private final String lastName;
    private final String address;
    private final int metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] color;

    public OrderCreateTest(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {

        return TestValues.ORDER_CREATE_PARAMETERS;

    }
    @Test
    public void createOrderTest(){
        OrderCreate orderCreate = new OrderCreate(firstName,lastName,address,metroStation,phone,rentTime,deliveryDate,comment,color);
        ValidatableResponse createResponse=client.createOrder(orderCreate);
        int orderTrack=check.createSuccessOrder(createResponse);
        assertNotEquals(0, orderTrack);

    }
}

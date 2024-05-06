import constants.TestValues;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import helpers.CourierChecks;
import helpers.CourierClient;
import pojo.CourierCreate;
import pojo.CourierLogin;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertNotEquals;
public class CourierCreateTest {

    private final CourierClient client = new CourierClient();
    private final CourierChecks check = new CourierChecks();
    int courierId;

    @After
    public void deleteCourier() {
        if (courierId != 0) {
            ValidatableResponse response = client.deleteCourier(courierId);
            check.deletedSuccesfully(response);
        }
    }

    @Test
    @DisplayName("Test create courier")
    public void courierCreateTest() {
        CourierCreate courier = CourierCreate.random();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.createdSuccessfully(createResponse);

        CourierLogin creds = CourierLogin.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = check.loggedInSuccessfully(loginResponse);
        assertNotEquals(0, courierId);
    }

    @Test
    @DisplayName("Test create two identical courier")
    public void courierTwoIdenticalTest(){
        CourierCreate courier = CourierCreate.random();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.createdSuccessfully(createResponse);
        createResponse = client.createCourier(courier);
        check.createdSameLogin(createResponse, TestValues.CREATED_SAME_LOGIN);

        CourierLogin creds = CourierLogin.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = check.loggedInSuccessfully(loginResponse);
        assertNotEquals(0, courierId);
    }

    @Test
    @DisplayName("Test create with only Login")
    public void courierCreateWithOnlyLoginTest() {
        CourierCreate courier = new CourierCreate();
        courier.setLogin(RandomStringUtils.randomAlphabetic(5, 15));
        ValidatableResponse createResponse = client.createCourier(courier);
        check.createdWithoutRequiredFields(createResponse,TestValues.CREATED_REQUIRED);
    }

    @Test
    @DisplayName("Test create with only Password")
    public void courierCreateWithOnlyPasswordTest() {
        CourierCreate courier = new CourierCreate();
        courier.setPassword(RandomStringUtils.randomAlphabetic(5, 15));
        ValidatableResponse createResponse = client.createCourier(courier);
        check.createdWithoutRequiredFields(createResponse,TestValues.CREATED_REQUIRED);
    }

    @Test
    @DisplayName("Test create with only firstName")
    public void courierCreateWithOnlyFirstNameTest() {
        CourierCreate courier = new CourierCreate();
        courier.setFirstName(RandomStringUtils.randomAlphabetic(5, 15));
        ValidatableResponse createResponse = client.createCourier(courier);
        check.createdWithoutRequiredFields(createResponse,TestValues.CREATED_REQUIRED);
    }





}

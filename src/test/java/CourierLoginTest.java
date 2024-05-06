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

public class CourierLoginTest {
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
    @DisplayName("Test success login courier")
    public void courierSuccessLoginTest(){
        CourierCreate courier = CourierCreate.random();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.createdSuccessfully(createResponse);

        CourierLogin creds = CourierLogin.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = check.loggedInSuccessfully(loginResponse);
        assertNotEquals(0, courierId);
    }

    @Test
    @DisplayName("Test login courier only with Login")
    public void courierLoginWithOnlyLoginTest() {
        CourierCreate courier = CourierCreate.random();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.createdSuccessfully(createResponse);
        CourierLogin badCreds = CourierLogin.from(courier);
        badCreds.setPassword("");
        ValidatableResponse badLoginResponse = client.loginCourier(badCreds);
        check.loggedInWithoutRequiredFields(badLoginResponse, TestValues.LOGIN_REQUIRED_MESSAGE);

        CourierLogin creds = CourierLogin.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = check.loggedInSuccessfully(loginResponse);
        assertNotEquals(0, courierId);
    }

    @Test
    @DisplayName("Test login courier only with Password")
    public void courierLoginWithOnlyPasswordTest() {
        CourierCreate courier = CourierCreate.random();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.createdSuccessfully(createResponse);
        CourierLogin badCreds = CourierLogin.from(courier);
        badCreds.setLogin("");
        ValidatableResponse badLoginResponse = client.loginCourier(badCreds);
        check.loggedInWithoutRequiredFields(badLoginResponse,TestValues.LOGIN_REQUIRED_MESSAGE);

        CourierLogin creds = CourierLogin.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = check.loggedInSuccessfully(loginResponse);
        assertNotEquals(0, courierId);
    }
    @Test
    @DisplayName("Test login courier with non existent Login")
    public void courierLoginNonExistent(){
        CourierCreate courier = CourierCreate.random();
        CourierLogin creds = CourierLogin.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        check.loggedNonExistent(loginResponse,TestValues.LOGIN_NON_EXISTENT);
    }

    @Test
    @DisplayName("Test login courier with non existent Password")
    public void courierPasswordNonExistent(){
        CourierCreate courier = CourierCreate.random();
        ValidatableResponse createResponse = client.createCourier(courier);
        check.createdSuccessfully(createResponse);
        CourierLogin badCreds = CourierLogin.from(courier);
        badCreds.setPassword(RandomStringUtils.randomAlphabetic(5, 15));
        ValidatableResponse badLoginResponse = client.loginCourier(badCreds);
        check.loggedNonExistent(badLoginResponse,TestValues.LOGIN_NON_EXISTENT);

        CourierLogin creds = CourierLogin.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = check.loggedInSuccessfully(loginResponse);
        assertNotEquals(0, courierId);

    }
}

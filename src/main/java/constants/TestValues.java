package constants;

public class TestValues {
    public static final String LOGIN_REQUIRED_MESSAGE="Недостаточно данных для входа";
    public static final String LOGIN_NON_EXISTENT="Учетная запись не найдена";

    public static final String CREATED_SAME_LOGIN="Этот логин уже используется. Попробуйте другой.";
    public static final String CREATED_REQUIRED="Недостаточно данных для создания учетной записи";


    public static final Object[][] ORDER_CREATE_PARAMETERS = {
        {"TestName","TestFirstName","TestAddress",4,"123123123",1,"2024","comment", new String[]{"BLACK"}},
        {"TestName","TestFirstName","TestAddress",4,"123123123",1,"2024","comment", new String[]{"GRAY"}},
        {"TestName","TestFirstName","TestAddress",4,"123123123",1,"2024","comment", new String[]{"BLACK","GRAY"}},
        {"TestName","TestFirstName","TestAddress",4,"123123123",1,"2024","comment", new String[]{}}
    };


}

package starter.mentutor;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.Constants;

import java.io.File;

public class MentutorAPIAdmin {

    public static String LOGIN_AS_ADMIN = Constants.BASE_URL + "login";

    public static String REGISTER_NEW_USERS_ADMIN = Constants.BASE_URL + "admin/users";

//    ADMIN

    @Step("Login as admin")
    public void loginAsAdmin() {
        SerenityRest.given()
                .headers("Authorization", MentutorResponsesAdmin.ADMIN_TOKEN)
    }

    @Step("Register new user as admin")
    public void registerNewUserAsAdmin(File json) {
        SerenityRest.given()
                .headers("Authorization", MentutorResponsesAdmin.ADMIN_TOKEN)
                .contentType(ContentType.JSON)
                .body(json);
    }
}

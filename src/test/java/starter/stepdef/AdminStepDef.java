package starter.stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import starter.mentutor.MentutorAPIAdmin;
import starter.mentutor.MentutorResponsesAdmin;
import starter.utils.Constants;

import java.io.File;


import static org.hamcrest.Matchers.equalTo;

public class AdminStepDef {

    @Steps
    MentutorAPIAdmin mentutorAPIAdmin;

    String textResponse = "";

//    LOGIN AS ADMIN

    @Given("User login as admin with valid {string}")
    public void userLoginAsAdminWithValid(String arg0) {

    }

//    REGISTER NEW USER VALID
    @Given("Post register new user as admin with valid {string}")
    public void postRegisterNewUserAsAdminWithValid(String json) {

        File jsonFile = new File(Constants.REQ_BODY + json);
        mentutorAPIAdmin.registerNewUserAsAdmin(jsonFile);
    }

    @When("Send request register user as admin")
    public void sendRequestRegisterUserAsAdmin() {
        Response response = SerenityRest.when()
                .post(MentutorAPIAdmin.REGISTER_NEW_USERS_ADMIN);
        textResponse = response.asString();
        JsonPath jsonPath = response.jsonPath();
        String token = jsonPath.get("data.name");
        System.out.println(token);
        MentutorResponsesAdmin.ADMIN_TOKEN_1 = token;
    }

    @And("Response body should be name {string}")
    public void responseBodyShouldBeName(String name) {
        SerenityRest.and()
                .body(MentutorResponsesAdmin.USER_NAME_ADMIN, equalTo(name));
    }

    @Then("Status code should be {int}")
    public void statusCodeShouldBe(int statusCode) {
        SerenityRest.then()
                .statusCode(statusCode);
    }

    @And("Validate json schema {string}")
    public void validateJsonSchema(String json) {
        File jsonFile = new File(Constants.JSON_SCHEMA + json);
        SerenityRest.and()
                .body(JsonSchemaValidator.matchesJsonSchema(jsonFile));

    }


}

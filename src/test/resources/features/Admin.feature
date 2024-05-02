@Projects
  Feature: Admin
    As admin i can edit mentor mentee, add class and member


#    Login as admin
  Scenario: login as admin
    Given User login as admin with valid "AdminLogin.json"
    When Send request login user as admin
    Then Status code should be 200
    And Response body should be name "admin"
    And Validate json schema "AdminLoginJsonSchema.json"

#  Register new user as admin
  Scenario: Register new user as admin
    Given Post register new user as admin with valid "AdminRegisterUser.json"
    When Send request register user as admin
    Then Status code should be 201
    And Response body should be name "Soma Mentee Delapan"
    And Validate json schema "AdminRegisterUserJsonSchema.json"



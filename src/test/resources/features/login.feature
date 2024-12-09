@LoginFeature
Feature: Login page for open cart

  Background:
    Given I am on the opencart login page

  Scenario: Successful login with valid credentials
    Given Enter username and password
    When Click on login button
    Then Verify logged in successfully

  Scenario Outline: Unsuccessful login with invalid or empty credentials
    Given Enter invalid "<username>" and "<password>"
    When Click on login button
    Then Verify the error message "<error_message>"
    Examples:
      | username          | password        | error_message                                         |
      | invalid@gmail.com | invalidpassword | Warning: No match for E-Mail Address and/or Password. |
      | abccd             | validPassword   | Warning: No match for E-Mail Address and/or Password. |
      | valid@gmail.com   | abccd           | Warning: No match for E-Mail Address and/or Password. |

    Scenario: Navigating to forgot password page
    When I click on the "Forgotten Password" link
    Then I should be redirected to the password reset page

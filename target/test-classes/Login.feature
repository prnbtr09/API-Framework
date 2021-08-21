Feature: verify List of Users from API

  @Users
  Scenario: Title of your scenario
    Given User has logged in to app with following users
      | userName | Password |domain|cookie|
      | admin    | admin    |domainName|cookieName|
      And Service "service-port/web" with endPoint "holdings/test/test1" is setup
    And Provided paramters
    |paramName|paramType|paramValue|
    |asOfTimeStamp|QUERY_PARAM|currentTimeStamp|
    When A GET call is made
    Then verify status code is 200
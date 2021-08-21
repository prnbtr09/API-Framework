package Features;

import org.junit.Assert;

import Features.Utils.Scenario;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;

public class ResponseValidation {
	@Then("verify status code is {int}")
	public void verify_status_code_is(Integer statuCode) {
	   Scenario scenario=Scenario.getScenario();
	   
	   int actualCode=scenario.getResponse().getStatusCode();
	   
	  Assert.assertTrue(null, actualCode==statuCode);
	   
	   
		
	  String response=scenario.getResponse().asString();
	  
String expectedValue=JsonPath.from(response).get("parameterName");
		
		
		
		
	}
	
	

	
}

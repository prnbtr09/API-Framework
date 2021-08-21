package Features;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import Features.Utils.GetEndPointParamters;
import Features.Utils.Scenario;
import Features.Utils.Session;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIInvocation {

	Logger log=LogManager.getLogger(APIInvocation.class);
	
	@Given("User has logged in to app with following users")
	public void verifLogin(DataTable dataTable) {
	
		List<Map<String,String>> mapList=dataTable.asMaps(String.class,String.class);
	
		
		
		
		Session session=Session.getSession();
		
		for(Map<String,String> map:mapList) {
			session.setDomain(map.get("domain"));
			session.loginVerify(map.get("userName"),map.get("password"),map.get("domain"),map.get("cookie"));
			
		}

	}
	

	@Given("Provided paramters")
	public void getParamters(List<Map<String,String>> parameters) {
	  
	Scenario scenario=Scenario.getScenario();
	List<GetEndPointParamters> params=new ArrayList<GetEndPointParamters>();
	
	for(Map<String,String> param:parameters) {
		params.add(getParameterEntry(param));
	}
	
	scenario.setParams(params);
	
	
	}
	
private static GetEndPointParamters getParameterEntry(Map<String,String> param) {
	
	return new GetEndPointParamters(param.get("paramName"),param.get("paramType"),param.get("value"));
		
	}


@Given("Service {string} with endPoint {string} is setup")
public void service_with_end_point_is_setup(String service, String endPoint) {
	Scenario scenario=Scenario.getScenario();
	scenario.setService("/"+service+"/"+scenario.getConfig().getString(service+".SprintVersion")+"/"+endPoint);
	scenario.setEndPointType(service);
   
}

@When("A GET call is made")
public void a_get_call_is_made() {
    Session session=Session.getSession();
    Scenario scenario=Scenario.getScenario();
    
    String url=scenario.getURL();
    
    Response res=RestAssured.given().header("cookie",session.getCookieName()+"="+session.getToken()).when().get(url).then().statusCode(200).extract().response();
    
    scenario.setResponse(res);
	
	
}
	
	
}

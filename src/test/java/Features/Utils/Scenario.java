package Features.Utils;

import java.util.List;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import Features.Utils.GetEndPointParamters.ParamType;
import io.restassured.response.Response;

public class Scenario {

	private Response response;
	private String token;
	private String service;
	private String endPoint;
	private String endPointType;
	private List<GetEndPointParamters> params;
	private Configuration config=null;
	
	public static Scenario INSTANCE;
	
	private Scenario() {
		
		try {
			config=new Configurations().properties(Thread.currentThread().getContextClassLoader().getResource("application.properties").getPath());
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static Scenario newScenario() {
		INSTANCE=new Scenario();
		
		return INSTANCE;
	}
	
	public static Scenario getScenario() {
		
		return INSTANCE;
	}
	
	
	public String getURL() {
		
		if(params==null || params.isEmpty()) {
			return "https://"+Session.getSession().getDomain()+this.endPoint;
		}else {
			
			String url="https://"+Session.getSession().getDomain()+this.endPoint;
			
			boolean pathEnded=false;
			for(GetEndPointParamters parameter:params) {
				if(ParamType.PATH_PARAM.equals(parameter.getParamType())) {
					url=url+"/"+parameter.getValue();
				}else {
					if(!pathEnded) {
						url=url+"?";
						pathEnded=true;
						url=url+parameter.getParamName()+"="+parameter.getValue();
						continue;
					}
					url=url+"&"+parameter.getParamName()+"="+parameter.getValue();
				}
			}
			
			return url;
		}
		
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public List<GetEndPointParamters> getParams() {
		return params;
	}

	public void setParams(List<GetEndPointParamters> params) {
		this.params = params;
	}

	public Configuration getConfig() {
		return config;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}

	public static Scenario getINSTANCE() {
		return INSTANCE;
	}

	public static void setINSTANCE(Scenario iNSTANCE) {
		INSTANCE = iNSTANCE;
	}


	public String getEndPointType() {
		return endPointType;
	}


	public void setEndPointType(String endPointType) {
		this.endPointType = endPointType;
	}
	
	
	
	
	
	
	
	
	
	
}

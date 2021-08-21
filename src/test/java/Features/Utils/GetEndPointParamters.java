package Features.Utils;

import java.util.List;

import io.restassured.response.Response;

public class GetEndPointParamters {
	
	private String  paramName;
	private ParamType  paramType;
	private String  value;
	
	
	public GetEndPointParamters(String paramName,String paramType,String value) {
		this.paramName=paramName;
		this.paramType=ParamType.valueOf(paramType);
		
		if(paramName.equals("some specific date")) {
			//write code for same and put the value
			this.value="value we get from above code";
		}else {
			this.value=value;
		}
		

		
	}
	
	
	public enum ParamType{
		PATH_PARAM,QUERY_PARAM
		
	}


	public String getParamName() {
		return paramName;
	}


	public ParamType getParamType() {
		return paramType;
	}



	public String getValue() {
		return value;
	}
	
	
}

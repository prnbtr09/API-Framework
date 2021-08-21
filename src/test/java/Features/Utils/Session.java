package Features.Utils;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Session {

	private static Session INSTANCE;
	private String domain;
	private String cookieName;
	private String token;
	private Map<String, String> cookies;
	
	
	public static Session getINSTANCE() {
		return INSTANCE;
	}

	public static void setINSTANCE(Session iNSTANCE) {
		INSTANCE = iNSTANCE;
	}

	

	private Session() {
		
	}
	
	public synchronized static Session getSession() {
		
		if(INSTANCE==null) {
			INSTANCE=new Session();
		}
		return INSTANCE;
		
	}
	
	
	
	public void loginVerify(String userName, String password, String domain,String cookie) {
		this.domain=domain;
		this.cookieName=cookie;
		
		Response Response=RestAssured.given().contentType(ContentType.JSON).body("{\"userId\": \""+userName+"\",\r\n"
				+ "\"password\": \""+password+"\"\r\n"
				+ "}").when().post("https://"+domain+"/auth/verify/login").then().statusCode(200).extract().response();
		
		cookies=Response.getCookies();
		token=Response.getCookie(cookie);
		
	}

	
	
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getCookieName() {
		return cookieName;
	}

	public void setCookieName(String cookieName) {
		this.cookieName = cookieName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Map<String, String> getCookies() {
		return cookies;
	}

	public void setCookies(Map<String, String> cookies) {
		this.cookies = cookies;
	}
}

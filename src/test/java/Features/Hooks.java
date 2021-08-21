package Features;

import Features.Utils.Scenario;
import Features.Utils.Session;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	@Before
	public void setUp() {
		System.out.println("In Before");
		
	Session.getSession();
	Scenario.newScenario();
	
	}
	
	@After
	public void tearDown() {
		System.out.println("In after");
	}

}

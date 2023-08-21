package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

public class TestngRunner {
	
	@CucumberOptions(features={"src/test/java/INFOSYS/BMC_UI/"}, 
			glue= {"StepDefination"}
	
	
			 )

			public class Runnerclass extends AbstractTestNGCucumberTests {
				
				

			}


}

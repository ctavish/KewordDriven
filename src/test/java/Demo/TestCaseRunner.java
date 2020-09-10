package Demo;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
 

public class TestCaseRunner
{
	WebDriver ffDriver;
	Logger log = Logger.getLogger(TestCaseRunner.class);
	
	private void runTestCase(List<TestCaseDTO> tcDtoList, List<WebElementDTO> weDtoList) throws InterruptedException
	  {
		System.setProperty("webdriver.chrome.driver","/Users/tavishchadha/Tavish_Workspace/KeywordDriven/chromedriver");
		ffDriver = new ChromeDriver();
		ffDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		ffDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		if(tcDtoList!=null && weDtoList!=null)
		{
			TestActionPerformer actionPerformer = new TestActionPerformer();
			int tcSize = tcDtoList.size();
			for(int i=0;i<tcSize;i++)
			{
				TestCaseDTO tcDto = tcDtoList.get(i);
				String tcName = tcDto.getName();
				System.out.println("Run test case : " + tcName);
				List<TestStepDTO> stepList = tcDto.getStepList();
				int stepSize = stepList.size();
				for(int j=0;j<stepSize;j++)
				{
					TestStepDTO stepDto = stepList.get(j);
					String action = stepDto.getActionKeyword();
					String symbolName = stepDto.getWebEleSymbolName();
					String value = stepDto.getWebEleValue();
					if(action.length()>0)
					{
						System.out.println("Run step " + j + " : " + action + " , "  + symbolName + " , " + value);
						actionPerformer.performAction(action, WebElementManager.getWebEleDtoBySymbolName(weDtoList, symbolName), value, ffDriver, log);
					}
				}
			}
		}
		
		ffDriver.quit();
	  }
	
	@Test
	  public void Run() throws InterruptedException, IOException {
		  
		  String excelFilePath = "/Users/tavishchadha/Tavish_Workspace/KeywordDriven/TestData.xlsx";
		  
		  String tcDataSheetName = "Test Case";
		  
		  String weDataSheetName = "Web Element";
		  
		  
		  TestCaseManager tcManager = new TestCaseManager();
		  List<TestCaseDTO> tcDtoList = tcManager.readTestCaseData(excelFilePath, tcDataSheetName);
		  
		  WebElementManager weManager = new WebElementManager();
		  List<WebElementDTO> weDtoList = weManager.readWebElementData(excelFilePath, weDataSheetName);
		  
		  this.runTestCase(tcDtoList, weDtoList);
	  }
	
	
	
}


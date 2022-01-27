package practice;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.crm.GenericLibraries.BaseClass;
import com.crm.comcast.objectrepositorylib.Home;

//@Listeners(com.crm.GenericLibraries.ListenerImp.class)
public class SampleTestForScreenshot extends BaseClass{

	@Test(retryAnalyzer = com.crm.GenericLibraries.ReTryImpClass.class)
	
	public void sampleCodeTest() throws Throwable {
		
		Home hp = new Home(driver);
		hp.getContactLink();
		
		Assert.assertEquals("A", "B");
		
	}
}

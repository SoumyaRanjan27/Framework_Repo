package OrganizationTest;

import java.io.IOException;


import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import elementRepository.CreateOrganizationPage;
import elementRepository.HomePage;
import elementRepository.OrganizationInfoPage;
import elementRepository.OrganizationPage;
import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.WebDriverUtility;

public class ToCreateOrganizationWithIndustryTest extends BaseClass{
	
	@Test(groups="regression")
	public void toCreateOrgWithIndustry() throws EncryptedDocumentException, IOException {
		HomePage hp=new HomePage(driver);
		hp.getOrganizationlink().click();
		OrganizationPage op=new OrganizationPage(driver);
		op.getOrganizationlookupimagelink().click();
		
		ExcelFileUtility eutil=new ExcelFileUtility();
		String ORGNAME = eutil.toReadDataFromExcelFile("Organization", 3, 2);
		String industrydropdown = eutil.toReadDataFromExcelFile("Organization",3,3);
		CreateOrganizationPage cop=new CreateOrganizationPage(driver);
		JavaUtility jutil=new JavaUtility();
		cop.getOrgname().sendKeys(ORGNAME+jutil.toGenarateRandomNumber());
		WebElement industry = cop.getIndustry();
		WebDriverUtility wutil=new WebDriverUtility();
		wutil.toHandleDropDown(industrydropdown, industry);
		
		cop.getSave().click();
		
	    OrganizationInfoPage oip=new OrganizationInfoPage(driver);
	    String orgname = oip.getOrganizationheaderelement().getText();
	    Assert.assertTrue(orgname.contains(ORGNAME));
	}

	
}

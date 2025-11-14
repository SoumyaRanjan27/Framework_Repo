package contactTest;



import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import elementRepository.ContactInfoPage;
import elementRepository.ContactsPage;
import elementRepository.CreateContactsPage;
import elementRepository.HomePage;
import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.WebDriverUtility;

public class ToCreateContactWithOrgTest extends BaseClass{
	
	@Test(groups = "smoke")
	public void toCreateContactWithOrg_005() throws EncryptedDocumentException, IOException {
		

		HomePage hp = new HomePage(driver);
		hp.getContactslink().click();
		ContactsPage cp = new ContactsPage(driver);
		cp.getContactslookupimagelink().click();
		ExcelFileUtility eutil = new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		
		CreateContactsPage ccp = new CreateContactsPage(driver);
		ccp.getLastnametextfield().sendKeys(LASTNAME);
		WebDriverUtility wutil = new WebDriverUtility();
		ccp.getContactorganization().click();
		wutil.toSwitchWindow(driver, "Accounts");
		ccp.getSelectorganization().click();
		wutil.toSwitchWindow(driver, "Contacts");
		
		ccp.getSavebutton().click();
		
		ContactInfoPage cip = new ContactInfoPage(driver);
		String lastname = cip.getHeaderElement().getText();
		
		
		Assert.assertTrue(lastname.contains(LASTNAME));

		
		
	}
	
	

}

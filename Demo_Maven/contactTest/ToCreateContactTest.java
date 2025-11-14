package contactTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import elementRepository.ContactInfoPage;
import elementRepository.ContactsPage;
import elementRepository.CreateContactsPage;
import elementRepository.HomePage;
import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
@Listeners(genericUtility.ListenersImplementation.class)
public class ToCreateContactTest extends BaseClass{
	
	@Test(groups = "smoke")
	public void toCreateContact_001() throws EncryptedDocumentException, IOException {
		
		HomePage hp=new HomePage(driver);
		hp.getContactslink().click();
		ContactsPage cp=new ContactsPage(driver);
		cp.getContactslookupimagelink().click();
		
		ExcelFileUtility eutil=new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		CreateContactsPage ccp=new CreateContactsPage(driver);
		ccp.getLastnametextfield().sendKeys(LASTNAME);
		ccp.getSavebutton().click();
		
		//Fail
		Assert.fail();
		
		ContactInfoPage cip=new ContactInfoPage(driver);
		String lastname = cip.getHeaderElement().getText();
		
		Assert.assertTrue(lastname.contains(LASTNAME));
	}
}

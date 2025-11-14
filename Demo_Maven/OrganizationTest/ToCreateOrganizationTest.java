package OrganizationTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import elementRepository.CreateOrganizationPage;
import elementRepository.HomePage;
import elementRepository.OrganizationInfoPage;
import elementRepository.OrganizationPage;
import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;

public class ToCreateOrganizationTest extends BaseClass {

	@Test(groups = "regression")
	public void toCreateOrganization_002() throws EncryptedDocumentException, IOException {

		HomePage hp = new HomePage(driver);
		hp.getOrganizationlink().click();
		OrganizationPage op = new OrganizationPage(driver);
		op.getOrganizationlookupimagelink().click();
		ExcelFileUtility eutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		String ORGANIZATIONNAME = eutil.toReadDataFromExcelFile("Organization", 1, 2) + jutil.toGenarateRandomNumber();
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.getOrgname().sendKeys(ORGANIZATIONNAME);
		cop.getSave().click();

		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String organizationname = oip.getOrganizationheaderelement().getText();

		Assert.assertTrue(organizationname.contains(ORGANIZATIONNAME));
	}

}

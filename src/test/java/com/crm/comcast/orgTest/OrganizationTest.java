package com.crm.comcast.orgTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.GenericLibraries.BaseClass;
import com.crm.comcast.objectrepositorylib.CreatingNewOrganization;
import com.crm.comcast.objectrepositorylib.Home;
import com.crm.comcast.objectrepositorylib.OrganizationInformation;
import com.crm.comcast.objectrepositorylib.Organizations;

public class OrganizationTest extends BaseClass {
	
@Test(groups = "smokeTest")
	public void createOrganiztionTest() throws Throwable, Throwable {
	
		//read test data from excel file
		String orgName = eLib.getDataFromExcel("Organization", 4, 2) + jLib.getRandomNumber();
		//STEP 2: Navigate to Organization page
		Home hp = new Home(driver);
		hp.getOrgLink().click();
			
		//STEP 3: Navigate to create new organization page
		Organizations op = new Organizations(driver);
		op.getCreateNewOrgImg().click();
			
		//STEP 4: Creating new Organization
		CreatingNewOrganization cno = new CreatingNewOrganization(driver);
		cno.createOrg(orgName);
			
		//STEP 5: Verify the organization details
		OrganizationInformation oi = new OrganizationInformation(driver);
		String actOrgSucMsg = oi.getOrgHeaderSucMsg().getText();
		
		Assert.assertTrue(actOrgSucMsg.contains(orgName));
		/*
		if (actOrgSucMsg.contains(orgName)) {
			System.out.println("organization created successfully == Passed script");
		}
		else {
			System.out.println("organization not created successfully == Failed script");
		}   */
}


@Test(groups = "regressionTest")
	public void createOrganizationWithIndTest() throws Throwable, Throwable {
		
		//read test data from excel file
		String orgName = eLib.getDataFromExcel("Organization", 4, 2) + jLib.getRandomNumber();
		String indType = eLib.getDataFromExcel("Organization", 4, 3);	
	
		//STEP 2: Navigate to Organization page
		Home hp = new Home(driver);
		hp.getOrgLink().click();
				
		//STEP 3: Navigate to create new organization page
		Organizations op = new Organizations(driver);
		op.getCreateNewOrgImg().click();
				
		//STEP 4: Creating new Organization
		CreatingNewOrganization cno = new CreatingNewOrganization(driver);
		cno.createOrg(orgName, indType);
				
		//STEP 5: Verify the organization details
		OrganizationInformation oi = new OrganizationInformation(driver);
		String actOrgSucMsg = oi.getOrgHeaderSucMsg().getText();
		
		SoftAssert softA = new SoftAssert();
		softA.assertEquals(actOrgSucMsg, orgName);
		
		/*
		if (actOrgSucMsg.contains(orgName)) {
			System.out.println("organization created successfully == Passed script");
		}
		else {
			System.out.println("organization not created successfully == Failed script");
		}    */
		
	}
}
		
		

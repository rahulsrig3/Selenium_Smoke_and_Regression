package practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.GenericLibraries.ExcelUtility;

public class DataProviderExcel extends ExcelUtility{

	@Test(dataProvider = "cartItemAndQty")
	
	public void addToCart(String itemName, String itemQty) {
		
		System.out.println("Item "+itemName+" with quantity "+itemQty+" added to cart successfully");
	}
	
	@DataProvider
	
	public Object[][] cartItemAndQty() throws Throwable, Throwable {
		
		ExcelUtility eLib = new ExcelUtility();
		Object[][] objArr = new Object[getRowCount("addToCart")][2];
		
		for (int i = 0; i < 8; i++) {
			
			objArr[i][0] = eLib.getDataFromExcel("addToCart", i, 0);
			objArr[i][1] = eLib.getDataFromExcel("addToCart", i, 1);
		}
		
		return objArr;
	}
}

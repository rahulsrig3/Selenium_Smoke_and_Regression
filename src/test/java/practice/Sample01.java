package practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Sample01 {

	@Test(dataProvider = "dataProvider_bookTicketTest")
	
	public void bookTicketTest(String src, String dst, int tickets) {
		
		System.out.println("book "+tickets+" tickets from "+src+" to "+dst);
	}
	
	@DataProvider
	public Object[][] dataProvider_bookTicketTest() {
		
		Object[][] objArr = new Object[5][3];
		
		objArr[0][0] = "Bangalore";
		objArr[0][1] = "Goa";
		objArr[0][2] = 10;
		
		objArr[1][0] = "Mumbai";
		objArr[1][1] = "Mangalore";
		objArr[1][2] = 20;
		
		objArr[2][0] = "Calcutta";
		objArr[2][1] = "Arrah";
		objArr[2][2] = 30;
		
		objArr[3][0] = "Hydrabad";
		objArr[3][1] = "Kocchi";
		objArr[3][2] = 40;
		
		objArr[4][0] = "Kanpur";
		objArr[4][1] = "Delhi";
		objArr[4][2] = 50;
		
		return objArr;
	}
}

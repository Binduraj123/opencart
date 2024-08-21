package utility;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderMethod {
	
@DataProvider(name="Logindata")
 public  String[][]getdata() throws IOException{
	 //file path 
	String filepath=".//testdata/Opencart_LoginData.xlsx";
	
	//extract the data from the file
	ExcelUtility xutil=new ExcelUtility(filepath);
	int rows=xutil.getRowCount("Sheet1");
	int cols=xutil.getCellCount("Sheet1", 1);
	
	
	//we have extracted the rows and cols into 2 dimensional array
	//same number of rows and columns must be present in both excel and in 2 dimensional array.
	//we craete 2 dimesional array and speciffy the rows and cols
	String data[][]=new String[rows][cols];
	//read the dta from the file and store in a 2 dimensional array.
	for(int i=1;i<=rows;i++) 
	{
		for(int j=0;j<cols;j++)
		{
			data[i-1][j]=xutil.getCellData("Sheet1",i, j);
		}
	}
	
	
	return data;
	 
 }
}
/*public String[][] logindata() throws IOException {
	
	String file=".//testdata/Opencart_LoginData.xlsx";
	ExcelUtility xl=new ExcelUtility(file);
	int totalrows=xl.getRowCount("Sheet1");
	int totalcols=xl.getCellCount("Sheet1", 1);
	
	String data[][]=new String[totalrows][totalcols];
	
	for(int i=1;i<=totalrows;i++) {
		for(int j=0;j<totalcols;j++) {
			data[i-1][j]=xl.getCellData("Sheet1", i, j);
		}
	}
	
	return data;
	
	
}
	
}*/

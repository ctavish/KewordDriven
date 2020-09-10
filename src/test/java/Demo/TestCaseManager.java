package Demo;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class TestCaseManager {
	
	public List<TestCaseDTO> readTestCaseData(String excelFilePath, String sheetName)
	{
		List<TestCaseDTO> ret = new ArrayList<TestCaseDTO>();
		
		try
		{
			if(excelFilePath!=null && sheetName!=null)
			{
				FileInputStream fInputStream = new FileInputStream(excelFilePath.trim());
				
				/* Create excel workbook object. */
				Workbook workBook = new XSSFWorkbook(fInputStream);
	
				/* Get excel work sheet by name. */
				Sheet testCaseSheet = workBook.getSheet(sheetName);
				
				int lastRowNum = testCaseSheet.getLastRowNum();
				
				TestCaseDTO tmpTestCaseDto = null;
				
				/* First row is header row, so we read data from second row. */
				for(int i=1; i<lastRowNum+1; i++)
				{
					Row row = testCaseSheet.getRow(i);
					
					/* Get first cell value.*/
					String cValue = UtilTool.getExecelCellStringValue(row.getCell(0));
					/* This is the test case name cell. It is start for one test case.*/
					if(cValue.trim().length()>0)
					{
						tmpTestCaseDto = new TestCaseDTO();
						tmpTestCaseDto.setName(cValue);
						
						ret.add(tmpTestCaseDto);
					}
										
					TestStepDTO testStepDto = new TestStepDTO();
					/* Get second cell value.*/
					cValue = UtilTool.getExecelCellStringValue(row.getCell(1));
					testStepDto.setActionKeyword(cValue);
					
					/* Get third cell value.*/
					cValue = UtilTool.getExecelCellStringValue(row.getCell(2));
					testStepDto.setWebEleSymbolName(cValue);
					
					/* Get fourth cell value.*/
					cValue = UtilTool.getExecelCellStringValue(row.getCell(3));
					testStepDto.setWebEleValue(cValue);
					
					tmpTestCaseDto.getStepList().add(testStepDto);
				}
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			return ret;
		}
	}

}

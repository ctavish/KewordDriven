package Demo;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class WebElementManager {

	public List<WebElementDTO> readWebElementData(String excelFilePath, String sheetName)
	{
		List<WebElementDTO> ret = new ArrayList<WebElementDTO>();
		
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
				
				/* First row is header row, so we read data from second row. */
				for(int i=1; i<lastRowNum+1; i++)
				{
					Row row = testCaseSheet.getRow(i);
					
					WebElementDTO webElementDto = new WebElementDTO();
					
					String cValue = row.getCell(0).getStringCellValue();
					webElementDto.setSymbolName(cValue);
					
					cValue = row.getCell(1).getStringCellValue();
					webElementDto.setLocatorType(cValue);
					
					cValue = row.getCell(2).getStringCellValue();
					webElementDto.setLocatorValue(cValue);
					
					ret.add(webElementDto);
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
	
	
	public static WebElementDTO getWebEleDtoBySymbolName(List<WebElementDTO> weDtoList, String symbolName)
	{
		WebElementDTO ret = new WebElementDTO();
		
		if(weDtoList!=null && symbolName.trim().length()>0)
		{
			int size = weDtoList.size();
			for(int i=0;i<size;i++)
			{
				WebElementDTO tmpDto = weDtoList.get(i);
				if(symbolName.equalsIgnoreCase(tmpDto.getSymbolName()))
				{
					ret = tmpDto;
					break;
				}
			}
		}
		return ret;
	}
}
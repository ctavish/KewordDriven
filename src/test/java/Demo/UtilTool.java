package Demo;

import org.apache.poi.ss.usermodel.Cell;

public class UtilTool {

	public static String getExecelCellStringValue(Cell cell)
	{
		String ret = "";
		if(cell!=null)
		{
			ret = cell.getStringCellValue();
		}
		return ret;
	}
}

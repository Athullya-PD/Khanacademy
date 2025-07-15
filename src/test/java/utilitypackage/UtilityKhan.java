package utilitypackage;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UtilityKhan {
    public static String getData(String path, int row, int col) {
        try {
            FileInputStream fis = new FileInputStream(path);
            Workbook wb = new XSSFWorkbook(fis);
            Sheet sheet = wb.getSheetAt(0);
            String data = sheet.getRow(row).getCell(col).toString();
            wb.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}

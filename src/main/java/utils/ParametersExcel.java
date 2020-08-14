package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.apache.poi.ss.usermodel.CellType.STRING;

public class ParametersExcel {

    private Workbook wb;
    private Sheet sheet;
    private String sheetName;

    public Workbook GetExcelFile(String filePath) {
        // initialize Workbook null
        wb = null;
        try {
            // reading data from a file in the form of bytes
            FileInputStream fis = new FileInputStream(filePath);
            // constructs an XSSFWorkbook object, by buffering the whole stream into the memory
            wb = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return wb;
    }

    public Sheet GetExcelSheet(String sheetName) {
        this.sheetName = sheetName;
        sheet = wb.getSheet(sheetName);
        return sheet;
    }

    public String GetParamValue(String paramName) throws IOException {
        // variable for storing the cell value
        String value = null;
        // getting the XSSFSheet object at given name
        this.sheet = wb.getSheet(this.sheetName);

        for (Row row : sheet) {
            String cellValue = row.getCell(0).getStringCellValue();

            if (cellValue.equalsIgnoreCase(paramName)) {

                Cell cell = row.getCell(1);         // getting cell value
                cell.setCellType(STRING);             // convert to String all cell Types
                value = cell.getStringCellValue();    // getting cell value
                break;
            }
        }
        wb.close();
        return value;                       // returns the cell value
    }
}

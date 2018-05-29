package repository;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by N.Babenkov on 14.05.2018.
 **/
public class DocumentRepositoryFromXLS extends DocumentRepository {
    public DocumentRepositoryFromXLS(BufferedInputStream is) throws IOException{
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row;
            XSSFCell cell;
            int contrIndex = -1;
            int distIndex = -1;
            Iterator iterator = sheet.rowIterator();
            row = (XSSFRow) iterator.next();

            Iterator cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                cell = (XSSFCell) cellIterator.next();
                if (cell.getCellTypeEnum() == CellType.STRING) {
                    String fieldName = cell.getStringCellValue().toLowerCase();
                    if (fieldName.equals("договор") || fieldName.equals("dog"))
                        contrIndex = cell.getColumnIndex();
                    else if (fieldName.equals("участок") || fieldName.equals("res"))
                        distIndex = cell.getColumnIndex();
                }
            }

            if ((contrIndex == -1) || (distIndex == -1))
                throw new IOException("No contract or district column in xls file!");

            while (iterator.hasNext()) {
                row = (XSSFRow) iterator.next();
                try {
                    int district = (int) row.getCell(distIndex).getNumericCellValue();
                    int contract = (int) row.getCell(contrIndex).getNumericCellValue();
                    addDocument(district, contract);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
    }
}

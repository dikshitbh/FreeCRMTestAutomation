package org.crm.util;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.crm.base.TestBase;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestUtil extends TestBase {
    public static long PAGE_LOAD_TIMEOUT = 20;
    public static long IMPLICIT_WAIT = 10;
    public static long LONG_WAIT = 30;
    public static String TESTDATA_SHEET_PATH = "C:\\Sonal\\FreeCRMTestAutomation\\src\\main\\java\\org\\crm\\testdata\\freeCRMTestData.xlsx";
    static Workbook book ;
    static Sheet sheet;

    public void switchToFrame(String frameName){
        driver.switchTo().frame(frameName);
    }

    public void addWaitUntilVisibilityOfElement(WebElement ele){
        WebDriverWait wait = new WebDriverWait(driver, LONG_WAIT);
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public static Object[][] getTestData(String sheetName) {
        FileInputStream file = null;

        Object[][] data = null;

        try {
            file = new FileInputStream(TESTDATA_SHEET_PATH);
            book = WorkbookFactory.create(file); // Handles both XLS and XLSX formats
            sheet = book.getSheet(sheetName);
            data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                    Cell cell = sheet.getRow(i + 1).getCell(j);
                    data[i][j] = (cell == null) ? "" : cell.toString(); // Handle null cells
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Exception: File not found " + e.getMessage());
        } catch (InvalidFormatException e) {
            System.err.println("Exception: Invalid file format " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Exception: IO error " + e.getMessage());
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            e.printStackTrace();
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException e) {
                System.err.println("Exception: Error closing file/book " + e.getMessage());
            }
        }
        return data;
    }

    public static void takeScreenshotAtEndOfTest() throws IOException {
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String currDir = System.getProperty("user.dir");
        FileUtils.copyFile(srcFile,new File(currDir+"\\screenshots\\"+System.currentTimeMillis()+".png"));
    }



}

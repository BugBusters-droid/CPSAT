package tests;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Point6LiveMarket {

    @Test
    public void testLiveMarketPoint6() throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.nseindia.com/");
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//li[@id='main_livemkt']"))).perform();
        driver.findElement(By.linkText("Top Ten Gainers / Losers")).click();

        List<WebElement> irows = driver.findElements(By.xpath("//*[@id='topGainers']/tbody/tr"));
        int iRowsCount = irows.size();
        List<WebElement> icols = driver.findElements(By.xpath("//*[@id='topGainers']/tbody/tr[1]/th"));
        int iColsCount = icols.size();
        System.out.println("Selected web table has " + iRowsCount + " Rows and " + iColsCount + " Columns");
        System.out.println();

        FileOutputStream fos = new FileOutputStream("./data/data (3).xlsx");
        XSSFWorkbook wkb = new XSSFWorkbook();
        XSSFSheet sheet1 = wkb.createSheet("DataStorage");

        for (int i = 1; i <= iRowsCount; i++) {
            for (int j = 1; j <= iColsCount; j++) {
                if (i == 1) {
                    WebElement val = driver.findElement(By.xpath("//*[@id='topGainers']/tbody/tr[" + i + "]/th[" + j + "]"));
                    String a = val.getText();
                    System.out.print(a);

                    XSSFRow excelRow = sheet1.createRow(i);
                    XSSFCell excelCell = excelRow.createCell(j);
                    excelCell.getCellType();
                    // excelCell.setCellType(XSSFCell.CELL_TYPE_STRING);
                    excelCell.setCellValue(a);
                    wkb.write(fos);
                } else {
                    WebElement val = driver.findElement(By.xpath("//*[@id='topGainers']/tbody/tr[" + i + "]/td[" + j + "]"));
                    String a = val.getText();
                    System.out.print(a);

                    XSSFRow excelRow = sheet1.createRow(i);
                    XSSFCell excelCell = excelRow.createCell(j);
                    //excelCell.setCellType(XSSFCell.CELL_TYPE_STRING);
                    excelCell.setCellValue(a);
                    wkb.write(fos);
                }
            }
            System.out.println();
        }
        fos.flush();
        wkb.write(fos);
        fos.close();



    }

}

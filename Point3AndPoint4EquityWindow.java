package tests;

import helpers.Lib;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Point3AndPoint4EquityWindow {
    Lib lib = new Lib();

    @Test
    public void testEquityPoint3() throws InterruptedException, AWTException, IOException {
        System.setProperty("webdriver.gecko.driver", "./driver/geckodriver");

        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.nseindia.com/");
        driver.findElement(By.xpath("//input[@id='keyword']")).sendKeys("Eicher Motors Limited");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@id='keyword']")).sendKeys(Keys.ENTER);
        String faceValue = driver.findElement(By.xpath("//span[@id='faceValue']")).getText();
        System.out.println("faceValue is : " + faceValue);

        String date = driver.findElement(By.xpath("//span[@id='mock_cm_adj_high_dt']")).getText();
        String weekHigh = driver.findElement(By.xpath("//span[@id='high52']")).getText();
        System.out.println("weekHigh value is " + weekHigh + date);

        String date1 = driver.findElement(By.xpath("//span[@id='mock_cm_adj_low_dt']")).getText();
        String weeklow = driver.findElement(By.xpath("//span[@id='low52']")).getText();
        System.out.println("weekHigh value is " + weeklow + date1);

        Robot r = new Robot();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle rect = new Rectangle(d);
        BufferedImage img = r.createScreenCapture(rect);
        ImageIO.write(img, "bmp", new File("./screenshots/point3Eicher Motors Limited" + ".bmp"));
        driver.close();
    }

    @Test
    public void testEquityPoint4() throws InterruptedException, IOException, AWTException {
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String xlPath = "./data/data (1).xlsx";
        int rowCount = Lib.getRowCount(xlPath,"Data for point4");
        System.out.println("No of rows are :----->  " + rowCount);

        for (int i = 1; i <= rowCount; i++) {
            String companyName = Lib.getCellValue(xlPath,"Data for point4", i, 0);
            System.out.println("Company to be entered in equity search box is :------> " + companyName);

            driver.manage().window().maximize();
            driver.get("https://www.nseindia.com/");
            driver.findElement(By.xpath("//input[@id='keyword']")).sendKeys(companyName);
            Thread.sleep(3000);
            driver.findElement(By.xpath("//input[@id='keyword']")).sendKeys(Keys.ENTER);
            Thread.sleep(3000);
            String faceValue = driver.findElement(By.xpath("//span[@id='faceValue']")).getText();
            System.out.println("faceValue for the company " + companyName + " is :-------> : " + faceValue);

            String date = driver.findElement(By.xpath("//span[@id='mock_cm_adj_high_dt']")).getText();
            String weekHigh = driver.findElement(By.xpath("//span[@id='high52']")).getText();
            System.out.println("weekHigh value for the company " + companyName + " is :-------> " + weekHigh + date);

            String date1 = driver.findElement(By.xpath("//span[@id='mock_cm_adj_low_dt']")).getText();
            String weekLow = driver.findElement(By.xpath("//span[@id='low52']")).getText();
            System.out.println("weekHigh value for the company " + companyName + " is ------>" + weekLow + date1);

            Robot r = new Robot();
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle rect = new Rectangle(d);
            BufferedImage img = r.createScreenCapture(rect);
            ImageIO.write(img, "bmp", new File("./screenshots/point4Equity" + companyName + ".bmp"));
        }
        driver.close();
    }



}

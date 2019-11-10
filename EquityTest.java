package tests;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EquityTest {

    @Test
    public void testEquityPoint4() throws InterruptedException, IOException, AWTException {
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver");
        WebDriver driver = new ChromeDriver();

        String xlPath = "./data/data (1).xlsx";
        FileInputStream fis = new FileInputStream(xlPath);

        Workbook wb = WorkbookFactory.create(fis);
        int rowCount = wb.getSheet("Data for point4").getLastRowNum();
        System.out.println("No of rows are :----->  " + rowCount);

        for (int i = 1; i <= rowCount; i++) {
            String companyName = wb.getSheet("Data for point4").getRow(i).getCell(0).toString();
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
            ImageIO.write(img, "bmp", new File("./screenshots/point4" + companyName + ".bmp"));
        }
        driver.close();
    }

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
    public void testEquityPoint2() {
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.nseindia.com/");
        int number = 0;
        List numbers = new ArrayList();
        List<WebElement> lst = driver.findElements(By.xpath("//ul[@class='advanceTab']/li"));
        for (int i = 0; i < lst.size(); i++) {
            String str = lst.get(i).getText();
            System.out.println("Market watch string is " + str);
            StringBuilder myNumbers = new StringBuilder();
            for (int j = 0; j < str.length(); j++) {
                if (Character.isDigit(str.charAt(j))) {
                    myNumbers.append(str.charAt(j));
                    System.out.println(str.charAt(j) + " is a digit.");
                }
            }
            number = Integer.parseInt(myNumbers.toString());
            numbers.add(number);
        }
        List<Integer> sortedList = new ArrayList<Integer>(numbers);
        Collections.sort(sortedList);
        System.out.println("Sorted list is " + sortedList);
        int minNumber = sortedList.get(0);
        System.out.println("Lowest number is " + minNumber);
    }

    @Test
    public void testEquityPoint6() throws InterruptedException, IOException {
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


    @Test
    public void testEquityPoint5() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.shoppersstop.com/");
        driver.manage().window().maximize();
        int count = 0;

        int slick_dots = driver.findElements(By.xpath("//ul[@class='slick-dots']/li")).size();
        System.out.println("No of times banner is supposed to change is :----->" + slick_dots);

        for (int i = 1; i <= slick_dots; i++) {
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@style='right: 0px; display: block;']")).click();
            count++;
        }
        System.out.println("No of times banner clicked right side is " + count);

        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='firstVisit']/div[1]")).click();
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("/html/body/main/nav/div[2]/div/ul/li[4]/a"))).perform();
        Thread.sleep(2000);
        action.moveToElement(driver.findElement(By.xpath("/html/body/main/nav/div[2]/div/ul/li[4]/div/div/ul/li[6]/a"))).perform();
        int accHeadersNos = driver.findElements(By.xpath("/html/body/main/nav/div[2]/div/ul/li[4]/div/div/ul/li[6]/div/ul/li/div/ul/li")).size();
        System.out.println("No of Men's fragrance category present are :-->" + accHeadersNos);
        String accHeadersText = "";
        System.out.println("Accessories under Men's Fragrance are :----->");
        for (int i = 1; i <= accHeadersNos; i++) {
            accHeadersText = driver.findElement(By.xpath("/html/body/main/nav/div[2]/div/ul/li[4]/div/div/ul/li[6]/div/ul/li/div/ul/li[" + i + "]")).getText();
            System.out.println(accHeadersText);
        }

        driver.findElement(By.xpath("//*[@id='header-info-bar']/div/div/div[2]/ul/li[1]/a")).click();

        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='city-name']")).click();
        WebElement listBox = driver.findElement(By.xpath("//*[@id='city-name']"));
        Select select = new Select(listBox);
        List<WebElement> allCities = select.getOptions();
        List citiesList = new ArrayList();
        System.out.println("cities are :---->");
        for (int i = 0; i < allCities.size(); i++) {
            String city = allCities.get(i).getText();
            System.out.println(city);
            citiesList.add(city);
        }
        System.out.println(citiesList);
        Assert.assertTrue(citiesList.contains("Agra"));
        Assert.assertTrue(citiesList.contains("Bhopal"));
        Assert.assertTrue(citiesList.contains("Mysore"));

        String pageTitle = driver.getTitle();
        System.out.println("Page title is :----->  " + pageTitle);
    }

    @Test
    public void testEquityPoint1() throws Exception {
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver");


        WebDriver driver = new ChromeDriver();
        driver.get("http://agiletestingalliance.org/");
        driver.manage().window().maximize();
        SoftAssert softAssert = new SoftAssert();

        driver.findElement(By.xpath("/html/body/header/div/div/nav/ul/li[6]/a")).click();
        int noOfCertificationLogo = driver.findElements(By.xpath("//map[@name='image-map']/area")).size();
        System.out.println("No of certification icons present on the page are :---->  " + noOfCertificationLogo);

        System.out.println("URL Every Certification Icon Is Pointing To ---------->      ");
        for (int i = 1; i <= noOfCertificationLogo; i++) {
            String link = driver.findElement(By.xpath("//map[@name='image-map']/area[" + i + "]")).getAttribute("href");
            System.out.println(link);
            int responseCode = Integer.parseInt(isLinkBroken(link));
            if(responseCode==200)
            {
                System.out.println(link+" is working");
            }
            else
            {
                softAssert.assertEquals(responseCode, 200, "Link is broken and the response code is "+responseCode);
            }
        }

        Robot r = new Robot();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle rect = new Rectangle(d);
        BufferedImage img = r.createScreenCapture(rect);
        ImageIO.write(img, "bmp", new File("./screenshots/CertificationPage" + ".bmp"));

        Thread.sleep(3000);
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//*[@id='content']/div/div[2]/map/area[4]"))).perform();
        Thread.sleep(5000);
        Robot r1 = new Robot();
        Dimension d1 = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle rect1 = new Rectangle(d1);
        BufferedImage img1 = r1.createScreenCapture(rect1);
        ImageIO.write(img1, "bmp", new File("./screenshots/HoverOnCP-CCT" + ".bmp"));
    }


    public static String isLinkBroken(String link) throws Exception {
        URL url = new URL(link);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            connection.connect();
            String responseMessage = connection.getResponseMessage();
            int responseCode = connection.getResponseCode();
            connection.disconnect();
            System.out.println("Response is :----> "+responseMessage);
            System.out.println("Response code is :---->"+responseCode);
            return String.valueOf(responseCode);
        } catch (Exception exp) {
            return exp.getMessage();
        }
    }
}


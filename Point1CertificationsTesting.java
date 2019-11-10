package tests;


import helpers.Lib;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
//import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Point1CertificationsTesting {

    Lib lib = new Lib();

    @Test
    public void testCertificationsPoint1() throws Exception {
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
            int responseCode = Integer.parseInt(lib.isLinkBroken(link));
            if (responseCode == 200) {
                System.out.println(link + " is working");
            } else {
                softAssert.assertEquals(responseCode, 200, "Link is broken and the response code is " + responseCode);
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
}

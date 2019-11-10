package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Point5ShoppersStop {

    @Test
    public void testShoppersStopPoint5() throws InterruptedException {
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

}

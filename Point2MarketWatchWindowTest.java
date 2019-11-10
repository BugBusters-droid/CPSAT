package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Point2MarketWatchWindowTest {

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


}

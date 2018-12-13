package ua.org.autotest;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;


public class AutoTest {

    private static WebDriver driver;

    @BeforeClass
    public static void Enter() {
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://mailinator.com");
    }

    @Test
    public void testRunning() {
        loginAs("donni.dima@gmail.com");
        checkingFields("Donni. Dima", "1st letter");
        checkingContent();
    }

    public void loginAs(String username){
        driver.findElement(By.cssSelector("#inboxfield")).sendKeys(username);
        driver.findElement(By.xpath("/html/body/section[1]/div/div[3]/" +
                "div[2]/div[2]/div[1]/span/button")).click();
    }

    public void checkingFields(String username, String subject){
        String verifyString1 = driver.findElement(By.cssSelector("#row_donni\\2e " +
                "dima-1538819817-3583517 > td:nth-child(3)")).getText();
        Assert.assertEquals(username, verifyString1);

        String verifyString2 = driver.findElement(By.cssSelector("#row_donni\\2e dima-" +
                "1538819817-3583517 > td:nth-child(4)")).getText();
        Assert.assertEquals(subject, verifyString2);
    }

    public void checkingContent(){
        driver.findElement(By.cssSelector("#row_donni\\2e dima-" +
                "1538819817-3583517 > td:nth-child(4)")).click();
        try
        {
            driver.findElement(By.xpath("/html/body/div"));
        } catch(NoSuchElementException e)
        {
            System.out.print("Try again, the element was not found");
        }
    }

    public void Hello() {
	System.out.println("Hello everyone!");
    }

    @AfterClass
    public static void CLose()
    {
        driver.quit();
    }
}


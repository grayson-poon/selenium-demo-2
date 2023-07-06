import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.Random;

public class Demo {
    public static ChromeOptions options;
    public static WebDriver driver;


    public static void main(String[] args) throws InterruptedException {
//        setup ChromeDriver options
        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

//        run tests
        Demo.testGenderOptions();
        Demo.createAccount();
    }

    public static void testGenderOptions() throws InterruptedException {
//        create new driver
        driver = new ChromeDriver(options);
        driver.get("https://www.facebook.com");
        driver.manage();

//        find new account button
        WebElement new_acc_button = driver.findElement(By.linkText("Create new account"));
        Thread.sleep(2000);
        new_acc_button.click();
        Thread.sleep(1000);

//        check signup modal exists
        System.out.println("Does sign up modal exist?");
        System.out.println(Demo.existsElement(driver, By.className("_8ien")));

//        check signup modal says sign up
        System.out.println("Does modal exist and does it have a child (direct or indirect) that says 'Sign Up'?");
        System.out.println(Demo.existsElement(driver, By.xpath("//div[@class='_8ien']//div[text()='Sign Up']")));

//        check there is an option to select gender on the form
        System.out.println("Is there a gender selector option?");
        System.out.println(Demo.existsElement(driver, By.xpath("//span[@data-name='gender_wrapper']")));
        Thread.sleep(1000);

//        check there are 3 gender options
        System.out.println("Does gender wrapper have 3 options?");
        List<WebElement> gender_options = driver.findElements(By.xpath("//span[@data-name='gender_wrapper']/*"));
        System.out.println(gender_options.size() == 3);
        Thread.sleep(1000);

        // randomly select a gender option and click element
        Random random = new Random();
        int index = random.nextInt(2);
        System.out.println(index);
        gender_options.get(index).click();

        // quit driver
        Thread.sleep(2000);
        driver.quit();
    }

    public static void createAccount() throws InterruptedException {
//        enter values into input tag field
        String input_email = "test.test@gmail.com";
        String input_pass = "password";

//        create new driver
        driver = new ChromeDriver(options);
        driver.get("https://www.facebook.com");
        driver.manage();

//        input email into email field
        WebElement email = driver.findElement(By.id("email"));
        Thread.sleep(1000);
        email.sendKeys(input_email);

//        input password into pass field
        WebElement pass = driver.findElement(By.id("pass"));
        Thread.sleep(1000);
        pass.sendKeys(input_pass);

//        quit driver
        Thread.sleep(1000);
        driver.quit();
    }

//    return true is element exists, false if not
    private static Boolean existsElement(WebDriver driver, By eleLocator) {
        try {
            driver.findElement(eleLocator);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
}

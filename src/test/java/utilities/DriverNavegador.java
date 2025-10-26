package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverNavegador {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Configura automáticamente el driver correcto para tu versión de Chrome
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().deleteAllCookies();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    protected void sleep(int timeMs){
        try {
            Thread.sleep(timeMs);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        }
    }
}

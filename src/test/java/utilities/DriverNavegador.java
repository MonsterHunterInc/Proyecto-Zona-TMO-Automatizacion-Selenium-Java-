package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.ArrayList;

public class DriverNavegador {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.out.println("Iniciando Chrome con el perfil seleccionado...");

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        //Ruta a tu perfil de Chrome y perfil de usuario clonado para Selenium
        options.addArguments("user-data-dir=C:\\Users\\Monst\\AppData\\Local\\Google\\Chrome\\User Data\\SeleniumProfile");

        // Configuración estable y segura
        options.addArguments("--no-sandbox"); //Deshabilita el mecanismo de aislamiento (sandbox) que Chrome usa para ejecutar procesos del navegador en espacios restringidos
        options.addArguments("--disable-dev-shm-usage"); // Deshabilita el uso de /dev/shm (memoria compartida) para evitar problemas en entornos con recursos limitados
        options.addArguments("--disable-gpu"); // Deshabilita la aceleración por hardware de la GPU para evitar problemas en sistemas sin soporte adecuado
        options.addArguments("--remote-debugging-port=9222"); // Habilita la depuración remota en el puerto 9222 para permitir la conexión de herramientas de desarrollo
        options.addArguments("--disable-features=OptimizationGuideModelDownloading,OptimizationHintsFetching"); // Deshabilita la descarga de modelos y sugerencias de optimización para mejorar el rendimiento y reducir el uso de datos
        options.addArguments("--disable-background-networking"); // Deshabilita las conexiones de red en segundo plano para mejorar la privacidad y reducir el uso de datos

        options.setExperimentalOption("detach", true); // Mantiene la ventana del navegador abierta después de que el script termine

        // Iniciar el navegador con las opciones configuradas
        driver = new ChromeDriver(options);

        // Confirmación de que el navegador se ha iniciado correctamente
        System.out.println("✅ Chrome iniciado correctamente.");

        // Navegar a la página inicial
        driver.get("https://zonatmo.com/");

        // Maximizar la ventana del navegador
        driver.manage().window().maximize();

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    protected void sleep(int timeMs){
        try {
            Thread.sleep(timeMs);
        } catch (InterruptedException e) {
            System.out.println("Tiempo interrumpido....");
        }
    }

}

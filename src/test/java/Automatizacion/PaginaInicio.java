package Automatizacion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverNavegador;

import java.sql.Driver;
import java.time.Duration;
import java.util.Set;

public class PaginaInicio extends DriverNavegador {

    @Test
    public void presionBotonBiblioteca() {
        //Realizo un xpath para encontrar el boton de biblioteca
        final var botonBiblioteca = driver.findElement(By.xpath("//a[@href='https://zonatmo.com/library']"));
        //Presiono el boton de biblioteca
        botonBiblioteca.click();

        sleep(2000);

        //Verifico que la url actual contenga /library
        final var urlfinal = driver.getCurrentUrl();
        System.out.println("La url actual es: " + urlfinal);

        //Realizo la asercion correspondiente para verificar que la redireccion fue correcta
        Assertions.assertTrue(urlfinal.contains("/library"), "La reedireccion a la biblioteca fallo o fue interrumpida");
    }

    @Test
    public void presionarBotonGroups() {
        //Realizo un xpath para encontrar el boton de Grupos
        final var botonGroups = driver.findElement(By.xpath("//a[@href='https://zonatmo.com/groups']"));
        //Presiono el boton de Grupos
        botonGroups.click();

        // Espero 2 segundos para que se abra la nueva pestaña
        sleep(2000);

        //Verifico que la url actual contenga /groups
        final var urlFinal = driver.getCurrentUrl();
        System.out.println("La url actual es: " + urlFinal);

        //Realizo la asercion correspondiente para verificar que la redireccion fue correcta
        Assertions.assertTrue(urlFinal.contains("/groups"), "La redireccion a la pagina de grupos fallo");
    }

    @Test
    public void presionarBotonListas() {
        //Realizo un xpath para encontrar el boton de Grupos
        final var botonListas = driver.findElement(By.xpath("//a[@href='https://zonatmo.com/lists']"));

        //Presiono el boton de Grupos
        botonListas.click();

        // Espero 2 segundos para que se abra la nueva pestaña
        sleep(2000);

        //Verifico que la url actual contenga /lists
        final var urlFinal = driver.getCurrentUrl();
        System.out.println("La url actual es: " + urlFinal);

        //Realizo la asercion correspondiente para verificar que la redireccion fue correcta
        Assertions.assertTrue(urlFinal.contains("/lists"), "La redireccion a la pagina de grupos fallo");
    }

    @Test
    public void presionarBotonForo() {
        //Guardo el ID de la pestaña principal
        final var ventanaPrincipal = driver.getWindowHandle();

        //Realizo un xpath para encontrar el boton de Foro)
        final var botonForo = driver.findElement(By.xpath("//a[@href='https://zonatmo.com/forum']"));
        botonForo.click();

        // Espero 2 segundos para que se abra la nueva pestaña
        sleep(2000);

        // Espero que se abra una nueva pestaña
        Set<String> pestañas = driver.getWindowHandles();
        Assertions.assertTrue(pestañas.size() > 1, "No se abrió una nueva pestaña al presionar el botón de Foro");

        // Cambio el control a la nueva pestaña
        for (var pestaña : pestañas) {
            if (!pestaña.equals(ventanaPrincipal)) {
                driver.switchTo().window(pestaña);
                break;
            }
        }

        // Espero 2 segundos para que se abra la nueva pestaña
        sleep(2000);

        //Se valida que la URL y el título sean los esperados
        final var urlFinal = driver.getCurrentUrl();
        final var tituloPagina = driver.getTitle();

        // Imprimo en consola los valores obtenidos
        Assertions.assertEquals("https://tmocommunity.com/", urlFinal, "La URL de la pestaña del foro es incorrecta");
        Assertions.assertEquals("TMOCommunity", tituloPagina, "El título de la pestaña del foro es incorrecto");

    }


}
